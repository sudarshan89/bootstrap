/*
SQLyog Community v10.2 
MySQL - 5.1.51-community : Database - macula
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `abusive_words` */

DROP TABLE IF EXISTS `abusive_words`;

CREATE TABLE `abusive_words` (
  `ID` bigint(20) NOT NULL,
  `word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

/*Table structure for table `answer_sheet` */

DROP TABLE IF EXISTS `answer_sheet`;

CREATE TABLE `answer_sheet` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
);

/*Table structure for table `answersheet_answers` */

DROP TABLE IF EXISTS `answersheet_answers`;

CREATE TABLE `answersheet_answers` (
  `ANSWERSHEET` bigint(20) NOT NULL,
  `ANSWERS` varchar(255) DEFAULT NULL,
  `PHASE` varchar(255) NOT NULL DEFAULT '',
  `QUESTION_ID` bigint(20) NOT NULL DEFAULT '0',
  `MARKED_FOR_REVIEW` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ANSWERSHEET`,`PHASE`,`QUESTION_ID`),
  UNIQUE KEY `unique_questions_in_answersheet` (`ANSWERSHEET`,`QUESTION_ID`),
  KEY `FK20B7F257AB8260C` (`ANSWERSHEET`)

);

/*Table structure for table `coupon` */

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `COUPON_CODE` varchar(255) NOT NULL,
  `ASSIGNED_TO_STUDENT_ID` varchar(255) default NULL,
  `COUPON_STATUS` varchar(255) NOT NULL,
  `COUPON_VALUE` decimal(19,2) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `EXPIRY_DATE` date NOT NULL,
  `FOR_MULTIPLE_USER` tinyint(1) NOT NULL,
  `LOCKED_ON` datetime default NULL,
  `PAYMENT_ID` varchar(255) default NULL,
  `LOCKED_BY` varchar(255) default NULL,
  PRIMARY KEY  (`COUPON_CODE`)
);

/*Table structure for table `domain_event_entry` */

DROP TABLE IF EXISTS `domain_event_entry`;

CREATE TABLE `domain_event_entry` (
  `AGGREGATE_IDENTIFIER` varchar(255) NOT NULL,
  `SEQUENCE_NUMBER` bigint(20) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `EVENT_IDENTIFIER` varchar(255) NOT NULL,
  `META_DATA` longblob,
  `PAYLOAD` longblob NOT NULL,
  `PAYLOAD_REVISION` varchar(255) DEFAULT NULL,
  `PAYLOAD_TYPE` varchar(255) NOT NULL,
  `TIME_STAMP` varchar(255) NOT NULL,
  PRIMARY KEY (`AGGREGATE_IDENTIFIER`,`SEQUENCE_NUMBER`,`TYPE`)
);

/*Table structure for table `entrance_exam` */

DROP TABLE IF EXISTS `entrance_exam`;

CREATE TABLE `entrance_exam` (
  `EXAM_CODE` varchar(255) NOT NULL,
  `ACTUAL_EXAM_NAME` varchar(255) DEFAULT NULL,
  `EXAM_DURATION` varchar(255) NOT NULL,
  `EXAM_NAME` varchar(255) NOT NULL,
  `HTML_CONTENT` longtext,
  `IS_FOR_SUBSCRIBED_USER` varchar(255) DEFAULT NULL,
  `NEGATIVE_MARKS_PER_QUESTION` float NOT NULL,
  `NO_OF_QUESTIONS` int(11) NOT NULL,
  `POSITIVE_MARKS_PER_QUESTION` int(11) NOT NULL,
  `TOTAL_MARKS` int(11) NOT NULL,
  PRIMARY KEY (`EXAM_CODE`)
);

/*Table structure for table `enumeration` */

DROP TABLE IF EXISTS `enumeration`;

