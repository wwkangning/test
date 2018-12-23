/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : spring_shiro

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 23/12/2018 22:31:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `PERMISSION_ID` int(11) NOT NULL COMMENT '主键',
  `PERMISSION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `RESOURCE_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源类型，[menu|button]',
  `URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `PERMISSION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `PARENT_ID` int(11) NULL DEFAULT NULL COMMENT '父编号',
  `PRRENT_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父编号列表',
  `AVAILABLE` int(1) NULL DEFAULT NULL COMMENT '0：不可用；1：可用',
  PRIMARY KEY (`PERMISSION_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '用户管理', 'menu', 'user/userList', 'user:view', 0, '0/', 1);
INSERT INTO `sys_permission` VALUES (2, '用户添加', 'button', 'user/userAdd', 'user:add', 1, '0/1', 1);
INSERT INTO `sys_permission` VALUES (3, '用户删除', 'button', 'user/userDel', 'user:del', 1, '0/1', 1);

SET FOREIGN_KEY_CHECKS = 1;
