/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : spring_shiro

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-01-01 18:20:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PERMISSION_NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `RESOURCE_TYPE` varchar(255) DEFAULT NULL COMMENT '资源类型，[menu|button]',
  `URL` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `PERMISSION` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父编号',
  `PRRENT_IDS` varchar(255) DEFAULT NULL COMMENT '父编号列表',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0：不可用；1：可用',
  PRIMARY KEY (`PERMISSION_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ROLE` varchar(255) DEFAULT NULL COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `AVAILABLE` int(1) DEFAULT NULL COMMENT '0：不可用；1：可用，是否可用,如果不可用将不会添加给用户',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERMISSION_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
  `PHONE_NO` varchar(11) DEFAULT NULL COMMENT '手机号',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `SECRET_KEY` varchar(64) DEFAULT NULL COMMENT 'OTP动态验证码KEY',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `UNI_USER_NAME` (`USER_NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', '4UT2GTFWIRKA2Z6R');
INSERT INTO `sys_user` VALUES ('2', 'admin1', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('3', 'admin3', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('4', 'admin4', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('5', 'admin5', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('6', 'admin6', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('7', 'admin7', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('8', 'admin8', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('9', 'admin9', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('10', 'admin10', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('11', 'admin11', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '1017287599@qq.com', '1', '13693340821', '2018-12-30 19:19:29', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('12', 'admin32', null, '43e7242d3dc251fcecdb5c6b1d0137e8', '637712f1ad62dda8045a8ea5d757611b', '1', '11@11.com', '0', '18514589031', '2019-01-01 00:00:00', 'IBWLAQROY5XQJBJT');
INSERT INTO `sys_user` VALUES ('13', 'admin33', null, '8c80c1de0e607947c1d43f49869896d9', 'dce0276ec657052d16dda65fca964add', '1', '11@11.com', '1', '18514589031', '2019-01-01 00:00:00', 'CSRD7S3ML5LLWHCN');
INSERT INTO `sys_user` VALUES ('14', 'admin34', null, '2cf6ad625a7f42abddcecd47f55115bc', '0737f8b398f55f004b1bed4c4b90beab', '1', '11@11.com', '0', '18514589031', '2019-01-01 00:00:00', null);
INSERT INTO `sys_user` VALUES ('15', 'admin35', null, '437858a1838cff20ad5f40db671fb983', '854a267e74fb1613cc169d28d0dfb8fb', '1', '11@11.com', '0', '18514589031', '2019-01-01 07:17:30', null);
INSERT INTO `sys_user` VALUES ('16', 'lijun', '李俊', '78e5c00c3968873469dbfea991b0451c', 'ab06f7d699b71e7f0d7e4275c226b486', '1', '11@qq.com', '0', '18514589031', '2019-01-01 07:22:43', null);
INSERT INTO `sys_user` VALUES ('17', 'xiaoqing', '小晴', '55f49cfeee12115ba8e6a1a5dd7e508d', '964d3f65ea604e918224c13a3b404cfa', '1', '11@qq.com', '0', '13692239812', '2019-01-01 07:24:26', null);
INSERT INTO `sys_user` VALUES ('18', 'guodong', '国东', 'e83ac4737e51c1db3fda46eda7849c35', 'd194c69c6b381db0ab19237e4f5ced2b', '1', '111231233@11.com', '0', '13692239816', '2019-01-01 18:14:42', null);
INSERT INTO `sys_user` VALUES ('19', 'adddd', 'adfsdf', 'da857410450cba43f07878d630ac5af4', 'a1338965b3ee24a0fc26e026f7d4ac4d', '1', '111231233@11.com', '0', '13692239817', '2019-01-01 18:16:38', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '12');
INSERT INTO `sys_user_role` VALUES ('3', '1', '13');
INSERT INTO `sys_user_role` VALUES ('4', '1', '14');
INSERT INTO `sys_user_role` VALUES ('5', '1', '15');
INSERT INTO `sys_user_role` VALUES ('6', '2', '16');
INSERT INTO `sys_user_role` VALUES ('7', '2', '17');
INSERT INTO `sys_user_role` VALUES ('11', '1', '18');
INSERT INTO `sys_user_role` VALUES ('12', '3', '19');
