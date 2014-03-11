package org.nthdimenzion.security.application.services;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class MailService {

    // Email Templates

    public static final String FORGOT_PASSWORD_DETAILS_EMAIL_TEMPLATE = "org/forgotPasswordDetails.vm";
    public static final String STUDENT_VERIFICATION_EMAIL = "org/emailUserLoginDetails.vm";


    @Autowired
    private final JavaMailSenderImpl javaMailSender;

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private IEncryportDecryptor iEncryportDecryptor;

    private Properties mailSettingProperties;

    private ClassLoader bundleClassLoader = Thread.currentThread().getContextClassLoader();

    public MailService(JavaMailSenderImpl javaMailSender) throws IOException {
        this.javaMailSender = javaMailSender;
        mailSettingProperties = new Properties();
        mailSettingProperties.load(bundleClassLoader.getResourceAsStream("mailSettings.properties"));
    }

    public Map<String, Object> sendMail(final Map model, final String recipientMailAddress) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                Properties mailCredentialProperties = new Properties();
                mailCredentialProperties.load(bundleClassLoader.getResourceAsStream("mailCredentials.properties"));
                javaMailSender.setUsername(mailCredentialProperties.getProperty("userName"));
                javaMailSender.setPassword(mailCredentialProperties.getProperty("password"));
                javaMailSender.setJavaMailProperties(mailSettingProperties);
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
                message.setTo(recipientMailAddress);
                message.setFrom(mailCredentialProperties.getProperty("sendFrom"));
                message.setSubject((String) model.get("subject"));
                String messageBody = (String) model.get("messageBody");
                message.setText(messageBody, true);
                logger.debug(messageBody);
                resultMap.put("subject", model.get("subject"));
                resultMap.put("recipientMailAddress", recipientMailAddress);
                resultMap.put("messageBody", messageBody);
                resultMap.put("userName", model.get("userName"));
            }
        };
        javaMailSender.send(preparator);
        return resultMap;
    }

    public Map<String, Object> prepareVerificationMailContentAndSendMail(Map<String, Object> mailSenderInfoMap,String templateFileNameWithPath) {
        String verificationCode = EncryptionUtil.encrypt(iEncryportDecryptor,(String) mailSenderInfoMap.get("userName"));
        String verificationUrl = mailSettingProperties.getProperty("verficationUrl").trim();
        verificationUrl = verificationUrl.concat(verificationCode);
        mailSenderInfoMap.put("verficationUrl", verificationUrl);
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFileNameWithPath, mailSenderInfoMap);
        String mailSubject = mailSettingProperties.getProperty("verificationMailSubject");
        mailSenderInfoMap.put("subject", mailSubject);
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("recipientMailAddress");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String,Object> prepareRegistrationAccountDetailsMailContentAndSendMail(Map<String, Object> mailSenderInfoMap, String templateFileNameWithPath){
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFileNameWithPath, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", mailSettingProperties.getProperty("accountDetailsMailSubject"));
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("recipientMailAddress");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareNewPasswordContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String verificationUrl = mailSettingProperties.getProperty("forgotPasswordVerficationUrl").trim();
        mailSenderInfoMap.put("verficationUrl", verificationUrl);
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, FORGOT_PASSWORD_DETAILS_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "New Password");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("recipientMailAddress");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

}

