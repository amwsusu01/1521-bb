package com.hanergy.reportForms.commons.utils.sfsys;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName SFUtils
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/26 16:32
 * @Version 1.0
 **/
public class SFUtils {


    private static Logger logger = LoggerFactory.getLogger(SFUtils.class);


    private static String SF_USER_NAME = "PS_Admin@hanergyhol";
    private static String SF_PASSWORD = "1LheMlrEmam?5l";

    // 获取招聘系统人员名单(生产可用)
    private static String GET_RECRUITLEADER_URL = "https://api15.sapsf.cn/odata/v2/getUsersByDynamicGroup?groupId=2901l&$format=json";
    // 获取单个候选人接口(主要用于测试)
    private static String SINGLE_CANDIDATE_INFO_URL = "https://api15.sapsf.cn/odata/v2/JobApplication?$format=json&$filter=jobRequisition/jobReqId%20eq%20%2720163%27%20&$expand=candidate,education,jobRequisition,jobRequisition/recruiter,jobRequisition/department_obj,jobRequisition/jobReqLocale,outsideWorkExperience&$select=applicationId,candidate/candidateId,lastName,firstName,dateOfBirth,gender,cellPhone,contactEmail,jobRequisition/recruiter/userName,jobRequisition/recruiter/lastName,jobRequisition/recruiter/firstName,jobRequisition/department_obj/externalCode,jobRequisition/department_obj/description,jobRequisition/positionNumber,jobRequisition/joblevelmax,jobRequisition/joblevelmin,jobRequisition/jobReqLocale/jobTitle,outsideWorkExperience/startDate,outsideWorkExperience/startTitle,outsideWorkExperience/employer,outsideWorkExperience/businessType,outsideWorkExperience/endDate,education";
    // 批量获取,用于生产
    public static String CANDIDATE_INFO_URL = "https://api15.sapsf.cn/odata/v2/JobApplication?$format=json&$filter=jobAppStatus/appStatusId%20eq%20%27218%27&$expand=candidate,education,jobRequisition,jobRequisition/recruiter,jobRequisition/department_obj,jobRequisition/jobReqLocale,outsideWorkExperience&$select=applicationId,candidate/candidateId,lastName,firstName,dateOfBirth,gender,cellPhone,contactEmail,jobRequisition/recruiter/userName,jobRequisition/recruiter/lastName,jobRequisition/recruiter/firstName,jobRequisition/department_obj/externalCode,jobRequisition/department_obj/description,jobRequisition/positionNumber,jobRequisition/joblevelmax,jobRequisition/joblevelmin,jobRequisition/jobReqLocale/jobTitle,outsideWorkExperience/startDate,outsideWorkExperience/startTitle,outsideWorkExperience/employer,outsideWorkExperience/businessType,outsideWorkExperience/endDate,education";

    // 获取SF系统中处于背调状态的候选人数量
    public static String CANDIDATE_INFO_TOTAL = "https://api15.sapsf.cn/odata/v2/JobApplication/$count?$format=json&$filter=jobAppStatus/appStatusId eq '218' &$expand=candidate,education,jobRequisition,jobRequisition/recruiter,jobRequisition/department_obj,jobRequisition/jobReqLocale,outsideWorkExperience&$select=candidate/candidateId,lastName,firstName,dateOfBirth,gender,cellPhone,contactEmail,jobRequisition/recruiter/userName,jobRequisition/recruiter/lastName,jobRequisition/recruiter/firstName,jobRequisition/department_obj/externalCode,jobRequisition/department_obj/description,jobRequisition/positionNumber,jobRequisition/joblevelmax,jobRequisition/joblevelmin,jobRequisition/jobReqLocale/jobTitle,outsideWorkExperience/startDate,outsideWorkExperience/startTitle,outsideWorkExperience/employer,outsideWorkExperience/businessType,outsideWorkExperience/endDate,education";
    // 背调完成后,回传给SF系统最后的背调结果 POST请求
    public static String RETURN_CANDIDATE_BACKGROUND_STATUS_URL = "https://api15.sapsf.cn/odata/v2/upsert?purgeType=incremental";

    public static String getResult(String serviceUrl, Map<String, Object> requestParams) {
        BufferedReader in = null;
        String result = "";
        try {
            URL url = new URL(serviceUrl);
            // 登陆
            authenticator();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Charset", "UTF-8");

            // 设置参数
            if (requestParams != null && requestParams.size() > 0) {
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(JSONObject.toJSONString(requestParams));
                outputStream.flush();
                outputStream.close();
            }

            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static void authenticator() {
        Authenticator authenticator = new MyAuthenticator(SF_USER_NAME, SF_PASSWORD);
        Authenticator.setDefault(authenticator);
    }

    public static String singleCandidateInfo() {
        return getResult(SINGLE_CANDIDATE_INFO_URL, null);
    }

    public static String moreCandidateInfos() {
        return moreCandidateInfos(CANDIDATE_INFO_URL);
    }

    public static String moreCandidateInfos(String url) {
        return getResult(url, null);
    }

    /**
     * 获取SF系统处于背调状态的候选人的总数量
     *
     * @return
     */
    public static Integer getCandidateCounts() {
        String result = getResult(CANDIDATE_INFO_TOTAL, null);
        return StringUtils.isNotEmpty(result) ? Integer.parseInt(result) : 0;
    }


    public static String sendPostRequest(String url, String param) {
        HttpURLConnection httpURLConnection = null;
        OutputStream out = null; //写
        InputStream in = null;   //读
        int responseCode = 0;    //远程主机响应的HTTP状态码
        String result = "";
        try {
            URL sendUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) sendUrl.openConnection();
            //post方式请求
            httpURLConnection.setRequestMethod("POST");
            //一定要设置 Content-Type 要不然服务端接收不到参数
            httpURLConnection.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            //指示应用程序要将数据写入URL连接,其值默认为false（是否传参）
            httpURLConnection.setDoOutput(true);
            //httpURLConnection.setDoInput(true);

            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(30000); //30秒连接超时
            httpURLConnection.setReadTimeout(30000);    //30秒读取超时
            //传入参数
            out = httpURLConnection.getOutputStream();
            out.write(param.getBytes());
            out.flush(); //清空缓冲区,发送数据
            out.close();
            responseCode = httpURLConnection.getResponseCode();
            logger.info(DateUtils.defaultDateToString(new Date()) + "=====>>>[候选人背调完成,结果回传SF系统]===>>响应状态===>>>" + responseCode);
            //获取请求的资源
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            result = br.readLine();
            logger.info(DateUtils.defaultDateToString(new Date()) + "=====>>>[候选人背调完成,结果回传SF系统]===>>响应结果===>>>" + result);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;

    }

}