CREATE TABLE `enumeration` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ENUM_CODE` varchar(255) DEFAULT NULL,
  `ENUM_TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ENUM_CODE` (`ENUM_CODE`)
);

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `ID` varchar(255) NOT NULL,
  `LAST_EVENT_SEQUENCE_NUMBER` bigint(20) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `CREATED_ON` date NOT NULL,
  `EXAM_DURATION` varchar(255) DEFAULT NULL,
  `EXAM_TYPE` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `SCHEDULED_ON` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `ENTRANCEEXAM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK20B01F8637AC93` (`ENTRANCEEXAM`)

);

/*Table structure for table `exam_question_ids` */

DROP TABLE IF EXISTS `exam_question_ids`;

CREATE TABLE `exam_question_ids` (
  `EXAM` varchar(255) NOT NULL,
  `PHASE` varchar(255) DEFAULT NULL,
  `QUESTION_ID` bigint(20) DEFAULT NULL,
  KEY `FKAE836DF54E88204` (`EXAM`)

);

/*Table structure for table `faculty` */

DROP TABLE IF EXISTS `faculty`;

CREATE TABLE `faculty` (
  `DESIGNATION` varchar(255) DEFAULT NULL,
  `INSTITUTE_ID` bigint(20) DEFAULT NULL,
  `IS_FREE_LANCER` tinyint(1) DEFAULT NULL,
  `MOBILE_NUMBER` varchar(255) DEFAULT NULL,
  `SPECIALISATION` varchar(255) DEFAULT NULL,
  `SUBJECT_CODE` varchar(255) DEFAULT NULL,
  `YEARS_OF_EXPERIENCE` int(11) DEFAULT NULL,
  `ID` varchar(255) NOT NULL,
  `VERIFIED` tinyint(1) default NULL,
   PRIMARY KEY (`ID`),
   KEY `FKE9B72644C5BA4AA4` (`ID`)

);


/*Table structure for table `infomercial` */

DROP TABLE IF EXISTS `infomercial`;

CREATE TABLE `infomercial` (
  `ID` varchar(255) NOT NULL,
  `DEFAULT_INFOMERCIAL` tinyint(1) NOT NULL default '0',
  `DISPLAY_ON` date default NULL,
  `FACE_BOOK_FILE` longblob,
  `FACE_BOOK_FILE_CONTENT_TYPE` varchar(255) default NULL,
  `I_PAD_FILE` longblob,
  `I_PAD_FILE_CONTENT_TYPE` varchar(255) default NULL,
  `INFOMERCIAL_BANNER_TYPE` varchar(255) NOT NULL,
  `INFOMERCIAL_NAME` varchar(255) default NULL,
  `WEBSITE_FILE` longblob,
  `WEBSITE_FILE_CONTENT_TYPE` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
);

/*Table structure for table `infomercial_key_words` */

DROP TABLE IF EXISTS `infomercial_key_words`;

CREATE TABLE `infomercial_key_words` (
  `INFOMERCIAL` varchar(255) NOT NULL,
  `KEY_WORDS` varchar(255) default NULL,
  KEY `FK52B6D6279B791767` (`INFOMERCIAL`),
  CONSTRAINT `FK52B6D6279B791767` FOREIGN KEY (`INFOMERCIAL`) REFERENCES `infomercial` (`ID`)
) ;

/*Table structure for table `institutes` */

DROP TABLE IF EXISTS `institutes`;

CREATE TABLE `institutes` (
  `ID` bigint(10) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `INSTITUTE_NAME` varchar(255) NOT NULL,
  `DISTRICT` varchar(255) NOT NULL,
  `UNIVERSITY` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
);

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `ID` varchar(255) NOT NULL,
  `LAST_EVENT_SEQUENCE_NUMBER` bigint(20) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `AGENT_CODE` varchar(255) DEFAULT NULL,
  `AMOUNT` decimal(19,2) NOT NULL,
  `EXTERNAL_REFERENCE_ID` varchar(255) DEFAULT NULL,
  `PAYMENT_MODE` varchar(255) NOT NULL,
  `PAYMENT_STATUS` varchar(255) NOT NULL,
  `STUDENT_ID` varchar(255) DEFAULT NULL,
  `UPDATED_ON` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_payment` (`STUDENT_ID`)

);

/*Table structure for table `payment_payment_details` */

DROP TABLE IF EXISTS `payment_payment_details`;

