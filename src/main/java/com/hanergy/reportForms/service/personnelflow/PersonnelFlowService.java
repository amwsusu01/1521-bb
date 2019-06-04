package com.hanergy.reportForms.service.personnelflow;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.mapper.PersonnelFlowMapper;
import com.hanergy.reportForms.mapper.entity.JobApplication;
import com.hanergy.reportForms.mapper.entity.UrlData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonnelFlowService {
    @Autowired
    PersonnelFlowMapper personnelFlowMapper;

    public JSONObject list(JobApplication jobApplication) {
        JSONObject result = new JSONObject();
        result.put("data", personnelFlowMapper.findByParam(jobApplication));
        result.put("count", personnelFlowMapper.countJobApplication(jobApplication));
        return result;
    }

    public void personnelFlowExport(JobApplication jobApplication, HttpServletResponse response) {
        List list = personnelFlowMapper.findByParam(jobApplication);
        ReturnExcel.personnelFlowExport(response, list);
    }
    public void export1521bakByCreateDate(HttpServletResponse response){
        List list = personnelFlowMapper.find1521bakByCreateDate();
        ReturnExcel.personnelFlowExport(response, list);
    }
    public JSONObject findQueryParam(JobApplication jobApplication) {
        JSONObject result = new JSONObject();
        List<String> approverList = new ArrayList<>();
        List<String> recruiterList = new ArrayList<>();
        List<String> hrvpList = new ArrayList<>();
        List<String> buoncodeList = new ArrayList<>();
        List<String> divicodeList = new ArrayList<>();
        List<String> busUnitList = new ArrayList<>();
        List<Map<String, String>> list = personnelFlowMapper.findQueryParam(jobApplication);
        if (!list.isEmpty()) {
            for (Map<String, String> map : list) {
                Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key.equals("approver") && StringUtils.isNotBlank(value)) {
                        approverList.add(value);
                    }
                    if (key.equals("recruiter") && StringUtils.isNotBlank(value)) {
                        if (value.indexOf(",") > 0) {
                            String[] recruiterArray = value.split(",");
                            for (String recruiter : recruiterArray) {
                                if (!recruiterList.contains(recruiter)) {
                                    recruiterList.add(recruiter);
                                }
                            }
                        } else {
                            if (!recruiterList.contains(value)) {
                                recruiterList.add(value);
                            }
                        }
                    }
                    if (key.equals("hrvp") && StringUtils.isNotBlank(value)) {
                        if (value.indexOf(",") > 0) {
                            String[] hrvpArray = value.split(",");
                            for (String hrvp : hrvpArray) {
                                if (!hrvpList.contains(hrvp)) {
                                    hrvpList.add(hrvp);
                                }
                            }
                        } else {
                            if (!hrvpList.contains(value)) {
                                hrvpList.add(value);
                            }
                        }
                    }
                    if (key.equals("buoncode") && StringUtils.isNotBlank(value)) {
                        if (value.indexOf(",") > 0) {
                            String[] buoncodeArray = value.split(",");
                            for (String buoncode : buoncodeArray) {
                                if (!buoncodeList.contains(buoncode)) {
                                    buoncodeList.add(buoncode);
                                }
                            }
                        } else {
                            if (!buoncodeList.contains(value)) {
                                buoncodeList.add(value);
                            }
                        }
                    }
                    if (key.equals("divicode") && StringUtils.isNotBlank(value)) {
                        if (value.indexOf(",") > 0) {
                            String[] divicodeArray = value.split(",");
                            for (String divicode : divicodeArray) {
                                if (!divicodeList.contains(divicode)) {
                                    divicodeList.add(divicode);
                                }
                            }
                        } else {
                            if (!divicodeList.contains(value)) {
                                divicodeList.add(value);
                            }
                        }
                    }
                    if (key.equals("busUnit") && StringUtils.isNotBlank(value)) {
                        if (value.indexOf(",") > 0) {
                            String[] busUnitArray = value.split(",");
                            for (String busUnit : busUnitArray) {
                                if (!busUnitList.contains(busUnit)) {
                                    busUnitList.add(busUnit);
                                }
                            }
                        } else {
                            if (!busUnitList.contains(value)) {
                                busUnitList.add(value);
                            }
                        }
                    }
                }
            }
        }
        result.put("approverList", approverList);
        result.put("recruiterList", recruiterList);
        result.put("hrvpList", hrvpList);
        result.put("buoncodeList", buoncodeList);
        result.put("divicodeList", divicodeList);
        result.put("busUnitList", busUnitList);
        return result;
    }

    public String findLastModifiedDateTime() {
        return personnelFlowMapper.findLastModifiedDateTime();
    }

    public void truncatePersonnelFlow(){
        personnelFlowMapper.truncatePersonnelFlow();
    }
    public void createDate(List<JobApplication> list) {
        personnelFlowMapper.createPersonnelFlowBak(list);//先备份一份
        personnelFlowMapper.createPersonnelFlow(list);//写数据
    }

    public Integer queryData() throws Exception {
        List<UrlData> list = personnelFlowMapper.queryData();
        for (UrlData data : list) {
            data.setContent(URLDecoder.decode(data.getContent(), "utf8"));
        }
        return personnelFlowMapper.createUrlDate(list);
    }

}
