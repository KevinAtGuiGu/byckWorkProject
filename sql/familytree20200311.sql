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

 Date: 11/03/2020 16:31:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分支管理员ID' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for family_member_mapping
-- ----------------------------
DROP TABLE IF EXISTS `family_member_mapping`;
CREATE TABLE `family_member_mapping`  (
  `family_member_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族用户关联ID',
  `parent_member_id` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族用户关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for famliy
-- ----------------------------
DROP TABLE IF EXISTS `famliy`;
CREATE TABLE `famliy`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for famliy_basic
-- ----------------------------
DROP TABLE IF EXISTS `famliy_basic`;
CREATE TABLE `famliy_basic`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族辈份' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for famliy_branch
-- ----------------------------
DROP TABLE IF EXISTS `famliy_branch`;
CREATE TABLE `famliy_branch`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家族分支' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `member_basic_info`;
CREATE TABLE `member_basic_info`  (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '分组ID',
  `parent_member_id` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `mate_id` bigint(20) NULL DEFAULT NULL COMMENT '伴侣ID',
  `source_id` int(11) NULL DEFAULT NULL COMMENT '来源',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
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
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `head_thumb` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `family_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家谱名',
  `other_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '曾用名',
  `id_card_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `birthday_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出生地点',
  `gender_id` int(11) NULL DEFAULT NULL COMMENT '性别',
  `age_range_id` int(11) NULL DEFAULT NULL COMMENT '年龄段',
  `age` int(11) NULL DEFAULT NULL COMMENT ' 年龄',
  `birthday` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `is_dead` bit(1) NULL DEFAULT b'0' COMMENT '是否死亡',
  `dead_date` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
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

SET FOREIGN_KEY_CHECKS = 1;
