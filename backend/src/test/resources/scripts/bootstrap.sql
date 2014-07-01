/*
SQLyog Community v10.2 
MySQL - 5.1.51-community-log : Database - bootstrap
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3B40B2FC5BA4AA4` (`ID`)

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
  PRIMARY KEY (`AGGREGATE_IDENTIFIER`,`SEQUENCE_NUMBER`,`TYPE`),
  UNIQUE KEY `EVENT_IDENTIFIER` (`EVENT_IDENTIFIER`)
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

CREATE ALIAS IF NOT EXISTS Date FOR "org.nthdimenzion.userdefinedh2dbfunctions.H2DBFunctions.currentDate";

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
