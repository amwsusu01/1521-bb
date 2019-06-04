package com.hanergy.reportForms.service.impl;

import com.hanergy.reportForms.service.IMailService;

import freemarker.template.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@Service
public class IMailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMailTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSentDate(new Date());
        message.setFrom("bgs_admin@hanergy.com");
        message.setTo(new String[]{"drh0534@163.com"});
        message.setSubject("测试发送邮件");
        message.setText("Hello!This is a test mail");
        try {
            javaMailSender.send(message);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

//    @Override
//    public void sendMailWithAttachments(String[] toArray, String[] attachArray) throws Exception {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setFrom(from);
//        helper.setTo(toArray);
//        helper.setSubject("主题:有附件");
//        helper.setText("测试邮件");

//        FileSystemResource fileSystemResource = new FileSystemResource(new File("磁盘路径"));
//        int index = 1;
//        for (String fileNetUrl : attachArray) {
//            URL url = new URL(fileNetUrl);
//            FileUrlResource fileUrlResource = new FileUrlResource(url);
//            helper.addAttachment("附件-" + (index++), fileUrlResource);
//        }
//
//        javaMailSender.send(mimeMessage);
//    }

    @Override
    public void sendTemplateEmail(
            String subject, String[] sendToArray,
            String templateName, Map<String, Object> model,
            Map<String, String> attachmentMap, Boolean localFile
    ) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from + "@hanergy.com");
        helper.setSubject(subject);
        helper.setTo(sendToArray);

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(text, true);

        /**
         * 添加附件
         */
        if (attachmentMap != null && attachmentMap.size() > 0) {
            if (localFile) {
                for (String fileName : attachmentMap.keySet()) {
                    FileSystemResource systemResource = new FileSystemResource(new File(attachmentMap.get(fileName)));
                    helper.addAttachment(fileName, systemResource);
                }
            } else {
                for (String fileName : attachmentMap.keySet()) {
                    FileUrlResource urlResource = new FileUrlResource(new URL(attachmentMap.get(fileName)));
                    helper.addAttachment(fileName, urlResource);
                }
            }
        }
        javaMailSender.send(mimeMessage);

    }
}
