/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : system_db

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-11 22:28:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookId` varchar(10) NOT NULL,
  `bookName` varchar(255) NOT NULL,
  `authorName` varchar(255) NOT NULL,
  `presentStock` int(11) NOT NULL,
  `totalStock` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`bookId`),
  UNIQUE KEY `bookId_UNIQUE` (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('0004', '大学英语', '未知', '5', '8', '1');
INSERT INTO `book` VALUES ('22', '高等数学', '未知', '2', '5', '0');
INSERT INTO `book` VALUES ('33', 'c++ primer plus', '未知', '2', '8', '0');
INSERT INTO `book` VALUES ('444', 'java疯狂讲义', '李刚', '5', '6', '0');
INSERT INTO `book` VALUES ('45644', '大学物理上', '未知', '6', '6', '0');
INSERT INTO `book` VALUES ('56465', 'python从入门到实践', '未知', '2', '9', '0');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `recordId` varchar(45) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `bookId` varchar(10) NOT NULL,
  `lendTime` datetime(6) DEFAULT NULL,
  `returnTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId_UNIQUE` (`recordId`),
  KEY `userkey` (`userId`),
  KEY `bookkey` (`bookId`),
  CONSTRAINT `bookkey` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`),
  CONSTRAINT `userkey` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('111', '0', '56465', '2018-04-12 00:00:00.000000', '2018-04-13 00:00:00.000000');
INSERT INTO `record` VALUES ('1111', '123', '444', '2018-04-11 00:00:00.000000', '2018-04-26 00:00:00.000000');
INSERT INTO `record` VALUES ('2018042601', '0', '0004', '2018-04-26 00:00:00.000000', '2018-04-26 00:00:00.000000');
INSERT INTO `record` VALUES ('20180426021840', '0', '0004', '2018-04-26 00:00:00.000000', '2018-04-26 00:00:00.000000');
INSERT INTO `record` VALUES ('20180426103736', '123', '22', '2018-04-26 00:00:00.000000', '2018-04-26 00:00:00.000000');
INSERT INTO `record` VALUES ('20180427024259', '2', '0004', '2018-04-27 14:42:59.830000', '2018-04-27 14:49:24.402000');
INSERT INTO `record` VALUES ('20180427065501', '0', '45644', '2018-04-27 06:55:01.203000', '2018-04-27 06:55:21.391000');
INSERT INTO `record` VALUES ('20181205103825', '0', '22', '2018-12-05 22:38:25.393000', null);
INSERT INTO `record` VALUES ('20181206120855', '5', '0004', '2018-12-06 12:08:55.789000', '2018-12-06 12:09:46.192000');
INSERT INTO `record` VALUES ('20181211030454', '0', '444', '2018-12-11 15:04:54.926000', '2018-12-11 15:06:08.238000');
INSERT INTO `record` VALUES ('20181211082034', '666', '444', '2018-12-11 20:20:34.050000', '2018-12-11 20:30:52.881000');
INSERT INTO `record` VALUES ('20181211082039', '666', '444', '2018-12-11 20:20:39.155000', '2018-12-11 20:31:01.945000');
INSERT INTO `record` VALUES ('20181211095645', '0', '45644', '2018-12-11 21:56:45.408000', '2018-12-11 22:00:34.241000');
INSERT INTO `record` VALUES ('20181211100021', '0', '45644', '2018-12-11 22:00:21.925000', '2018-12-11 22:00:27.331000');
INSERT INTO `record` VALUES ('20181211100116', '555', '45644', '2018-12-11 22:01:16.922000', '2018-12-11 22:01:21.658000');
INSERT INTO `record` VALUES ('235', '6666666666', '33', '2020-00-10 00:00:00.000000', '2020-00-10 00:00:00.000000');
INSERT INTO `record` VALUES ('865', '0', '444', '2018-04-26 00:00:00.000000', null);
INSERT INTO `record` VALUES ('999', '123', '0004', '2020-00-10 00:00:00.000000', '2020-00-10 00:00:00.000000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(10) NOT NULL DEFAULT '0',
  `userType` int(1) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '1');
INSERT INTO `user` VALUES ('02', '2');
INSERT INTO `user` VALUES ('1', '2');
INSERT INTO `user` VALUES ('123', '1');
INSERT INTO `user` VALUES ('2', '1');
INSERT INTO `user` VALUES ('3', '2');
INSERT INTO `user` VALUES ('3217004870', '1');
INSERT INTO `user` VALUES ('5', '1');
INSERT INTO `user` VALUES ('555', '1');
INSERT INTO `user` VALUES ('666', '1');
INSERT INTO `user` VALUES ('6666666666', '2');
