/*
 Navicat MySQL Data Transfer

 Source Server         : dohko.mysql.001.master.hualala.com
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : dohko.mysql.001.master.hualala.com
 Source Database       : db_mock_server

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : utf-8

 Date: 06/24/2020 20:49:04 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tbl_agent_instance`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_agent_instance`;
CREATE TABLE `tbl_agent_instance` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(255) NOT NULL DEFAULT '' COMMENT '服务名',
  `agentName` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '实例名称',
  `ip` varchar(255) NOT NULL DEFAULT '' COMMENT '实例ip',
  `isActive` bit(1) DEFAULT NULL COMMENT '是否可用',
  `printLog` bit(1) DEFAULT NULL COMMENT '是否打印日志',
  `accessToken` varchar(255) DEFAULT '' COMMENT '认证token',
  `firstRequestTime` datetime DEFAULT NULL COMMENT '首次请求时间，注册时间',
  `lastRequestTime` datetime DEFAULT NULL COMMENT '最后访问时间',
  `updateTime` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `serviceName` (`serviceName`,`agentName`,`ip`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_grpc_interface_jar`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_grpc_interface_jar`;
CREATE TABLE `tbl_grpc_interface_jar` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `memo` varchar(255) DEFAULT '' COMMENT '描述',
  `groupID` varchar(255) DEFAULT '' COMMENT 'maven groupID',
  `artifactID` varchar(255) DEFAULT '' COMMENT 'maven artifactID',
  `version` varchar(255) DEFAULT '' COMMENT 'maven version',
  `jarUrl` varchar(1000) DEFAULT '' COMMENT 'jar包地址',
  `updateTime` datetime DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  UNIQUE KEY `groupID` (`groupID`,`artifactID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_grpc_request_group`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_grpc_request_group`;
CREATE TABLE `tbl_grpc_request_group` (
  `groupID` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(255) DEFAULT NULL,
  `sortIndex` int(11) NOT NULL DEFAULT '99',
  `isActive` bit(1) NOT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`groupID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_grpc_request_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_grpc_request_mapping`;
CREATE TABLE `tbl_grpc_request_mapping` (
  `requestID` int(11) NOT NULL AUTO_INCREMENT,
  `groupID` int(11) NOT NULL,
  `serviceName` varchar(100) NOT NULL,
  `methodName` varchar(100) NOT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `responseBody` mediumtext NOT NULL,
  `responseDelay` int(11) NOT NULL DEFAULT '0' COMMENT '延迟输出时间，单位ms',
  `sortIndex` int(11) NOT NULL DEFAULT '99',
  `isActive` bit(1) DEFAULT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_grpc_request_script`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_grpc_request_script`;
CREATE TABLE `tbl_grpc_request_script` (
  `scriptID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `groupID` int(11) NOT NULL DEFAULT '0',
  `scriptLanguage` enum('groovy','javascript') NOT NULL DEFAULT 'groovy',
  `scriptContent` text COMMENT '脚本内容',
  `serviceName` varchar(255) NOT NULL DEFAULT '',
  `methodName` varchar(255) NOT NULL DEFAULT '',
  `isActive` bit(1) NOT NULL,
  `sortIndex` int(11) NOT NULL DEFAULT '99',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`scriptID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_mapping_job`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mapping_job`;
CREATE TABLE `tbl_mapping_job` (
  `jobID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `requestID` int(11) NOT NULL,
  `protocol` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT 'http' COMMENT '''http''   ''grpc''',
  `delay` int(11) NOT NULL DEFAULT '0' COMMENT '延迟执行时间，单位 ms',
  `memo` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '备注',
  `sortIndex` int(11) NOT NULL DEFAULT '99' COMMENT '排序值， 越小越靠前',
  `isActive` bit(1) NOT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`jobID`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_mapping_rules_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mapping_rules_detail`;
CREATE TABLE `tbl_mapping_rules_detail` (
  `rulesDetailID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `requestID` int(11) NOT NULL DEFAULT '0',
  `protocol` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT 'http' COMMENT '''http''   ''grpc''',
  `variableName` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '比对用变量名',
  `variableSource` enum('header','parameter','cookie') DEFAULT 'parameter' COMMENT '变量来源',
  `variableValue` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '变量比对的值',
  `compareWay` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT 'equals' COMMENT '比对的方式：equals 字符串相等; contains 字符串包含; gt 数字大于； gte 数字大于等于； lt 数字小于； lte 数字小于等于； eq 数字等于； neq 数字不等于',
  `parentID` int(11) DEFAULT '0',
  `sortIndex` int(11) DEFAULT '99' COMMENT '排序值',
  `operator` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '&&' COMMENT '逻辑运算符:  &&   ||',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rulesDetailID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_mapping_task`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_mapping_task`;
CREATE TABLE `tbl_mapping_task` (
  `taskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jobID` int(11) NOT NULL,
  `taskType` int(11) NOT NULL,
  `configs` text CHARACTER SET utf8 NOT NULL COMMENT '任务对应的配置数据，JSONObject格式，由任务自行解析',
  `sortIndex` int(11) NOT NULL DEFAULT '99' COMMENT '排序值， 越小越靠前',
  `isActive` bit(1) NOT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`taskID`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_request_event`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_request_event`;
CREATE TABLE `tbl_request_event` (
  `requestEventID` int(11) NOT NULL AUTO_INCREMENT,
  `traceID` varchar(255) DEFAULT NULL,
  `eventName` varchar(255) DEFAULT NULL,
  `eventDesc` mediumtext,
  `updateTime` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`requestEventID`),
  KEY `traceID` (`traceID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=207231 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_request_log`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_request_log`;
CREATE TABLE `tbl_request_log` (
  `requestID` int(11) NOT NULL AUTO_INCREMENT,
  `requestPath` varchar(255) DEFAULT NULL,
  `protocol` varchar(255) DEFAULT NULL,
  `traceID` varchar(255) DEFAULT NULL,
  `requestTime` datetime(3) DEFAULT NULL,
  `responseTime` datetime(3) DEFAULT NULL,
  `updateTime` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB AUTO_INCREMENT=19597 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_rest_request_group`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_rest_request_group`;
CREATE TABLE `tbl_rest_request_group` (
  `groupID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `groupCode` varchar(255) CHARACTER SET utf8 NOT NULL,
  `groupName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sourceHost` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '来源host，nginx 配置时使用，如果不配置则不会创建nginx配置',
  `sourcePort` int(11) DEFAULT '80' COMMENT '来源端口号（被mock的端口号）',
  `memo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sortIndex` int(11) DEFAULT '99',
  `proxyIP` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '不走mock的接口，转发地址',
  `isActive` bit(1) NOT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`groupID`),
  UNIQUE KEY `groupCode` (`groupCode`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_rest_request_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_rest_request_mapping`;
CREATE TABLE `tbl_rest_request_mapping` (
  `requestID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `groupID` int(11) NOT NULL,
  `groupCode` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `path` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `memo` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注',
  `responseBody` mediumtext CHARACTER SET utf8 NOT NULL,
  `responseType` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '返回的数据类型：json  html  xml',
  `responseDelay` bigint(20) NOT NULL DEFAULT '0',
  `sortIndex` int(11) NOT NULL DEFAULT '99',
  `isActive` bit(1) NOT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`requestID`),
  KEY `modulesName_path` (`groupCode`,`path`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_rest_request_script`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_rest_request_script`;
CREATE TABLE `tbl_rest_request_script` (
  `scriptID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `scriptLanguage` enum('groovy','javascript') NOT NULL DEFAULT 'groovy',
  `scriptContent` text CHARACTER SET utf8 COMMENT '脚本内容',
  `groupID` int(11) NOT NULL,
  `groupCode` varchar(255) CHARACTER SET utf8 NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '/',
  `isActive` bit(1) NOT NULL,
  `sortIndex` int(11) NOT NULL DEFAULT '99',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`scriptID`),
  KEY `modulesName_path` (`groupCode`,`path`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_rules_compare_method`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_rules_compare_method`;
CREATE TABLE `tbl_rules_compare_method` (
  `compareMethodID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `compareMethod` enum('equals','contains','gt','gte','lt','lte','eq','neq') DEFAULT NULL COMMENT '对比方式',
  `compareMemo` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '对比方式描述',
  `sortIndex` int(11) DEFAULT '99' COMMENT '排序值',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`compareMethodID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tbl_system_config`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_system_config`;
CREATE TABLE `tbl_system_config` (
  `configID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `configType` int(11) NOT NULL DEFAULT '0' COMMENT '配置类型：1、rabbitmq，2、redis',
  `configTitle` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `configData` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '配置的具体内容，json格式',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`configID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
