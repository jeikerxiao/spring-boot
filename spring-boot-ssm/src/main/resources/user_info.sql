/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : mydb

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 07/05/2017 13:57:27 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `usertype` varchar(2) DEFAULT NULL COMMENT '用户类型',
  `enabled` int(2) DEFAULT NULL COMMENT '是否可用',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(14) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('1', 'jeiker', '12345678', '1', '1', '风', '2342314', 'jeiker@126.com', '13355556666'), ('2', 'jeikerxiao', '12345678', '2', '1', '水', '52345234', 'jeikerxiao@126.com', '13355556664'), ('3', 'xiao', '12345678', '1', '1', '火', '23452345', 'xiao@gmail.com', '13355556655');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
