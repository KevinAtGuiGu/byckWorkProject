/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : familytree

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-03-19 09:51:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `family_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `family_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '家族名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '家族代码',
  `status_id` int(11) DEFAULT NULL COMMENT '状态',
  `owner_id` bigint(20) DEFAULT '0' COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='家族';

-- ----------------------------
-- Records of family
-- ----------------------------

-- ----------------------------
-- Table structure for family_basic
-- ----------------------------
DROP TABLE IF EXISTS `family_basic`;
CREATE TABLE `family_basic` (
  `family_basic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '辈分ID',
  `family_id` bigint(20) DEFAULT NULL COMMENT '家族ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `basic_index` int(11) DEFAULT NULL COMMENT '辈分排序',
  `family_basic_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 辈分名称',
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '辈分简介',
  `owner_id` bigint(20) DEFAULT '0' COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_basic_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='家族辈份';

-- ----------------------------
-- Records of family_basic
-- ----------------------------

-- ----------------------------
-- Table structure for family_branch
-- ----------------------------
DROP TABLE IF EXISTS `family_branch`;
CREATE TABLE `family_branch` (
  `family_branch_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族分支ID',
  `family_id` bigint(20) DEFAULT NULL COMMENT '家族ID',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分支代码',
  `country_id` int(11) DEFAULT NULL COMMENT '国家',
  `province_id` int(11) DEFAULT NULL COMMENT '省份',
  `city_id` int(11) DEFAULT NULL COMMENT '城市',
  `district_id` int(11) DEFAULT NULL COMMENT '区/县',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分支名称',
  `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分支简介',
  `status_id` int(11) DEFAULT NULL COMMENT '状态',
  `owner_id` bigint(20) DEFAULT '0' COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_branch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='家族分支';

-- ----------------------------
-- Records of family_branch
-- ----------------------------

-- ----------------------------
-- Table structure for family_branch_admin
-- ----------------------------
DROP TABLE IF EXISTS `family_branch_admin`;
CREATE TABLE `family_branch_admin` (
  `family_branch_admin_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分支管理员ID',
  `family_branch_id` bigint(20) DEFAULT NULL COMMENT '家族分支ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `status_id` int(11) DEFAULT NULL COMMENT '状态',
  `owner_id` bigint(20) DEFAULT '0' COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_branch_admin_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='分支管理员ID';

-- ----------------------------
-- Records of family_branch_admin
-- ----------------------------

-- ----------------------------
-- Table structure for family_member_mapping
-- ----------------------------
DROP TABLE IF EXISTS `family_member_mapping`;
CREATE TABLE `family_member_mapping` (
  `family_member_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '家族用户关联ID',
  `parent_member_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `family_branch_id` bigint(20) DEFAULT NULL COMMENT '家族分支ID',
  `family_basic_id` bigint(20) DEFAULT NULL COMMENT '辈分ID',
  `family_id` bigint(20) DEFAULT NULL COMMENT '家族ID',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联说明',
  `status_id` int(11) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT '0' COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`family_member_mapping_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='家族用户关联';

-- ----------------------------
-- Records of family_member_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for member_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `member_basic_info`;
CREATE TABLE `member_basic_info` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `parent_member_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `source_id` int(11) DEFAULT NULL COMMENT '来源',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `member_prop_1_id` int(11) DEFAULT NULL COMMENT '会员属性-1',
  `member_prop_2_id` int(11) DEFAULT NULL COMMENT '会员属性-2',
  `member_prop_3_id` int(11) DEFAULT NULL COMMENT '会员属性-3',
  `member_prop_4_id` int(11) DEFAULT NULL COMMENT '会员属性-4',
  `member_prop_5_id` int(11) DEFAULT NULL COMMENT '会员属性-5',
  `count_prop_1` int(11) DEFAULT NULL COMMENT '计数属性1',
  `count_prop_2` int(11) DEFAULT NULL COMMENT '计数属性2',
  `count_prop_3` int(11) DEFAULT NULL COMMENT '计数属性3',
  `count_prop_4` int(11) DEFAULT NULL COMMENT '计数属性4',
  `count_prop_5` int(11) DEFAULT NULL COMMENT '计数属性5',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `head_thumb` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `family_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '家谱名',
  `other_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '曾用名',
  `id_card_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证号码',
  `birthday_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '出生地点',
  `gender_id` int(11) DEFAULT NULL COMMENT '性别',
  `age_range_id` int(11) DEFAULT NULL COMMENT '年龄段',
  `age` int(11) DEFAULT NULL COMMENT ' 年龄',
  `birthday` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机',
  `is_dead` bit(1) DEFAULT b'0' COMMENT '是否死亡',
  `dead_date` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `dead_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '死亡地点',
  `tomb_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '埋葬地点',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电邮',
  `country_id` int(11) DEFAULT NULL COMMENT '国家',
  `province_id` int(11) DEFAULT NULL COMMENT '省份',
  `city_id` int(11) DEFAULT NULL COMMENT '城市',
  `district_id` int(11) DEFAULT NULL COMMENT '区/县',
  `business_zone_id` int(11) DEFAULT NULL COMMENT '商业区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `store_id` int(11) DEFAULT NULL COMMENT '门店',
  `owner_id` int(11) DEFAULT '0' COMMENT '所有者ID',
  `status_id` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` int(11) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用5',
  `col6` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用6',
  `col7` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用7',
  `col8` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用8',
  `col9` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用9',
  `col10` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用10',
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='会员基础信息表';

-- ----------------------------
-- Records of member_basic_info
-- ----------------------------
INSERT INTO `member_basic_info` VALUES ('1', '1', null, '1', null, 'admin', '123456', null, null, null, null, null, null, null, null, null, null, '田氏族谱', null, null, null, null, null, null, null, null, '', null, '\0', '', '0', '0', null, null, null, null, null, null, null, null, null, '0', null, '2020-02-26 10:35:20', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('2', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '田红1', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAYAAACohjseAAACSUlEQVRoQ+2Wy2sTYRTFzx3j5AGt1q0gtoqiCLpSq0sdKTbbRhQxwZUuWuk/orRd6LIjopguDSJGcSN97NoiLYhpQXDrC+mY55UJXTg642dQmpv0ZpvL4Zzzu8n9CF3+oS7PBw3Y6YSVoBIU3oCuqHBARntK0FiR8AElKByQ0Z4SNFYkfEAJCgdktKcEjRUJH1CCwgEZ7W1PgkdH8nY5tecGwJcZOObXRMAbgB7FNz7eW5nJVIzVbQ60W+s3goeuvdxbo0YBwImIEIsxttJv75/7YAopQSsQ0G/7e6pvwQ/HwByI79hW6pkfpNLYGALTOAGDYFqydlVOvZu6WI4KKUUrEPBArjjKjEk/XL1aHn7/MP3p5wD7rhT6YjG7AKIzRBgrTTtTUQGlaAUDZotzDJxm4NK66+TDzPdnixkCHhMwX3KdwciAQrQCAQeyRX/lbKu3mohav/25VwmLax6A8prrJKICStFqOeDh6697qnXvK4Bva67T8y8Bt0IrfEWJM+vTF2ZCVzT3fISY8n+9om3W+iXgizEGT4B5tlarpEP/ZHbGnwA4S6BbJff8ZPRvUIZWIODB0afxxhd7AcTHm2eC6XbS29E8E16yPkRojPv/oACWrd7qyT+dCSlarR96pqUYaPi/HPot0Ap9izaPdHL3TRBdBXBkcw1Xwfwg4X2+2+pTrZ1a2/OxbXpjdtL3SrCTaIV5VYJKUHgDuqLCARntKUFjRcIHlKBwQEZ7StBYkfABJSgckNGeEjRWJHxACQoHZLTX9QR/AGtb9Uh46HJUAAAAAElFTkSuQmCC', '', '', '', null, null, null, null, '', '', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('3', '1', '1', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, '王敏1', 'data:image/png;base64,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', '', '', '', null, '1', null, null, '', '', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('4', '1', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '田学1', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAYAAACohjseAAADA0lEQVRoQ+2ZT0gUcRTHv2921w37Jx2iTE9GEOoll3WiQ1QgFOiu/YEIojp58mAEkpbjTERhBOEhqEsdCoWtxV2kS5GXip00xTz2jyIMwYIiCHNnXky1sLrauOyOs2u/Oc68ee995n1+vzn8CCv8ohXOBwFY7BMWExQTLPAv8P8pejChVBjAFRDvZsbmAh9QWnv0GhIG/aU4H6lRv6cezJngX7hxBm8oHrB5nRLG/aspGKlRf1pP5gCGEkofg48S6IEHaInK6sdiAGVmOjzaXZuc5X4GtksSzgwEtauZgHrXpKWlF1RZLHDpA2hOqCEDxgCIBuP1amMGYFOii62bcVkrys0nnNDqTCRHCHgRk7WAACyG9Zfeo5igWIMF7qxQVCgqFHX3C4g1KNaguwbaVheKCkVtJXE3QCgqFHXXQNvqQlGhqK0k7gYIRYWi7hpoW10oKhS1lcTdAHtFdeUrmNetBa2/K6vf3G03++pNCXUPYDwmwlCsXtu70OnSI2bsg0SX4kG1I/sS7r3R+qrX/+HLdIQZjSDqider7RmAzbq204TxhJklEN4RYHs+6CE6GQ2qb+ejhXS1GTDalgOZmYiAbQzeSITPpaX+6r7azqkMQOtGaFg5ABM3mLliKc0R6HJMVs+mxx56rlUnTSPB4DVLyZG/GBr1+XDqfp36MpVz0XPA8MiFKja4fLHiREaVafItAFObvFsqbwZaZq3Y8JhSZs5gGOCtBOoHea7nD2DhTJLHYELJm2ig89P8iJwOOkO6MsHMNQQ6EpPVewor0pjOgwD2E2GspLxsV6Ty9A+nAf+VPyfAsN7VajJ6ATyMy1pDSFcuMnMHiKa9Pl8guuPcezfhFlyD2TRk6cgzPMmEVQDawegBKAlIDXFZGcoml1OxOU3QaqpJ774NNk+kGpRAbQOyes2phrPNmzOg9WsxOPnsT2G6E5fV49k24WR8zoC/fy26MgHwbCFsKnndRVPJQnr3MY/P+7QQNhVHAJ1ULNfceVE01yacfF8AOvl1lyP3L9r1FFfgrmW8AAAAAElFTkSuQmCC', '', '', '', null, '0', null, null, '', '', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('5', '1', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '田若1', '', '', '', '', null, '1', null, null, '', '', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('6', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '田红2', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAYAAACohjseAAAE4UlEQVRoQ+2afWxTVRjGn/e2q5sLQacSPoRg7whGiShZoqAyxm4TkSXwD8Ttj7XbVIYmolFjFMwEgyaSKGhQA5tbawgkJMTFBI3tKPiBxAQM8uWktyNBGSEEEgxMaHsfcztKln3Z3bZbO3v+7XnfPr/zPPfec9sjGOdDxjkf8oC57vCgDpYGn72XscgmkuUApuQAZLeIHBBbweuhiuY/++odAGjCGdHIUYAlOQDWT6JcUuwFc/tCDgBUA+6dJJ8RYK/YHav6r0g2Qt9M3DaSSwSyU3d5axI6BwA6/bXnzFgqdsf0XIBLgPQm78ZZAN1hl2/qcIA0Pwy7fDl3h3X6awdoH8zBPGA2XoempryDQ61CtjrWX1fewbyDWZ7VUY2oM1hfJkZsGYgyAlMFsBM8J1BOAEb7vMqq73fLylg612xUANV97sdhcBOJ+cOKF/wBRXkzvLhtT7ogMw6odrjXknwXhIjIEQBegRKwFcrZEmVi9ML1K9NoRJ8EUQ3SZYIpkFbHlJmNJ+esv5EqaEYB1YBnM2msEeAKoazWtdadIhLfFQ02Sv2eRQYML4AZIvLtvMqlValGNmOApX5PvQGjBSLnbQUFi0+XN59Kxg31p8ZJ7Ln2HYi5EHwY1nyvJlM31JyMAD54sKGk51pUF6BYbLIwVNF2aCQi1QN103HDOEzgrgLhI52a77eR1PedmxFANeDZSBpvicjHuuZdY0WcGqhdReJzEbTrmm+5lR4Z24uqAXeIoPM2R+G0Uwu3dVsRtyjYZD8b7foLwB32IvvdnU988beVPml3UA02lDIaOS2QQ7rLO/xj4T8UOwPuZpANCmzLQ67W9qwAnOWv02KI+QXYrrt8z1sRlahxBmrXgNgMwcthzbfFSq+0O+j0e2oAY4dANuou7zorom4BpqFX2gHVjtolNLAXgk/Dmu/FVABLA54XDBpbReQNXfN+YKVX+gGDDXMYjRyDIBDWfPGdidWhBtxbSL4kolTrWtsuK33SDtjEJuXLjjPnhLxTJky8J/TYJ1esCDNr1IBbJ3hfoU2ZerKi7byVPmkHjAvzuz8j2KgI1oY033tWhJX665YZiH0FkR/CmnehlR4Zew7eH/TMjET5O8Drdofj4c7y5q6RCJz9Y/2EaE/sMMFZouBpvdL3zUjqM76T6Y1X7QYSbwtwvLC4oPzEgpZLyYh84HiT45/uM3sALgWwO+zyrUymbqg5GYmo+WW912JXO4gqEdGF9hUhV8uvw4mN70EjsV0kFpjzRLFV6pWt+7IS0BQ1/+ArRReuXmohWA2IIeAO2sQ7Q2Ye2F+xPmrOISmzO+rKDLLGEDSCLLwFJLisiE0LVbaa75GWRsYc7KtG7fA8R3IDyMlxZ0QiJM2/t6IEp4Aois8XOS/A+3FwcLP5kowUIUcF0BT80NHXiq9evLgC5DKAZQAmE7CBchnCIwL5etLtJdt/XvBRT+/dz70awq2pQo4aYP98mdGs2P+OLRHVwfKXDsgxA0z2gkoVMusBU41rTgAOhJRfwpr30WRSkDOACUgB1hHyVNjVdmzcASaem8P99NgfOqccTMaxPKC5n0hmFays5ljU5CPae/caeBRjLNyw8p15B286OL5POuX8WTWRXbrmrU5E/P932tAkH9fnRa3cvbK5JudOFI50Mcc94L96bFtmc62mrAAAAABJRU5ErkJggg==', '', '', '', null, '0', null, null, '', '', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('7', null, '1', '6', null, null, null, null, null, null, null, null, null, null, null, null, null, '田汉', '', '田汉', '', '4305632152512', null, '0', null, null, '1991-3-3', '13638536221', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('8', null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '田安安', '', '田安安', '田安安', '430563215213232', null, '1', null, null, '1991-3-3', '13633332323', '\0', '', '', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('9', null, null, null, null, null, '11', null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, '111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('10', null, null, null, null, null, '111111', null, null, null, null, null, null, null, null, null, null, 'aaaaaaaaaa', null, null, null, null, null, null, null, null, null, '22222', '\0', null, '0', '0', null, null, null, null, null, null, null, null, null, '0', null, '2020-03-06 17:10:07', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('11', null, null, null, null, null, '111111', null, null, null, null, null, null, null, null, null, null, 'aaaaaaaaaa', null, null, null, null, null, null, null, null, null, '22222', '\0', null, '0', '0', null, null, null, null, null, null, null, null, null, '0', null, '2020-03-06 17:13:58', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `member_basic_info` VALUES ('12', null, null, null, null, null, '111111', null, null, null, null, null, null, null, null, null, null, 'aaaaaaaaaa', null, null, null, null, null, null, null, null, null, '22222', '\0', null, '0', '0', null, null, null, null, null, null, null, null, null, '0', null, '2020-03-06 17:15:14', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for member_rand_code
-- ----------------------------
DROP TABLE IF EXISTS `member_rand_code`;
CREATE TABLE `member_rand_code` (
  `member_rand_code_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `mobile_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '验证码',
  `status_id` int(11) DEFAULT NULL COMMENT '状态',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序0-10置顶排序',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`member_rand_code_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='短信认证表';

-- ----------------------------
-- Records of member_rand_code
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '公告Id',
  `notice_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告名称',
  `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '公告内容',
  `owner_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` int(11) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', null, '2020-03-17 17:40:35', null, null, null, '0', null, null);
INSERT INTO `notice` VALUES ('2', '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', null, '2020-03-17 17:43:59', null, null, null, '0', null, null);
INSERT INTO `notice` VALUES ('3', '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', null, '2020-03-17 17:45:31', null, null, null, '0', null, null);
INSERT INTO `notice` VALUES ('4', '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', null, '2020-03-17 17:45:58', null, null, null, '0', null, null);
INSERT INTO `notice` VALUES ('5', '关于梨花加入家族的通知', '欢迎梨花加入田氏家族', null, '2020-03-17 17:47:30', null, null, null, '0', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `parent_role_id` bigint(20) DEFAULT '0' COMMENT '角色父ID',
  `role_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色类型',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` bigint(20) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` bigint(20) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '0', null, '超级管理员', null, '0', '2020-03-18 15:54:50', null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', '0', null, '分支管理员', null, '0', '2020-03-18 15:54:50', null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('3', '0', null, '注册用户', null, '0', '2020-03-18 15:54:50', null, null, null, '0', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for weixin_user
-- ----------------------------
DROP TABLE IF EXISTS `weixin_user`;
CREATE TABLE `weixin_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '微信用户ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `group_id` int(11) DEFAULT NULL COMMENT '分组ID',
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'OpenID',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `language` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '语言',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市',
  `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '国家',
  `head_image_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `union_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联合ID',
  `subscribe_time` datetime DEFAULT NULL COMMENT '提交时间戳',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `privilege` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限',
  `is_web_auth` int(11) DEFAULT NULL COMMENT '是否是网页授权用户',
  `owner_id` int(11) DEFAULT '0' COMMENT '所有者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_modify_by_id` int(11) DEFAULT NULL COMMENT '最后更新人',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_by_id` int(11) DEFAULT NULL COMMENT '删除人',
  `col1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用1',
  `col2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用2',
  `col3` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用3',
  `col4` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用4',
  `col5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备用5',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='微信用户表';

-- ----------------------------
-- Records of weixin_user
-- ----------------------------
INSERT INTO `weixin_user` VALUES ('1', '111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2020-03-06 17:13:58', null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `weixin_user` VALUES ('2', '111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2020-03-06 17:15:14', null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `weixin_user` VALUES ('3', '1111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `weixin_user` VALUES ('4', null, null, null, 'aaaa', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `weixin_user` VALUES ('5', null, null, null, 'aaaa', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null);