CREATE TABLE `payment_payment_details` (
  `PAYMENT` varchar(255) NOT NULL,
  `AMOUNT` decimal(19,2) DEFAULT NULL,
  `PLAN_ID` bigint(20) DEFAULT NULL,
  KEY `FKD91724707A4C0448` (`PAYMENT`),
  KEY `FK_payment_payment_details` (`PLAN_ID`)


);

/*Table structure for table `person_role` */

DROP TABLE IF EXISTS `person_role`;

CREATE TABLE `person_role` (
  `ID` varchar(255) NOT NULL,
  `DOMAIN_ROLE` varchar(255) NOT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `MIDDLE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

/*Table structure for table `plan` */

DROP TABLE IF EXISTS `plan`;

CREATE TABLE `plan` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  `DISCOUNTED_PRICE` decimal(19,2) DEFAULT NULL,
  `DISCOUNTED_PRICE_ONE` decimal(19,2) DEFAULT NULL,
  `ENQUIRY_PERIOD` int(11) DEFAULT NULL,
  `ICON_URL` varchar(255) DEFAULT NULL,
  `LABEL` varchar(255) NOT NULL,
  `NO_OF_ENQUIRIES` int(11) DEFAULT NULL,
  `PHASE_STUDY_DURATION` int(11) DEFAULT NULL,
  `PLAN_DESCRIPTION` varchar(1024) DEFAULT NULL,
  `PLAN_DURATION` varchar(255) NOT NULL,
  `PLAN_FAMILY` varchar(255) NOT NULL,
  `PLAN_PHASE` varchar(255) NOT NULL,
  `PLAN_TYPE` varchar(255) NOT NULL,
  `PRICE` decimal(19,2) NOT NULL,
  `SORT_ORDER` int(11) DEFAULT NULL,
  `TOPUP_STUDY_DURATION` int(11) DEFAULT NULL,
  `ICON_URL_LINK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

/*Table structure for table `question_attachment` */

DROP TABLE IF EXISTS `question_attachment`;

CREATE TABLE `question_attachment` (
  `IMAGE_ID` varchar(255) NOT NULL,
  `IMAGE_NAME` varchar(255) DEFAULT NULL,
  `IMAGE_CONTENT` longblob,
  `CONTENT_TYPE` varchar(255) DEFAULT NULL,
  `QUESTION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IMAGE_ID`),
  KEY `fk_question_attachment` (`QUESTION_ID`)

);


DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `SUBJECT_CODE` varchar(255) NOT NULL,
  `SUBJECT_DESCRIPTION` varchar(255) NOT NULL,
  `PHASE` varchar(255) NOT NULL,
  PRIMARY KEY (`SUBJECT_CODE`)
);


DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `CHAPTER_ID` varchar(255) NOT NULL,
  `CHAPTER_NUMBER` bigint(255) default NULL,
  `CHAPTER_NAME` varchar(255) default NULL,
  `SUBJECT_CODE` varchar(255) default NULL,
  PRIMARY KEY  (`CHAPTER_ID`),
  KEY `FK_SUBJECT_CHAPTER` (`SUBJECT_CODE`)
);

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `TOPIC_ID` varchar(255) NOT NULL,
  `TOPIC_NAME` varchar(255) default NULL,
  `CHAPTER_ID` varchar(255) default NULL,
  PRIMARY KEY  (`TOPIC_ID`),
  KEY `FK_CHAPTER_TOPIC` (`CHAPTER_ID`)
);

/*Table structure for table `question_bank` */

DROP TABLE IF EXISTS `question_bank`;

