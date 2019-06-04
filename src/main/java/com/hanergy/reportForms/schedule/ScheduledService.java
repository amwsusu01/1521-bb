package com.hanergy.reportForms.schedule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.commons.utils.HttpUtil;
import com.hanergy.reportForms.commons.utils.RegUtils;
import com.hanergy.reportForms.entity.log.SystemLog;
import com.hanergy.reportForms.mapper.PersonnelFlowMapper;
import com.hanergy.reportForms.mapper.entity.JobApplication;
import com.hanergy.reportForms.service.personnelflow.PersonnelFlowService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScheduledService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String sfUrl = "https://api15.sapsf.cn/odata/v2/";

    @Autowired
    PersonnelFlowService personnelFlowService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 每天晚上11点从sf上抓取招聘相关数据
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void scheduled() {
        logger.info("=============【{}】开始抓取SF系统数据=============", DateUtils.dateToString(new Date(System.currentTimeMillis()), DateUtils.PATTERN_YYYYMMDDHHMMSS));
        Integer page = 0;
        String countUrl = sfUrl + "JobApplication/$count?&$format=json";
        String searchUrl = sfUrl + "JobApplication?&$skip=" + page + "&$top=1000&$format=json";
        //String filter = "&$filter=jobApplicationStatusAuditTrail/jobAppStatus/appStatusSetId%20eq%20%27183%27%20or%20jobApplicationStatusAuditTrail/jobAppStatus/appStatusSetId%20eq%20%27219%27";
        String expand = "&$expand=jobApplicationStatusAuditTrail,jobApplicationStatusAuditTrail/jobAppStatus,jobOffer,candidate,jobRequisition,jobRequisition/recruiter,jobRequisition/jobReqLocale,jobRequisition/jobLevel/picklistLabels,jobRequisition/approver,jobRequisition/hiringManager";
        String select = "&$select=jobApplicationStatusAuditTrail/lastModifiedDateTime,jobApplicationStatusAuditTrail/jobAppStatus/appStatusSetId,Dayeesouce,candidate/candidateId,candidate/lastName,candidate/firstName,candidate/nationalidcardmun,jobRequisition/buoncode,jobRequisition/legalEntitycode,jobRequisition/divicode,jobRequisition/jobLevel/picklistLabels/label,jobRequisition/jobReqLocale/jobTitle,jobRequisition/locationcode,jobRequisition/Officecode,jobRequisition/jobLevel/externalCode,jobRequisition/approver/firstName,jobRequisition/approver/lastName,jobRequisition/approver/userName,jobRequisition/locationcode,jobRequisition/Officecode,jobRequisition/hiringManager/firstName,jobRequisition/hiringManager/lastName,jobRequisition/recruiter/userName,jobRequisition/recruiter/lastName,jobRequisition/recruiter/firstName,jobOffer/jobStartDate";
        countUrl = countUrl + expand + select;
        searchUrl = searchUrl + expand + select;
        Object obj = HttpUtil.doGetRequest(countUrl);
        if (obj != null) {
            Integer count = Integer.valueOf(obj.toString());
            Integer total = 0;//循环次数
            if (count > 0) {
                if (count % 1000 == 0) {//1000的整数倍
                    total = count / 1000;
                } else if (count % 1000 == count) {//不足一千
                    total = 1;
                } else if (count % 1000 != 0 && count % 1000 < 1000) {
                    total = count / 1000 + 1;
                }
                personnelFlowService.truncatePersonnelFlow();
                for (int i = 1; i <= total; i++) {
                    page = (i - 1) * 1000;
                    searchUrl = searchUrl.substring(0, searchUrl.indexOf("=") + 1) + page + searchUrl.substring(searchUrl.indexOf("&$top"));
                    Object data = HttpUtil.doGetRequest(searchUrl);
                    JSONObject jsonObject = JSONObject.parseObject(data.toString());
                    List<JobApplication> list = paseJson(jsonObject);
                    if (!list.isEmpty()) {
                        personnelFlowService.createDate(list);
                    }
                }
            }
        }
    }

    public List<JobApplication> paseJson(JSONObject jsonObject) {
        List<JobApplication> list = new LinkedList<>();
        if (jsonObject != null) {
            if (jsonObject.getJSONObject("d") != null) {
                JSONArray array = jsonObject.getJSONObject("d").getJSONArray("results");
                if (!array.isEmpty()) {
                    for (Object obj : array) {
                        JobApplication application = new JobApplication();
                        JSONObject json = JSONObject.parseObject(obj.toString());
                        JSONObject candidate = json.getJSONObject("candidate");
                        String userFullName = "";
                        String jobNumber = "";
                        if (candidate != null) {
                            application.setCandidateId(Integer.valueOf(candidate.getString("candidateId")));//候选人id
                            userFullName = candidate.getString("lastName") + candidate.getString("firstName");
                            application.setCandidateName(userFullName);//候选人姓名
                            if (StringUtils.isNotBlank(candidate.getString("nationalidcardmun")) && candidate.getString("nationalidcardmun").length() <= 255) {
                                application.setNationalidcardmun(candidate.getString("nationalidcardmun"));//候选人身份证号
                                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                                Matcher m = p.matcher(candidate.getString("nationalidcardmun"));
                                if (!m.find() && RegUtils.identificationCardVerify(candidate.getString("nationalidcardmun"))) {
                                    Map param = new HashMap();
                                    param.put("cardId", candidate.getString("nationalidcardmun"));
                                    try {
                                        JSONObject jsonCan = restTemplate.postForObject("http://bgs.hanergy.com/admin/api/v1/user/query", param, JSONObject.class);
                                        if (jsonCan.getJSONObject("data") != null) {
                                            jobNumber = jsonCan.getJSONObject("data").getString("jobNumber");
                                            application.setPositionNumber(jobNumber);//候选人工号
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        application.setPositionNumber("");
                                    }
                                }
                            }
                        }
                        String jobDate = "";
                        JSONObject jobOffer = json.getJSONObject("jobOffer");
                        if (jobOffer != null) {
                            JSONArray offerArray = jobOffer.getJSONArray("results");
                            if (!offerArray.isEmpty()) {
                                jobOffer = offerArray.getJSONObject(0);
                                jobDate = jobOffer.getString("jobStartDate");
                                if (StringUtils.isNotBlank(jobDate)){
                                    jobDate = longToStringTime(jobDate);
                                }
                                application.setExplanJobDate(jobDate);//预计入职时间
                            }
                        }
                        JSONObject jobApplicationStatusAuditTrail = json.getJSONObject("jobApplicationStatusAuditTrail");
                        if (jobApplicationStatusAuditTrail != null) {
                            JSONArray statuArray = jobApplicationStatusAuditTrail.getJSONArray("results");
                            if (!statuArray.isEmpty()) {
                                for (Object object : statuArray) {
                                    JSONObject trail = JSONObject.parseObject(object.toString());
                                    JSONObject jobAppStatus = trail.getJSONObject("jobAppStatus");
                                    if (jobAppStatus != null) {
                                        String lastModifiedDateTime = trail.getString("lastModifiedDateTime");
                                        if (StringUtils.isNotBlank(lastModifiedDateTime)) {
                                            lastModifiedDateTime = longToStringTime(lastModifiedDateTime);
                                            String appStatusSetId = jobAppStatus.getString("appStatusSetId");
                                            if (StringUtils.isNotBlank(appStatusSetId)) {
                                                if (appStatusSetId.equals("183")) {
                                                    application.setTalkSalaryDate(lastModifiedDateTime);//简历时间
                                                } else if (appStatusSetId.equals("219")) {
                                                    application.setOfferDate(lastModifiedDateTime);//offer时间
                                                    try {
                                                        if (StringUtils.isNotBlank(application.getTalkSalaryDate()) && StringUtils.isNotBlank(application.getOfferDate())) {
                                                            String offerDate = application.getOfferDate().substring(0, 10).replaceAll("-", "");
                                                            String talkSalaryDate = application.getTalkSalaryDate().substring(0, 10).replaceAll("-", "");
                                                            if (StringUtils.isNotBlank(jobNumber)) {
                                                                JSONObject user = restTemplate.getForObject("http://bgs.hanergy.com/admin/api/v1/user/getUserListByCondition?userKey=job_Number&eqOrLike=T1&userValue=" + jobNumber, JSONObject.class);
                                                                JSONArray source = user.getJSONObject("data").getJSONObject("userList").getJSONArray("list");
                                                                if (!source.isEmpty()) {
                                                                    for (Object userobj : source) {
                                                                        ObjectMapper mapper = new ObjectMapper();
                                                                        JSONObject userJson = mapper.convertValue(userobj, JSONObject.class);
                                                                        String bigda = userJson.getString("bigda");
                                                                        if (Long.valueOf(bigda) > Long.valueOf(offerDate) && Long.valueOf(bigda) > Long.valueOf(talkSalaryDate)) {
                                                                            bigda = bigda.substring(0, 4) + "-" + bigda.substring(4, 6) + "-" + bigda.substring(6, 8);
                                                                            application.setJobDate(bigda);//入职时间
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                if (StringUtils.isNotBlank(jobDate)) {
                                                                    SimpleDateFormat format1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                                    SimpleDateFormat format2 =new SimpleDateFormat("yyyy-MM-dd");
                                                                    Date jDate=format1.parse(jobDate);
                                                                    JSONObject user = restTemplate.getForObject("http://bgs.hanergy.com/admin/api/v1/user/getUserListByCondition?userKey=name&eqOrLike=T1&userValue=" + userFullName, JSONObject.class);
                                                                    JSONArray source = user.getJSONObject("data").getJSONObject("userList").getJSONArray("list");
                                                                    if (!source.isEmpty()) {
                                                                        for (Object userobj : source) {
                                                                            ObjectMapper mapper = new ObjectMapper();
                                                                            JSONObject userJson = mapper.convertValue(userobj, JSONObject.class);
                                                                            String bigda = userJson.getString("bigda");
                                                                            if (Long.valueOf(bigda) > Long.valueOf(offerDate) && Long.valueOf(bigda) > Long.valueOf(talkSalaryDate)) {
                                                                                bigda = bigda.substring(0, 4) + "-" + bigda.substring(4, 6) + "-" + bigda.substring(6, 8);
                                                                                Date bdate=format2.parse(bigda);
                                                                                if ((bdate.getTime()-jDate.getTime())/(24*3600*1000)<=30){
                                                                                    application.setJobDate(bigda);//入职时间
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        application.setJobDate("");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        application.setLeaderShip("");//分管领导 todo
                        application.setResumeSource(json.getString("Dayeesouce"));//简历来源
                        JSONObject jobRequisition = json.getJSONObject("jobRequisition");
                        if (jobRequisition != null) {
                            JSONObject approver = jobRequisition.getJSONObject("approver");
                            if (approver != null) {
                                JSONArray approverArray = approver.getJSONArray("results");
                                if (!approverArray.isEmpty()) {
                                    approver = approverArray.getJSONObject(0);
                                    application.setApprover(approver.getString("lastName") + approver.getString("firstName"));//负责人
                                }
                            }
                            JSONObject recruiter = jobRequisition.getJSONObject("recruiter");
                            if (recruiter != null) {
                                JSONArray recruiterArray = recruiter.getJSONArray("results");
                                if (!recruiterArray.isEmpty()) {
                                    recruiter = recruiterArray.getJSONObject(0);
                                    application.setRecruiter(recruiter.getString("lastName") + recruiter.getString("firstName"));//应聘负责人
                                }
                            }
                            if (!jobRequisition.getString("buoncode").equals("()")) {//脏数据
                                application.setBuoncode(jobRequisition.getString("buoncode"));//应聘的集团
                            }
                            if (!jobRequisition.getString("divicode").equals("()")) {//脏数据
                                application.setDivicode(jobRequisition.getString("divicode"));//应聘的部门/事业部
                            }
                            application.setLegalEntitycode(jobRequisition.getString("legalEntitycode"));//法人实体
                            application.setLocationcode(jobRequisition.getString("locationcode"));//工作地点
                            JSONObject hiringManager = jobRequisition.getJSONObject("hiringManager");
                            if (hiringManager != null) {
                                JSONArray hirArray = hiringManager.getJSONArray("results");
                                if (!hirArray.isEmpty()) {
                                    hiringManager = hirArray.getJSONObject(0);
                                    application.setHiringManager(hiringManager.getString("lastName") + hiringManager.getString("firstName"));//入职后汇报领导
                                }
                            }
                            JSONObject jobReqLocale = jobRequisition.getJSONObject("jobReqLocale");
                            if (jobReqLocale != null) {
                                if (!jobReqLocale.getJSONArray("results").isEmpty()) {
                                    application.setJobTitle(jobReqLocale.getJSONArray("results").getJSONObject(0).getString("jobTitle"));//申请职位
                                }
                            }
                            JSONObject jobLevel = jobRequisition.getJSONObject("jobLevel");
                            if (jobLevel != null) {
                                if (!jobLevel.getJSONArray("results").isEmpty()) {
                                    JSONObject picklistLabels = jobLevel.getJSONArray("results").getJSONObject(0).getJSONObject("picklistLabels");
                                    if (picklistLabels != null) {
                                        if (!picklistLabels.getJSONArray("results").isEmpty()) {
                                            JSONObject lable = picklistLabels.getJSONArray("results").getJSONObject(1);
                                            if (lable != null) {
                                                application.setJobLevel(lable.getString("label"));//推荐职级
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        list.add(application);
                    }
                }
            }
        }
        return list;
    }

    public static String longToStringTime(String time) {
        time = time.substring(time.indexOf("(") + 1, time.indexOf("+"));
        Long timestamp = Long.valueOf(time);
        return DateUtils.dateToString(new Date(timestamp), DateUtils.PATTERN_YYYYMMDDHHMMSS);
    }

}

