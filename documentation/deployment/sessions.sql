/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.1.73-log : Database - sessions
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `jettysessionids` */

DROP TABLE IF EXISTS `jettysessionids`;

CREATE TABLE `jettysessionids` (
  `id` varchar(120) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `jettysessions` */

DROP TABLE IF EXISTS `jettysessions`;

CREATE TABLE `jettysessions` (
  `rowId` varchar(120) NOT NULL DEFAULT '',
  `sessionId` varchar(120) DEFAULT NULL,
  `contextPath` varchar(60) DEFAULT NULL,
  `virtualHost` varchar(60) DEFAULT NULL,
  `lastNode` varchar(60) DEFAULT NULL,
  `accessTime` bigint(20) DEFAULT NULL,
  `lastAccessTime` bigint(20) DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `cookieTime` bigint(20) DEFAULT NULL,
  `lastSavedTime` bigint(20) DEFAULT NULL,
  `expiryTime` bigint(20) DEFAULT NULL,
  `map` blob,
  PRIMARY KEY (`rowId`),
  KEY `idx_JettySessions_expiry` (`expiryTime`),
  KEY `idx_JettySessions_session` (`sessionId`,`contextPath`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