CREATE TABLE `question_bank` (
  `ID` bigint(20) NOT NULL,
  `QUESTION_TEXT` longtext,
  `OPTION_ONE` longtext,
  `OPTION_TWO` longtext,
  `OPTION_THREE` longtext,
  `OPTION_FOUR` longtext,
  `OPTION_FIVE` longtext,
  `RIGHT_ANSWER` longtext,
  `PHASE` varchar(255) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `CHAPTER` varchar(255) DEFAULT NULL,
  `TOPIC` varchar(255) DEFAULT NULL,
  `MEDICAL_CONDITION` varchar(255) DEFAULT NULL,
  `SPECIALITY` varchar(255) DEFAULT NULL,
  `EXAM_YEAR` varchar(255) DEFAULT NULL,
  `ENTRANCE_EXAM_CODE` varchar(255) DEFAULT NULL,
  `DIFFICULTY_LEVEL` varchar(255) DEFAULT 'ONE',
  `QUESTION_CATEGORY` varchar(255) DEFAULT NULL,
  `COMMENT_STATUS` varchar(255) DEFAULT 'OPEN',
  `HAS_ATTACHMENT` tinyint(1) DEFAULT NULL,
  `IS_CLASSIFIED` tinyint(1) DEFAULT '0',
  `IS_ACTIVE` tinyint(1) DEFAULT '0',
  `IS_DUPLICATE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ENTRANCE_EXAM_CODE` (`ENTRANCE_EXAM_CODE`),
  KEY `subject_fk` (`SUBJECT`),
  KEY `chapter_fk` (`CHAPTER`),
  KEY `topic_fk` (`TOPIC`),
  KEY `chapter` (`CHAPTER`,`SUBJECT`),
  KEY `subjectCode` (`SUBJECT`,`PHASE`),
  KEY `topic` (`TOPIC`,`CHAPTER`,`SUBJECT`),
  KEY `medicalCondition` (`MEDICAL_CONDITION`,`CHAPTER`,`SUBJECT`)
) ;

/*Table structure for table `question_keywords` */

DROP TABLE IF EXISTS `question_keywords`;

CREATE TABLE `question_keywords` (
  `QUESTIONID` bigint(20) NOT NULL,
  `KEYWORDS` varchar(255) DEFAULT NULL,
  KEY `fk_question_keywords` (`QUESTIONID`)

);

/*Table structure for table `security_group` */

DROP TABLE IF EXISTS `security_group`;

CREATE TABLE `security_group` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
);

/*Table structure for table `security_permission` */

DROP TABLE IF EXISTS `security_permission`;

CREATE TABLE `security_permission` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PERMISSION_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PERMISSION_ID` (`PERMISSION_ID`)
);

/*Table structure for table `securitygroup_security_permissions` */

DROP TABLE IF EXISTS `securitygroup_security_permissions`;

CREATE TABLE `securitygroup_security_permissions` (
  `SECURITYGROUP` bigint(20) NOT NULL,
  `SECURITYPERMISSIONS` bigint(20) NOT NULL,
  PRIMARY KEY (`SECURITYGROUP`,`SECURITYPERMISSIONS`),
  KEY `FK85E36305E5AC1C76` (`SECURITYPERMISSIONS`),
  KEY `FK85E36305B3968A7B` (`SECURITYGROUP`)


);

/*Table structure for table `self_study_question_bank` */

DROP TABLE IF EXISTS `self_study_question_bank`;

CREATE TABLE `self_study_question_bank` (
  `QUESTION_ID` bigint(20) NOT NULL,
  `LAST_EVENT_SEQUENCE_NUMBER` bigint(20) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `ALTERED_BY_SPECIALIST` tinyint(1) NOT NULL,
  `ASSIGNED_TO_SUBJECT_EXPERT_ON` datetime DEFAULT NULL,
  `ASSIGNED_TO_SUBJECT_SPECIALIST_ON` datetime DEFAULT NULL,
  `CLOSED_ON` datetime DEFAULT NULL,
  `EXPERT_RATING` int(11) NOT NULL,
  `GOOD_TO_KNOW` longtext,
  `MUST_KNOW` longtext,
  `REJECTED` tinyint(1) NOT NULL,
  `REJECTED_ON` datetime DEFAULT NULL,
  `REJECTION_REASON` longtext,
  `STATUS` varchar(255) DEFAULT NULL,
  `SUBJECT_EXPERT` varchar(255) DEFAULT NULL,
  `SUBJECT_SPECIALIST` varchar(255) DEFAULT NULL,
  `IS_ALTERED_BY_ACTIVATOR` tinyint(1) NOT NULL,
  `ASSIGNED_TO_ACTIVATOR_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`QUESTION_ID`)
) ;

/*Table structure for table `question_comment` */

DROP TABLE IF EXISTS `question_comment`;

