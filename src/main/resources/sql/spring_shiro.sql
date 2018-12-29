/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : spring_shiro

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-29 16:55:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `PERMISSION_ID` int(11) NOT NULL COMMENT '主键',
  `PERMISSION_NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `RESOURCE_TYPE` varchar(255) DEFAULT NULL COMMENT '资源类型，[menu|button]',
  `URL` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `PERMISSION` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父编号',
  `PRRENT_IDS` varchar(255) DEFAULT NULL COMMENT '父编号列表',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0：不可用；1：可用',
  PRIMARY KEY (`PERMISSION_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '用户管理', 'menu', 'user/userList', 'user:view', '0', '0/', '1');
INSERT INTO `sys_permission` VALUES ('2', '用户添加', 'button', 'user/userAdd', 'user:add', '1', '0/1', '1');
INSERT INTO `sys_permission` VALUES ('3', '用户删除', 'button', 'user/userDel', 'user:del', '1', '0/1', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` int(11) NOT NULL COMMENT '编号',
  `ROLE` varchar(255) DEFAULT NULL COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0：不可用；1：可用，是否可用,如果不可用将不会添加给用户',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', '0');
INSERT INTO `sys_role` VALUES ('2', 'vip', 'VIP会员', '0');
INSERT INTO `sys_role` VALUES ('3', 'test', '测试', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `ID` int(11) NOT NULL,
  `PERMISSION_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '3', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `USER_NAME` varchar(255) NOT NULL COMMENT '登录用户名',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称（昵称或者真实姓名，根据实际情况定义）',
  `PASSWORD` varchar(255) NOT NULL COMMENT '加密密码',
  `SALT` varchar(255) NOT NULL COMMENT '加密密码的盐',
  `STATE` int(1) NOT NULL COMMENT '用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `OTP` int(1) NOT NULL DEFAULT '0' COMMENT 'OTP绑定状态，0-未绑定；1-绑定',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `UNI_USER_NAME` (`USER_NAME`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0', null, '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
