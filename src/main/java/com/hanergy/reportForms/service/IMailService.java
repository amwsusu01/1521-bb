package com.hanergy.reportForms.service;

import javax.mail.MessagingException;
import java.util.Map;

public interface IMailService {

    void sendMailTest();

    /**
     * 发送带有附件的邮件
     *
     * @param toArray     收件人地址列表
     * @param attachArray 附件列表
     * @throws Exception
     */
//    void sendMailWithAttachments(String[] toArray, String[] attachArray) throws Exception;

    /**
     * 发送带模板的邮件
     *
     * @param subject       邮件主题
     * @param sendToArray   接收者
     * @param templateName  模板名称 格式: *.ftl
     * @param model         数据
     * @param attachmentMap 附件清单 key-附件名称,value-附件路径
     * @param localFile     是否是本地文件,true-本地磁盘文件,false-文件的网络地址
     */
    void sendTemplateEmail(String subject, String[] sendToArray, String templateName, Map<String, Object> model,
                           Map<String, String> attachmentMap, Boolean localFile) throws MessagingException, Exception;

}
