/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : familytree

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 19/03/2020 18:08:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family`  (
  `family_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族ID',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `family_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '家族名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '家族代码',
  `status_id` int(11) NULL DEFAULT NULL COMMENT '状态',
  `owner_id` bigint(20) NULL DEFAULT 0 COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family
-- ----------------------------
INSERT INTO `family` VALUES (1, 1, '田', '1', NULL, 0, '2020-03-19 15:06:39', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for family_basic
-- ----------------------------
DROP TABLE IF EXISTS `family_basic`;
CREATE TABLE `family_basic`  (
  `family_basic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '辈分ID',
  `family_id` bigint(20) NULL DEFAULT NULL COMMENT '家族ID',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `basic_index` int(11) NULL DEFAULT NULL COMMENT '辈分排序',
  `family_basic_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 辈分名称',
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '辈分简介',
  `owner_id` bigint(20) NULL DEFAULT 0 COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_basic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族辈份' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family_basic
-- ----------------------------
INSERT INTO `family_basic` VALUES (1, 1, 1, 1, '世', '十五世', 0, '2020-03-19 16:29:53', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_basic` VALUES (2, 1, 1, 2, '农', '十六世', 0, '2020-03-19 16:30:06', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_basic` VALUES (3, 1, 1, 3, '思', '十七世', 0, '2020-03-19 16:30:24', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_basic` VALUES (4, 1, 1, 4, '中', '十八世', 0, '2020-03-19 16:50:46', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_basic` VALUES (5, 1, 1, 5, '道', '十九世', 0, '2020-03-19 16:50:56', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_basic` VALUES (6, 1, 1, 6, '继', '二十世', 0, '2020-03-19 16:51:04', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_basic` VALUES (7, 1, 1, 7, '学', '二十一世', 0, '2020-03-19 16:51:54', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for family_branch
-- ----------------------------
DROP TABLE IF EXISTS `family_branch`;
CREATE TABLE `family_branch`  (
  `family_branch_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族分支ID',
  `family_id` bigint(20) NULL DEFAULT NULL COMMENT '家族ID',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分支代码',
  `country_id` int(11) NULL DEFAULT NULL COMMENT '国家',
  `province_id` int(11) NULL DEFAULT NULL COMMENT '省份',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '城市',
  `district_id` int(11) NULL DEFAULT NULL COMMENT '区/县',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分支名称',
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分支简介',
  `status_id` int(11) NULL DEFAULT NULL COMMENT '状态',
  `owner_id` bigint(20) NULL DEFAULT 0 COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族分支' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family_branch
-- ----------------------------
INSERT INTO `family_branch` VALUES (1, 1, '1', 1, 110000, 110000, 110101, '北京分区', NULL, 0, 0, '2020-03-19 16:31:43', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for family_branch_admin
-- ----------------------------
DROP TABLE IF EXISTS `family_branch_admin`;
CREATE TABLE `family_branch_admin`  (
  `family_branch_admin_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分支管理员ID',
  `family_branch_id` bigint(20) NULL DEFAULT NULL COMMENT '家族分支ID',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `status_id` int(11) NULL DEFAULT NULL COMMENT '状态',
  `owner_id` bigint(20) NULL DEFAULT 0 COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_branch_admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分支管理员ID' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for family_member_mapping
-- ----------------------------
DROP TABLE IF EXISTS `family_member_mapping`;
CREATE TABLE `family_member_mapping`  (
  `family_member_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族用户关联ID',
  `parent_member_id` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `mate_id` bigint(20) NULL DEFAULT NULL COMMENT '伴侣id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `family_branch_id` bigint(20) NULL DEFAULT NULL COMMENT '家族分支ID',
  `family_basic_id` bigint(20) NULL DEFAULT NULL COMMENT '辈分ID',
  `family_id` bigint(20) NULL DEFAULT NULL COMMENT '家族ID',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联说明',
  `status_id` int(11) NULL DEFAULT NULL,
  `owner_id` bigint(20) NULL DEFAULT 0 COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_member_mapping_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族用户关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family_member_mapping
-- ----------------------------
INSERT INTO `family_member_mapping` VALUES (1, NULL, NULL, 13, 1, 1, 1, NULL, 0, 1, '2020-03-19 16:57:14', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (2, 13, NULL, 14, 1, 2, 1, NULL, 0, 1, '2020-03-19 16:58:52', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (3, 14, NULL, 15, 1, 3, 1, NULL, 0, 1, '2020-03-19 16:59:54', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (4, 15, NULL, 16, 1, 4, 1, NULL, 0, 1, '2020-03-19 17:02:06', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (5, 15, NULL, 17, 1, 4, 1, NULL, 0, 1, '2020-03-19 17:03:06', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (6, 15, NULL, 18, 1, 4, 1, NULL, 0, 1, '2020-03-19 17:03:23', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (7, 15, NULL, 19, 1, 4, 1, NULL, 0, 1, '2020-03-19 17:03:34', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (8, 15, NULL, 20, 1, 4, 1, NULL, 0, 1, '2020-03-19 17:03:44', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (9, 15, NULL, 21, 1, 4, 1, NULL, 0, 1, '2020-03-19 17:03:53', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (10, 17, NULL, 22, 1, 5, 1, NULL, 0, 1, '2020-03-19 17:04:43', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (11, 17, NULL, 23, 1, 5, 1, NULL, 0, 1, '2020-03-19 17:05:00', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (12, 17, NULL, 24, 1, 5, 1, NULL, 0, 1, '2020-03-19 17:05:16', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (13, 22, NULL, 25, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:05:44', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (14, 22, NULL, 26, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:05:56', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (15, 22, NULL, 27, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:06:05', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (16, 22, NULL, 28, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:06:31', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (17, 23, NULL, 29, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:06:58', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (18, 23, NULL, 30, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:07:07', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (19, 23, NULL, 31, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:07:17', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (20, 24, NULL, 32, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:07:39', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (21, 24, NULL, 33, 1, 6, 1, NULL, 0, 1, '2020-03-19 17:07:48', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (22, 26, NULL, 34, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:08:31', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (23, 27, NULL, 35, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:08:55', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (24, 28, NULL, 36, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:09:15', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (25, 29, NULL, 37, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:09:44', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (26, 29, NULL, 38, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:09:52', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (27, 30, NULL, 39, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:10:14', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (28, 31, NULL, 40, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:10:23', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (29, 31, NULL, 41, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:10:33', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (30, 33, NULL, 42, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:10:56', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (31, 17, 22, 43, 1, 5, 1, NULL, 0, 1, '2020-03-19 17:15:00', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `family_member_mapping` VALUES (32, 26, 34, 44, 1, 7, 1, NULL, 0, 1, '2020-03-19 17:16:20', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for member_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `member_basic_info`;
CREATE TABLE `member_basic_info`  (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '分组ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `source_id` int(11) NULL DEFAULT NULL COMMENT '来源',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `family_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家谱名',
  `member_prop_1_id` int(11) NULL DEFAULT NULL COMMENT '会员属性-1',
  `member_prop_2_id` int(11) NULL DEFAULT NULL COMMENT '会员属性-2',
  `member_prop_3_id` int(11) NULL DEFAULT NULL COMMENT '会员属性-3',
  `member_prop_4_id` int(11) NULL DEFAULT NULL COMMENT '会员属性-4',
  `member_prop_5_id` int(11) NULL DEFAULT NULL COMMENT '会员属性-5',
  `count_prop_1` int(11) NULL DEFAULT NULL COMMENT '计数属性1',
  `count_prop_2` int(11) NULL DEFAULT NULL COMMENT '计数属性2',
  `count_prop_3` int(11) NULL DEFAULT NULL COMMENT '计数属性3',
  `count_prop_4` int(11) NULL DEFAULT NULL COMMENT '计数属性4',
  `count_prop_5` int(11) NULL DEFAULT NULL COMMENT '计数属性5',
  `head_thumb` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `other_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '曾用名',
  `id_card_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `birthday_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出生地点',
  `gender_id` int(11) NULL DEFAULT NULL COMMENT '性别',
  `age_range_id` int(11) NULL DEFAULT NULL COMMENT '年龄段',
  `age` int(11) NULL DEFAULT NULL COMMENT ' 年龄',
  `birthday` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `is_dead` bit(1) NULL DEFAULT b'0' COMMENT '是否死亡',
  `dead_date` varchar(40) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL,
  `dead_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '死亡地点',
  `tomb_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '埋葬地点',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电邮',
  `country_id` int(11) NULL DEFAULT NULL COMMENT '国家',
  `province_id` int(11) NULL DEFAULT NULL COMMENT '省份',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '城市',
  `district_id` int(11) NULL DEFAULT NULL COMMENT '区/县',
  `business_zone_id` int(11) NULL DEFAULT NULL COMMENT '商业区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '门店',
  `owner_id` int(11) NULL DEFAULT 0 COMMENT '所有者ID',
  `status_id` int(11) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` int(11) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用5',
  `col6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用6',
  `col7` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用7',
  `col8` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用8',
  `col9` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用9',
  `col10` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用10',
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_basic_info
-- ----------------------------
INSERT INTO `member_basic_info` VALUES (13, NULL, 1, NULL, 'admin', '123456', '田世禄', '田世禄', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 16:57:14', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (14, NULL, 3, NULL, NULL, NULL, '田农盛', '田农盛', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 16:58:52', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (15, NULL, 3, NULL, NULL, NULL, '田思智', '田思智', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 16:59:54', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (16, NULL, 3, NULL, NULL, NULL, '田中岳', '田中岳', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:02:06', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (17, NULL, 3, NULL, NULL, NULL, '田中南', '田中南', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:03:06', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (18, NULL, 3, NULL, NULL, NULL, '田中庭', '田中庭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:03:23', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (19, NULL, 3, NULL, NULL, NULL, '田中信', '田中信', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:03:34', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (20, NULL, 3, NULL, NULL, NULL, '田中权', '田中权', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:03:44', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (21, NULL, 3, NULL, NULL, NULL, '田中凡', '田中凡', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:03:53', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (22, NULL, 3, NULL, NULL, NULL, '田子耀', '田子耀', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:04:43', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (23, NULL, 3, NULL, NULL, NULL, '田子厚', '田子厚', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:05:00', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (24, NULL, 3, NULL, NULL, NULL, '田子兴', '田子兴', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:05:16', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (25, NULL, 3, NULL, NULL, NULL, '田继虎', '田继虎', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:05:44', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (26, NULL, 3, NULL, NULL, NULL, '田红卫', '田红卫', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:05:56', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (27, NULL, 3, NULL, NULL, NULL, '田红兵', '田红兵', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:06:05', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (28, NULL, 3, NULL, NULL, NULL, '田红华', '田红华', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:06:31', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (29, NULL, 3, NULL, NULL, NULL, '田红勇', '田红勇', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:06:58', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (30, NULL, 3, NULL, NULL, NULL, '田艳梅', '田艳梅', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:07:07', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (31, NULL, 3, NULL, NULL, NULL, '田红涛', '田红涛', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:07:17', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (32, NULL, 3, NULL, NULL, NULL, '田红梅', '田红梅', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:07:39', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (33, NULL, 3, NULL, NULL, NULL, '田红彪', '田红彪', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:07:48', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (34, NULL, 3, NULL, NULL, NULL, '田学雷', '田学雷', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:08:31', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (35, NULL, 3, NULL, NULL, NULL, '田学雨', '田学雨', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:08:55', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (36, NULL, 3, NULL, NULL, NULL, '田学牛', '田学牛', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:09:15', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (37, NULL, 3, NULL, NULL, NULL, '田晨', '田晨', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:09:43', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (38, NULL, 3, NULL, NULL, NULL, '田学正', '田学正', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:09:52', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (39, NULL, 3, NULL, NULL, NULL, '田学锦', '田学锦', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:10:14', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (40, NULL, 3, NULL, NULL, NULL, '田偌语', '田偌语', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:10:23', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (41, NULL, 3, NULL, NULL, NULL, '田学谦', '田学谦', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:10:33', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (42, NULL, 3, NULL, NULL, NULL, '田学敏', '田学敏', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:10:56', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (43, NULL, 3, NULL, NULL, NULL, '田道云', '田道云', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:15:00', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `member_basic_info` VALUES (44, NULL, 3, NULL, NULL, NULL, '田雷', '田雷', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-03-19 17:16:20', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for member_rand_code
-- ----------------------------
DROP TABLE IF EXISTS `member_rand_code`;
CREATE TABLE `member_rand_code`  (
  `member_rand_code_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `status_id` int(11) NULL DEFAULT NULL COMMENT '状态',
  `sort_order` int(11) NULL DEFAULT NULL COMMENT '排序0-10置顶排序',
  `owner_id` bigint(20) NULL DEFAULT NULL COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`member_rand_code_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信认证表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '公告Id',
  `notice_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告名称',
  `notice_content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告内容',
  `status_id` int(11) DEFAULT NULL COMMENT '公告状态',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  `owner_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` int(11) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
/*INSERT INTO `notice` VALUES (1, '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', NULL, '2020-03-17 17:40:35', NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `notice` VALUES (2, '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', NULL, '2020-03-17 17:43:59', NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `notice` VALUES (3, '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', NULL, '2020-03-17 17:45:31', NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `notice` VALUES (4, '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', NULL, '2020-03-17 17:45:58', NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `notice` VALUES (5, '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', NULL, '2020-03-17 17:47:30', NULL, NULL, NULL, 0, NULL, NULL);*/

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `parent_role_id` bigint(20) NULL DEFAULT 0 COMMENT '角色父ID',
  `role_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `owner_id` bigint(20) NULL DEFAULT NULL COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, NULL, '超级管理员', NULL, 0, '2020-03-18 15:54:50', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, 0, NULL, '分支管理员', NULL, 0, '2020-03-18 15:54:50', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, 0, NULL, '注册用户', NULL, 0, '2020-03-18 15:54:50', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for weixin_user
-- ----------------------------
DROP TABLE IF EXISTS `weixin_user`;
CREATE TABLE `weixin_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '微信用户ID',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '分组ID',
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'OpenID',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `language` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '语言',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家',
  `head_image_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `union_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联合ID',
  `subscribe_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间戳',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `privilege` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限',
  `is_web_auth` int(11) NULL DEFAULT NULL COMMENT '是否是网页授权用户',
  `owner_id` int(11) NULL DEFAULT 0 COMMENT '所有者ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` int(11) NULL DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) NULL DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '微信用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weixin_user
-- ----------------------------
INSERT INTO `weixin_user` VALUES (1, 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2020-03-06 17:13:58', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `weixin_user` VALUES (2, 111, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2020-03-06 17:15:14', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `weixin_user` VALUES (3, 1111, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `weixin_user` VALUES (7, NULL, NULL, NULL, 'bbb', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-03-19 13:48:36', 1, '2020-03-19 14:29:40', 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