CREATE TABLE `question_comment` (
  `ID` bigint(20) NOT NULL auto_increment,
  `BOOK_REFERENCE` longtext,
  `CHOICE` varchar(255) default NULL,
  `COMMENT_TEXT` longtext,
  `EDITION` longtext,
  `PAGE_NUMBER` longtext,
  `RATING_COUNT` int(11) NOT NULL,
  `RATING_TOTAL` int(11) NOT NULL,
  `REFERENCE_LINK` longtext,
  `REMARKS` longtext,
  `QUESTION_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FKE54A06E63529EE7F` (`QUESTION_ID`),
  CONSTRAINT `FKE54A06E63529EE7F` FOREIGN KEY (`QUESTION_ID`) REFERENCES `self_study_question_bank` (`QUESTION_ID`)
);


/*Table structure for table `self_study` */

DROP TABLE IF EXISTS `self_study`;

CREATE TABLE `self_study` (
  `ID` varchar(255) NOT NULL,
  `QUESTION_ID` varchar(255) NOT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `STUDENT_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
);

/*Table structure for table `self_study_session` */

DROP TABLE IF EXISTS `self_study_session`;

CREATE TABLE `self_study_session` (
  `ID` varchar(255) NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `PHASE` varchar(255) DEFAULT NULL,
  `START_TIME` datetime NOT NULL,
  `STUDENT_ID` varchar(255) DEFAULT NULL,
  `STUDY_DURATION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_self_study_session` (`STUDENT_ID`)

);

/*Table structure for table `speciality` */

DROP TABLE IF EXISTS `speciality`;

CREATE TABLE `speciality` (
  `SPECIALITY_CODE` varchar(255) NOT NULL,
  `SPECIALITY_DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SPECIALITY_CODE`)
);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `INSTITUTE_ID` bigint(20) DEFAULT NULL,
  `MOBILE_NUMBER` varchar(255) DEFAULT NULL,
  `NATIONALITY` varchar(255) DEFAULT NULL,
  `PHASE` varchar(255) NOT NULL,
  `REAL_TIME_EXAM_NOTIFICATION_REQUIRE` tinyint(1) DEFAULT NULL,
  `SPECIALISATION` varchar(255) NOT NULL,
  `STUDENT_ID` varchar(255) DEFAULT NULL,
  `ID` varchar(255) NOT NULL,
  `LAST_NOTIFICATION_READ_ON` DATE NULL,
  PRIMARY KEY (`ID`),
  KEY `FKBACA0E1BC5BA4AA4` (`ID`)

);

/*Table structure for table `student_enquiry` */

DROP TABLE IF EXISTS `student_enquiry`;

CREATE TABLE `student_enquiry` (
  `ENQUIRY_ID` varchar(255) NOT NULL,
  `LAST_EVENT_SEQUENCE_NUMBER` bigint(20) default NULL,
  `VERSION` bigint(20) default NULL,
  `BOOK_REFERENCE` varchar(255) default NULL,
  `CURRENT_LEAD_PLAN` bigint(20) default NULL,
  `ENQUIRY_TEXT` varchar(255) default NULL,
  `FACULTY_ASSIGNED` varchar(255) default NULL,
  `FACULTY_RESPONSE` longtext,
  `LAST_MODIFIED` datetime NOT NULL,
  `QUESTION_ID` varchar(255) default NULL,
  `RAISED_ON` datetime NOT NULL,
  `REASSIGN_REASON` varchar(255) default NULL,
  `REFERENCE_LINK` varchar(255) default NULL,
  `STATUS` varchar(255) default NULL,
  `STUDENT_ID` varchar(255) default NULL,
  PRIMARY KEY  (`ENQUIRY_ID`)
);

/*Table structure for table `student_enquiry_status` */

DROP TABLE IF EXISTS `student_enquiry_status`;

CREATE TABLE `student_enquiry_status` (
  `ENQUIRY_ID` varchar(255) NOT NULL,
  `PERSON_ID` varchar(255) NOT NULL,
  `STATUS` varchar(255) NOT NULL,
  `DATE_TIME` datetime NOT NULL,
  `COMMENT` varchar(255) DEFAULT NULL
);

/*Table structure for table `student_exam` */

DROP TABLE IF EXISTS `student_exam`;

CREATE TABLE `student_exam` (
  `ID` varchar(255) NOT NULL,
  `LAST_EVENT_SEQUENCE_NUMBER` bigint(20) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `EXAM_COMPLETED_ON` varchar(255) DEFAULT NULL,
  `EXAM_TYPE` varchar(255) DEFAULT NULL,
  `FINAL_SCORE` float DEFAULT NULL,
  `IMPROVEMENT_INDEX` float DEFAULT NULL,
  `PHASE_FOUR` float DEFAULT NULL,
  `PHASE_INTERN` float DEFAULT NULL,
  `PHASE_ONE` float DEFAULT NULL,
  `PHASE_THREE` float DEFAULT NULL,
  `PHASE_TWO` float DEFAULT NULL,
  `TOTAL_CORRECT_ANSWERS` int(11) DEFAULT NULL,
  `TOTAL_INCORRECT_ANSWERS` int(11) DEFAULT NULL,
  `TOTAL_NEGATIVE_MARKS` float DEFAULT NULL,
  `TOTAL_POSITIVE_MARKS` int(11) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TAKEN_ON` varchar(255) NOT NULL,
  `TEMPLATEEXAM` varchar(255) DEFAULT NULL,
  `TEST_DURATION` varchar(255) NOT NULL,
  `TOTAL_MARKS` int(11) DEFAULT NULL,
  `ANSWERSHEET` bigint(20) DEFAULT NULL,
  `STUDENT` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8ABCF3C3AB8260C` (`ANSWERSHEET`),
  KEY `FK8ABCF3C37DD4D6E0` (`STUDENT`)


);

/*Table structure for table `student_plan` */

DROP TABLE IF EXISTS `student_plan`;

CREATE TABLE `student_plan` (
  `ID` varchar(255) NOT NULL,
  `LAST_EVENT_SEQUENCE_NUMBER` bigint(20) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `ACTUAL_END_DATE` date DEFAULT NULL,
  `PAYMENT_ID` varchar(255) DEFAULT NULL,
  `PLAN_END_DATE` date NOT NULL,
  `PLAN_ID` bigint(20) DEFAULT NULL,
  `PLAN_START_DATE` date NOT NULL,
  `PLAN_TYPE` varchar(255) NOT NULL,
  `STUDENT_ID` varchar(255) NOT NULL,
  `IS_FORCED_DEACTIVATION` tinyint(1) DEFAULT '0',
  `STUDENT_PLAN_PHASE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_student_plan` (`PAYMENT_ID`)

);

/*Table structure for table `studentexam_questions` */

DROP TABLE IF EXISTS `studentexam_questions`;

CREATE TABLE `studentexam_questions` (
  `STUDENTEXAM` varchar(255) NOT NULL,
  `PHASE` varchar(255) DEFAULT NULL,
  `QUESTION_ID` bigint(20) DEFAULT NULL,
  KEY `FK91263E8481F4B3E` (`STUDENTEXAM`)

);

/*Table structure for table `subject` */



/*Table structure for table `subject_allocation` */

DROP TABLE IF EXISTS `subject_allocation`;

CREATE TABLE `subject_allocation` (
  `ID` bigint(20) NOT NULL,
  `EXAM_CODE` varchar(255) NOT NULL,
  `SUBJECT` varchar(255) NOT NULL,
  `NUMBER` int(4) NOT NULL,
  `PERCENTAGE` decimal(3,3) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `subject_allocation_ibfk_1` (`EXAM_CODE`),
  KEY `subject_allocation_ibfk_2` (`SUBJECT`)


);

/*Table structure for table `subject_based_final_score` */

DROP TABLE IF EXISTS `subject_based_final_score`;

CREATE TABLE `subject_based_final_score` (
  `STUDENT_EXAM_ID` varchar(255) NOT NULL,
  `FINAL_SCORE` float DEFAULT NULL,
  `SUBJECT_CODE` varchar(255) NOT NULL,
  PRIMARY KEY (`STUDENT_EXAM_ID`,`SUBJECT_CODE`),
  KEY `FK90602F2A9799359B` (`STUDENT_EXAM_ID`)

);

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `ID` varchar(255) NOT NULL,
  `HOME_PAGE_VIEW` varchar(255) DEFAULT NULL,
  `IS_ACCOUNT_NON_LOCKED` tinyint(1) DEFAULT NULL,
  `IS_EMAIL_VERIFIED` tinyint(1) DEFAULT NULL,
  `IS_ENABLED` tinyint(1) DEFAULT NULL,
  `NUMBER_OF_FAILED_LOGIN_ATTEMPTS` int(11) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `VALID_UPTO_DATE` date DEFAULT NULL,
  `PERSONROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  KEY `FKC672F9D5EE3BA6D4` (`PERSONROLE`)

);

