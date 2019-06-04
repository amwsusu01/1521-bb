package com.hanergy.reportForms.commons.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static String userName = "PS_Admin@hanergyhol";
    private static String password = "1LheMlrEmam?5l";

    /**
     * 获取客户端网络ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static Object doGetRequest(String url) {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        String userPass = userName + ":" + password;
        String base64EncodedPassword = DatatypeConverter
                .printBase64Binary(userPass.getBytes(StandardCharsets.UTF_8));
        //添加http头信息
        httpGet.addHeader("Authorization", "Basic " + base64EncodedPassword);
        String result = "";
        HttpResponse httpResponse = null;
        HttpEntity entity = null;
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                if (result.contains(":")) {
                    return JSONObject.parseObject(result);
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
