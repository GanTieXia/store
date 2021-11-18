/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 18/11/2021 09:21:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别（0男1女）',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `personage_picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `id_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (3, '肝铁侠', '0', '1234567@qq.com', '/storeProject/image/20211017/10000003.jpg', '10000003', '99f11d010f20a896fed5d0feeefe031c', '2021-10-17 20:01:46');
INSERT INTO `s_user` VALUES (5, '蜘蛛侠', '0', '123456@qq.com', '/storeProject/image/20211017/10000004.png', '10000004', '2d44bbd0f0a3a35a384382fb1a6a1e0f', '2021-10-17 20:38:55');
INSERT INTO `s_user` VALUES (6, '钢铁侠', '0', '13360@qq.com', '/storeProject/image/20211017/10000005.png', '10000005', '8d8789ecddb5f8bfbad4ad30c97f86a4', '2021-11-09 22:27:04');
INSERT INTO `s_user` VALUES (10, '奇异博士', '0', '13360240@qq.com', '/storeProject/image/20211110/10000006.png', '10000006', 'a50dc3f4440854c8f0b7db38913fb3b3', '2021-11-10 14:04:34');
INSERT INTO `s_user` VALUES (11, '神奇女侠', '1', '1336024@qq.com', '/storeProject/image/20211117/10000007.png', '10000007', '2dd0c6c424d1afbf925b8be4fbe85981', '2021-11-17 10:57:39');
INSERT INTO `s_user` VALUES (12, '雷神', '0', '1336024089@qq.com', '/storeProject/image/20211117/10000008.png', '10000008', '1f62ff7760da2b49ee1468b19e90d80f', '2021-11-17 11:08:35');

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `commodity_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `inventory_begin` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初始库存',
  `inventory_end` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剩余库存',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_commodity
-- ----------------------------
INSERT INTO `t_commodity` VALUES (1, '华为P50 pro(8+256GB)', '1000', '999', 6288.99, '2021-10-01 02:35:30');

-- ----------------------------
-- Table structure for t_count
-- ----------------------------
DROP TABLE IF EXISTS `t_count`;
CREATE TABLE `t_count`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `visit` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问量',
  `count_time` date NULL DEFAULT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_count
-- ----------------------------
INSERT INTO `t_count` VALUES (1, '25', '2021-11-02');
INSERT INTO `t_count` VALUES (2, '120', '2021-11-03');
INSERT INTO `t_count` VALUES (3, '260', '2021-11-04');
INSERT INTO `t_count` VALUES (4, '580', '2021-11-05');
INSERT INTO `t_count` VALUES (5, '600', '2021-11-06');
INSERT INTO `t_count` VALUES (6, '250', '2021-11-07');
INSERT INTO `t_count` VALUES (7, '290', '2021-11-08');
INSERT INTO `t_count` VALUES (8, '45', '2021-11-09');
INSERT INTO `t_count` VALUES (9, '8', '2021-11-10');
INSERT INTO `t_count` VALUES (10, '59', '2021-11-12');
INSERT INTO `t_count` VALUES (11, '13', '2021-11-14');
INSERT INTO `t_count` VALUES (12, '45', '2021-11-15');
INSERT INTO `t_count` VALUES (13, '29', '2021-11-16');
INSERT INTO `t_count` VALUES (14, '31', '2021-11-17');

SET FOREIGN_KEY_CHECKS = 1;
