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
    public static final String REALTIME_EXAM_NOTIFICATION_EMAIL_TEMPLATE = "org/realTimeExamNotification.vm";
    public static final String REALTIME_EXAM_CANCELLATION_EMAIL_TEMPLATE = "org/realTimeExamCancellationNotification.vm";
    public static final String SUBSCRIPTION_NOTIFICATION_EMAIL_TEMPLATE = "org/subscriptionPackageNotification.vm";
    public static final String SUBSCRIPTION_UPGRADE_EMAIL_TEMPLATE = "org/subscriptionUpgradedPackageNotification.vm";
    public static final String PLAN_VALIDITY_EXTENDED_NOTIFICATION_EMAIL_TEMPLATE = "org/packagePlanValidityExtendedNotification.vm";
    public static final String TRIAL_PLAN_PURCHASE_NOTIFICATION_EMAIL_TEMPLATE = "org/trialPlanPurchaseNotification.vm";
    public static final String MULTI_PLAN_PURCHASE_NOTIFICATION_EMAIL = "org/multiplePlanPurchaseNotification.vm";
    public static final String ENTRY_PLAN_UPGRADE_EMAIL = "org/entryLevelPlanUpgradeNotification.vm";
    public static final String LAST_DAY_SUBSCRIPTION_EXPIRY_WARNING_EMAIL = "org/subscriptionExpiryLastDayWarning.vm";
    public static final String TWO_WEEKS_BEFORE_SUBSCRIPTION_EXPIRY_WARNING_EMAIL = "org/subscriptionExpiryInTwoWeeksWarning.vm";
    public static final String PLAN_RESET_NOTIFICATION_EMAIL = "org/planResetNotification.vm";
    public static final String STUDENT_ENQUIRY_ASSIGNED_NOTIFICATION_EMAIL = "org/studentEnquiryAssignedToFacultyNotification.vm";
    public static final String STUDENT_COUPON_CREATED_EMAIL = "org/studentCouponCreationNotification.vm";
    public static final String ADMIN_REGISTERED_NOTIFICATION_EMAIL = "org/adminregistration.vm";
    public static final String FREELANCER_REGISTRATION_EMAIL = "org/freeLancerRegistration.vm";
    public static final String FREELANCER_VERIFY_EMAIL = "org/verifyfreelancer.vm";
    public static final String STUDENT_VERIFICATION_EMAIL = "org/emailUserLoginDetails.vm";
    public static final String SPECIALIST_REGISTRATION_EMAIL = "org/subjectSpecialistLoginDetails.vm";
    public static final String EXPERT_REGISTRATION_EMAIL = "org/subjectExpertLoginDetails.vm";


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

    public Map<String, Object> prepareEligibleStudentsForRealTimeContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, REALTIME_EXAM_NOTIFICATION_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Real-time Test Scheduled");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }
    public Map<String, Object> prepareEligibleStudentsForRealTimeExamCancellationContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, REALTIME_EXAM_CANCELLATION_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Real-time test cancelled");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareSubscriptionPlanPackageForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, SUBSCRIPTION_NOTIFICATION_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Your package details");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareUpgradedSubscriptionPlanPackageForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        Map map = new HashMap();
        map.put("email", mailSenderInfoMap.get("email"));
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, SUBSCRIPTION_UPGRADE_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Upgrade details - entrancebook");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareSubscriptionPlanPackageForStudentPlanValidityExtendedContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, PLAN_VALIDITY_EXTENDED_NOTIFICATION_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Your package validity extended");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareStudentTrailPlanUpgradedContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TRIAL_PLAN_PURCHASE_NOTIFICATION_EMAIL_TEMPLATE, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Your trial package is activated");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareSubscriptionForMultiplePackageForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, MULTI_PLAN_PURCHASE_NOTIFICATION_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Package details - entrancebook");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareEntryLevelPlanUpgradedForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,ENTRY_PLAN_UPGRADE_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Package details - entrancebook");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareLastDaySubscriptionExpiryWarningForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap)
    {
        //${firstName}, ${studentPlanPhase}, ${planExpiresToday}, ${planLabel} and ${planEndDate}
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, LAST_DAY_SUBSCRIPTION_EXPIRY_WARNING_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Subscription Expires TODAY !!");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareTwoWeeksBeforeSubscriptionExpiryWarningForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap)
    {
        //${firstName}, ${studentPlanPhase}, ${planExpiresToday}, ${planLabel} and ${planEndDate}
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TWO_WEEKS_BEFORE_SUBSCRIPTION_EXPIRY_WARNING_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Subscription Expires in 2 weeks");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> preparePlanResetForStudentsContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        //${firstName}
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, PLAN_RESET_NOTIFICATION_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", "Plan reset successful");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }

    public Map<String, Object> prepareStudentEnquiryAssignedToFacultyContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        Map map = new HashMap();
        map.put("email", mailSenderInfoMap.get("email"));
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, STUDENT_ENQUIRY_ASSIGNED_NOTIFICATION_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", " Student's enquiry on entrancebook");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }
    public Map<String, Object> prepareStudentCouponContentAndSendMail(Map<String, Object> mailSenderInfoMap) {
        Map map = new HashMap();
        map.put("email", mailSenderInfoMap.get("email"));
        String messageText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, STUDENT_COUPON_CREATED_EMAIL, mailSenderInfoMap);
        mailSenderInfoMap.put("subject", " Your Coupon");
        mailSenderInfoMap.put("messageBody", messageText);
        String recipientMailAddress = (String) mailSenderInfoMap.get("email");
        logger.debug(messageText);
        Map resultMap = sendMail(mailSenderInfoMap, recipientMailAddress);
        return resultMap;
    }


}