/*Table structure for table `userlogin_security_groups` */

DROP TABLE IF EXISTS `userlogin_security_groups`;

CREATE TABLE `userlogin_security_groups` (
  `USERLOGIN` varchar(255) NOT NULL,
  `SECURITYGROUPS` bigint(20) NOT NULL,
  PRIMARY KEY (`USERLOGIN`,`SECURITYGROUPS`),
  KEY `FK82384FD2D5896450` (`SECURITYGROUPS`),
  KEY `FK82384FD297B86779` (`USERLOGIN`)


);
/*Table structure for table `personal_note` */

DROP TABLE IF EXISTS `personal_note`;

CREATE TABLE `personal_note` (
  `ID` varchar(255) NOT NULL,
  `PERSONAL_NOTE` longtext,
  `QUESTION_ID` bigint(20) NOT NULL,
  `STUDENT_ID` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
);


DROP TABLE IF EXISTS `entry_level_plan_upgrade_audit`;
CREATE TABLE `entry_level_plan_upgrade_audit` (
  `STUDENT_ID` varchar(255) NOT NULL,
  `PAYMENT_ID` varchar(255) NOT NULL,
  `UPGRADED_TO_PLAN_ID` bigint(20) NOT NULL,
  `AMOUNT` decimal(19,2) NOT NULL,
  `UPGRADED_ON` date NOT NULL
);

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `MOBILE_NUMBER` varchar(255) DEFAULT NULL,
  `NATIONALITY` varchar(255) DEFAULT NULL,
  `ID` varchar(255) NOT NULL,
  `MEDICAL_ADMIN` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3B40B2FC5BA4AA4` (`ID`),
  CONSTRAINT `FK3B40B2FC5BA4AA4` FOREIGN KEY (`ID`) REFERENCES `person_role` (`ID`)
);

DROP TABLE IF EXISTS `selfstudy_questionbank_faculty_audit`;
CREATE TABLE `selfstudy_questionbank_faculty_audit` (
  `QUESTION_ID` bigint(20) NOT NULL,
  `FACULTY_TYPE` varchar(255) NOT NULL,
  `CHAPTER` varchar(255) default NULL,
  `TOPIC` varchar(255) default NULL,
  `MEDICAL_CONDITION` varchar(255) default NULL,
  `DIFFICULTY` varchar(255) default NULL,
  `MUST_KNOW` longtext,
  `GOOD_TO_KNOW` longtext,
  `ATTEMPT` varchar(255) NOT NULL,
  `RIGHT_ANSWER` varchar(255) default NULL,
  PRIMARY KEY  (`QUESTION_ID`,`FACULTY_TYPE`,`ATTEMPT`)
) ;

DROP TABLE IF EXISTS `question_comment_faculty_audit`;
CREATE TABLE `question_comment_faculty_audit` (
  `QUESTION_ID` bigint(20) NOT NULL default '0',
  `CHOICE` varchar(255) NOT NULL default '',
  `FACULTY_TYPE` varchar(255) NOT NULL default '',
  `BOOK_REFERENCE` longtext,
  `COMMENT_TEXT` longtext,
  `EDITION` longtext,
  `PAGE_NUMBER` longtext,
  `REFERENCE_LINK` longtext,
  `TITLE` longtext,
  `REMARKS` longtext,
  `ATTEMPT` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`QUESTION_ID`,`CHOICE`,`FACULTY_TYPE`,`ATTEMPT`)
);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
