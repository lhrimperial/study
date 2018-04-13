/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : ifarm-console

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-04-13 11:49:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_console_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_console_permission`;
CREATE TABLE `t_console_permission` (
  `id` int(11) NOT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `resource_permission` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_console_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_console_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_console_resource`;
CREATE TABLE `t_console_resource` (
  `id` int(11) NOT NULL,
  `resource_code` varchar(50) NOT NULL,
  `resource_name` varchar(100) NOT NULL,
  `entry_url` varchar(100) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `resource_level` tinyint(1) DEFAULT NULL,
  `resource_type` tinyint(4) DEFAULT NULL,
  `display_order` tinyint(4) DEFAULT NULL,
  `node_icon` varchar(100) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `leaf_flag` char(1) DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_console_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_console_role
-- ----------------------------
DROP TABLE IF EXISTS `t_console_role`;
CREATE TABLE `t_console_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_console_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_console_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_console_role_permission`;
CREATE TABLE `t_console_role_permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_console_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_console_user
-- ----------------------------
DROP TABLE IF EXISTS `t_console_user`;
CREATE TABLE `t_console_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nick_name` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile_no` varchar(50) DEFAULT NULL,
  `emp_code` varchar(50) DEFAULT NULL,
  `dept_code` varchar(50) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `active` char(1) DEFAULT NULL COMMENT '是否可用Y:N',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_console_user
-- ----------------------------
INSERT INTO `t_console_user` VALUES ('2', 'admin', '88212F91E2E9CF36981A91B6C518AF5C', null, 'YWRtaW4=', null, null, null, null, null, 'Y', '2018-04-12 20:05:53', '2018-04-12 20:05:53');

-- ----------------------------
-- Table structure for t_console_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_console_user_role`;
CREATE TABLE `t_console_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_console_user_role
-- ----------------------------
