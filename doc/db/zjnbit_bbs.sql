/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : zjnbit_bbs

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/04/2023 16:47:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `group_id` bigint DEFAULT NULL COMMENT '分组ID',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限名称',
  `permission_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限地址',
  `permission_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_permission_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端权限';

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
BEGIN;
INSERT INTO `auth_permission` (`id`, `group_id`, `permission_code`, `permission_name`, `permission_path`, `permission_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495680647895826434, 1495680382488657922, 'role-add', '添加角色', '/admin/role/add', '添加角色', 1481796949110722562, '2022-02-21 16:43:50', 1481796949110722562, '2022-02-21 16:43:50', 1, 0);
INSERT INTO `auth_permission` (`id`, `group_id`, `permission_code`, `permission_name`, `permission_path`, `permission_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495680987730808834, 1495680382488657922, 'permission-add', '添加权限', '/admin/permission/add', '添加权限', 1481796949110722562, '2022-02-21 16:45:11', 1481796949110722562, '2022-02-21 16:45:11', 1, 0);
INSERT INTO `auth_permission` (`id`, `group_id`, `permission_code`, `permission_name`, `permission_path`, `permission_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495681072694824962, 1495680382488657922, 'permission-addGroup', '添加权限组', '/admin/permission/addGroup', '添加权限组', 1481796949110722562, '2022-02-21 16:45:31', 1481796949110722562, '2022-02-21 16:45:31', 1, 0);
INSERT INTO `auth_permission` (`id`, `group_id`, `permission_code`, `permission_name`, `permission_path`, `permission_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495683648790208513, 1495680382488657922, 'role-get', '获取单个角色', '/admin/role/get', '获取单个角色', 1481796949110722562, '2022-02-21 16:55:45', 1481796949110722562, '2022-02-21 16:55:45', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for auth_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission_group`;
CREATE TABLE `auth_permission_group` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分组名称',
  `group_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_permission_group_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端权限分组';

-- ----------------------------
-- Records of auth_permission_group
-- ----------------------------
BEGIN;
INSERT INTO `auth_permission_group` (`id`, `group_name`, `group_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495680382488657922, 'default', '默认分组', 1481796949110722562, '2022-02-21 16:42:46', 1481796949110722562, '2022-02-21 16:42:46', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_role_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端角色表';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_role` (`id`, `role_code`, `role_name`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495666350796849154, 'root', '超级管理员', 0, '2022-02-21 15:47:01', 0, '2022-02-21 15:47:01', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_role_permission_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联表';

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495682889189187585, 1495666350796849154, 1495680647895826434, 1481796949110722562, '2022-02-21 16:52:44', 1481796949110722562, '2022-02-21 16:52:44', 1, 0);
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495682890355204097, 1495666350796849154, 1495680987730808834, 1481796949110722562, '2022-02-21 16:52:44', 1481796949110722562, '2022-02-21 16:52:44', 1, 0);
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495682890397147138, 1495666350796849154, 1495681072694824962, 1481796949110722562, '2022-02-21 16:52:44', 1481796949110722562, '2022-02-21 16:52:44', 1, 0);
INSERT INTO `auth_role_permission` (`id`, `role_id`, `permission_id`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495683761440825345, 1495666350796849154, 1495683648790208513, 1481796949110722562, '2022-02-21 16:56:12', 1481796949110722562, '2022-02-21 16:56:12', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_user_role_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理端用户角色表';

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_user_role` (`id`, `user_id`, `role_id`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1495669595694964738, 1641433038623604737, 1495666350796849154, 1481796949110722562, '2022-02-21 15:59:55', 1481796949110722562, '2022-02-21 15:59:55', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for bbs_attach
-- ----------------------------
DROP TABLE IF EXISTS `bbs_attach`;
CREATE TABLE `bbs_attach` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `attach_path` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `oss_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'OSS上的地址',
  `cdn_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'CDN地址',
  `mime_type` varchar(31) COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='论坛附件表';

-- ----------------------------
-- Records of bbs_attach
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_node
-- ----------------------------
DROP TABLE IF EXISTS `bbs_node`;
CREATE TABLE `bbs_node` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父节点ID，0为顶级节点，0或非0',
  `node_code` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点编码',
  `node_group_id` bigint NOT NULL COMMENT '节点组ID',
  `node_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `node_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'https://dn-qiniu-avatar.qbox.me/avatar' COMMENT '图标',
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否展示(1开放，0不开放)',
  `is_publish` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开放发帖功能(1开放，0关闭)',
  `sort` int NOT NULL COMMENT '排序，从小到大',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `bbs_node_node_code_uindex` (`node_code`),
  KEY `bbs_node_node_group_id_index` (`node_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='论坛节点表';

-- ----------------------------
-- Records of bbs_node
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_node_group
-- ----------------------------
DROP TABLE IF EXISTS `bbs_node_group`;
CREATE TABLE `bbs_node_group` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分钟名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  `sort` int NOT NULL DEFAULT '1' COMMENT '排序，从小到达',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据更新者ID',
  `update_time` datetime NOT NULL COMMENT '数据更新时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='论坛节点分组';

-- ----------------------------
-- Records of bbs_node_group
-- ----------------------------
BEGIN;
INSERT INTO `bbs_node_group` (`id`, `group_name`, `remark`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1564186782705143809, 'code', '编程', 1, 1560475574944722946, '2022-08-29 17:42:45', 1560475574944722946, '2022-08-31 13:58:22', 1, 0);
INSERT INTO `bbs_node_group` (`id`, `group_name`, `remark`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1564190136395141122, 'work', '工作', 2, 1560475574944722946, '2022-08-29 17:56:04', 1560475574944722946, '2022-08-29 17:56:04', 1, 0);
INSERT INTO `bbs_node_group` (`id`, `group_name`, `remark`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1564190165302284290, 'game', '游戏', 2, 1560475574944722946, '2022-08-29 17:56:11', 1560475574944722946, '2022-08-29 17:56:11', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for bbs_oss_type
-- ----------------------------
DROP TABLE IF EXISTS `bbs_oss_type`;
CREATE TABLE `bbs_oss_type` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `oss_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `oss_type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型：''aliyun'',''tencent'',''amazon''',
  `bucket_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prefix` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'BUCKET/prefix路径前缀',
  `oss_host` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cdn_host` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_pub_read` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否公共读',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='对象存储配置';

-- ----------------------------
-- Records of bbs_oss_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_reply
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply`;
CREATE TABLE `bbs_reply` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `topic_id` bigint NOT NULL COMMENT '主题ID',
  `parent_reply_id` bigint NOT NULL DEFAULT '0' COMMENT '回复ID(非0为回复中的回复)',
  `quote_reply_id` bigint NOT NULL DEFAULT '0' COMMENT '引用的回复ID',
  `post_user_id` bigint NOT NULL COMMENT '发布者ID',
  `post_user_nickname` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '发布者昵称',
  `post_time` datetime NOT NULL COMMENT '发布时间',
  `post_ip` varchar(15) COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布IP',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞统计',
  `reply_content` varchar(511) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '回复内容',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否匿名',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `bbs_reply_topic_id_index` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回复表';

-- ----------------------------
-- Records of bbs_reply
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_reply_attach
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply_attach`;
CREATE TABLE `bbs_reply_attach` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `reply_id` bigint NOT NULL COMMENT '回复ID',
  `attach_id` bigint NOT NULL COMMENT '附件ID',
  `attach_name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件名称',
  `attach_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件地址',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回复附件表';

-- ----------------------------
-- Records of bbs_reply_attach
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_reply_like
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply_like`;
CREATE TABLE `bbs_reply_like` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `reply_id` bigint NOT NULL COMMENT '回复ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回复点赞表';

-- ----------------------------
-- Records of bbs_reply_like
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_sys_conf
-- ----------------------------
DROP TABLE IF EXISTS `bbs_sys_conf`;
CREATE TABLE `bbs_sys_conf` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `conf_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `conf_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='论坛系统配置项';

-- ----------------------------
-- Records of bbs_sys_conf
-- ----------------------------
BEGIN;
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431640039426, 'aliyun:security:access-key', '123456', 1481796949110722562, '2022-02-21 16:43:50', 1481796949110722562, '2022-02-21 16:43:50', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431723925505, 'aliyun:security:access-key-secret', '123456', 1481796949110722562, '2022-02-21 16:45:11', 1481796949110722562, '2022-02-21 16:45:11', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431779823618, 'aliyun:oss:bucket', 'zjnbit', 1481796949110722562, '2022-02-21 16:45:31', 1481796949110722562, '2022-02-21 16:45:31', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431786840065, 'aliyun:oss:endpoint', 'oss-cn-hongkong.aliyuncs.com', 1481796949110722562, '2022-02-21 16:55:45', 1481796949110722562, '2022-02-21 16:55:45', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431818543106, 'aliyun:oss:bucket-url', 'abc.oss-cn-hongkong.aliyuncs.com', 1481796949110722562, '2022-02-21 16:45:11', 1481796949110722562, '2022-02-21 16:45:11', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431876292609, 'aliyun:oss:cdn-url', 'abc.abc.com', 1481796949110722562, '2022-02-21 16:45:31', 1481796949110722562, '2022-02-21 16:45:31', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1554036431902429185, 'aliyun:oss:callback-url', 'https://bbs.abc.com/api/callback/oss/aliyun', 1481796949110722562, '2022-02-21 16:55:45', 1481796949110722562, '2022-02-21 16:55:45', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1560431909987777561, 'auth:secret:aes-key', '123456', 1481796949110722562, '2022-08-19 09:03:14', 1481796949110722562, '2022-08-19 09:03:14', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1560431909987778561, 'auth:cas:client-id', '123456', 1481796949110722562, '2022-08-19 09:04:14', 1481796949110722562, '2022-08-19 09:04:14', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1560432243307655170, 'auth:cas:client-secret', '123456', 1481796949110722562, '2022-08-19 09:04:14', 1481796949110722562, '2022-08-19 09:04:14', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1560436488048160770, 'auth:cas:server-url', 'https://auth.abc.com', 1481796949110722562, '2022-08-19 09:04:14', 1481796949110722562, '2022-08-19 09:04:14', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1575322518842699777, 'bbs:topic:post-status-default', 'open', 1481796949110722562, '2022-09-29 11:13:47', 1481796949110722562, '2022-09-29 11:13:51', 1, 0);
INSERT INTO `bbs_sys_conf` (`id`, `conf_key`, `conf_value`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1575356982457200641, 'bbs:node:post-can-anonymous', '1', 1481796949110722562, '2022-09-29 13:30:48', 1481796949110722562, '2022-09-29 13:30:52', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for bbs_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `bbs_sys_dict`;
CREATE TABLE `bbs_sys_dict` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典键',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `dict_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `bbs_sys_dict_dict_name_dict_key_uindex` (`dict_name`,`dict_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of bbs_sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `bbs_sys_dict` (`id`, `dict_name`, `dict_key`, `dict_value`, `dict_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1562325777530957826, 'userbase:sex', 'm', '男', '性别:男', 1560475574944722946, '2022-08-24 14:30:22', 1560475574944722946, '2022-08-24 14:30:22', 1, 0);
INSERT INTO `bbs_sys_dict` (`id`, `dict_name`, `dict_key`, `dict_value`, `dict_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1562325785350602753, 'userbase:sex', 'f', '女', '性别:女', 1560475574944722946, '2022-08-24 14:30:22', 1560475574944722946, '2022-08-24 14:30:22', 1, 0);
INSERT INTO `bbs_sys_dict` (`id`, `dict_name`, `dict_key`, `dict_value`, `dict_remark`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1562325785367379969, 'userbase:sex', 'n', '未知', '性别:未知', 1560475574944722946, '2022-08-24 14:30:22', 1560475574944722946, '2022-08-24 14:30:22', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for bbs_topic
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic`;
CREATE TABLE `bbs_topic` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `node_id` bigint NOT NULL COMMENT '节点ID',
  `status` varchar(15) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'open' COMMENT '状态(open:开启,closed:关闭,locked:锁定,auditing:审核中)',
  `status_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '异常详情',
  `topic_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题名称',
  `post_user` bigint NOT NULL COMMENT '发布者ID',
  `post_user_nickname` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布者昵称',
  `post_time` datetime NOT NULL COMMENT '发布时间',
  `post_ip` varchar(15) COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布者IP',
  `last_reply_user` bigint NOT NULL COMMENT '最后回复用户ID',
  `last_reply_user_nickname` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '最后回复用户昵称',
  `last_reply_time` datetime NOT NULL COMMENT '最后回复时间',
  `last_reply_ip` varchar(15) COLLATE utf8mb4_general_ci NOT NULL COMMENT '最后回复IP',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否匿名',
  `reply_count` int NOT NULL DEFAULT '0' COMMENT '回复数量',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `favorite_count` int NOT NULL DEFAULT '0' COMMENT '收藏数量',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='论坛主题';

-- ----------------------------
-- Records of bbs_topic
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_topic_attach
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic_attach`;
CREATE TABLE `bbs_topic_attach` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `topic_id` bigint NOT NULL COMMENT '主题ID',
  `attach_id` bigint NOT NULL COMMENT '附件ID',
  `attach_name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件名称',
  `attach_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件地址',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='主题附件';

-- ----------------------------
-- Records of bbs_topic_attach
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_topic_content
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic_content`;
CREATE TABLE `bbs_topic_content` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `topic_id` bigint NOT NULL COMMENT '主题ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题内容',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `bbs_topic_content_topic_id_index` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='主题内容表';

-- ----------------------------
-- Records of bbs_topic_content
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_topic_like
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic_like`;
CREATE TABLE `bbs_topic_like` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `topic_id` bigint NOT NULL COMMENT '主题ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `bbs_topic_like_user_id_topic_id_uindex` (`user_id`,`topic_id`),
  KEY `bbs_topic_like_topic_id_is_valid_index` (`topic_id`,`is_valid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='主题点赞表';

-- ----------------------------
-- Records of bbs_topic_like
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user_favorite`;
CREATE TABLE `bbs_user_favorite` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `favorite_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收藏夹名称',
  `favorite_count` int NOT NULL DEFAULT '0' COMMENT '收藏数量',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户收藏夹';

-- ----------------------------
-- Records of bbs_user_favorite
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_user_favorite_topic
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user_favorite_topic`;
CREATE TABLE `bbs_user_favorite_topic` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `favorite_id` bigint NOT NULL DEFAULT '0' COMMENT '收藏夹ID',
  `topic_id` bigint NOT NULL COMMENT '主题ID',
  `topic_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主题名称',
  `post_user_id` bigint NOT NULL COMMENT '发布者ID',
  `post_user_name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布者昵称',
  `post_time` datetime NOT NULL COMMENT '发布时间',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户收藏的主题';

-- ----------------------------
-- Records of bbs_user_favorite_topic
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bbs_user_follow
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user_follow`;
CREATE TABLE `bbs_user_follow` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `follow_user_id` bigint NOT NULL COMMENT '关注的用户的ID',
  `is_follow` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否关注',
  `follow_time` datetime NOT NULL COMMENT '关注时间',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户关注列表';

-- ----------------------------
-- Records of bbs_user_follow
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for log_request
-- ----------------------------
DROP TABLE IF EXISTS `log_request`;
CREATE TABLE `log_request` (
  `id` bigint NOT NULL,
  `request_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `uri` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ua` text COLLATE utf8mb4_general_ci,
  `path_param` text COLLATE utf8mb4_general_ci,
  `body_param` text COLLATE utf8mb4_general_ci,
  `ip` bigint DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `consumed_time` int DEFAULT NULL,
  `response_code` varchar(31) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `response_msg` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `response_data` text COLLATE utf8mb4_general_ci,
  `create_user` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int NOT NULL DEFAULT '0',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '仅支持中国11位手机号',
  `email` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱地址',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '加密后的密码',
  `nickname` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `gender` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'N' COMMENT '性别(M:男性,F:女性,N:未知)',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像，URL地址',
  `score` int NOT NULL DEFAULT '0' COMMENT '积分',
  `status` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'normal' COMMENT '状态',
  `level` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'normal' COMMENT '级别',
  `is_activated` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否激活',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_base_username_uindex` (`username`) USING BTREE,
  UNIQUE KEY `user_base_email_uindex` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户基础表';

-- ----------------------------
-- Records of user_base
-- ----------------------------
BEGIN;
INSERT INTO `user_base` (`id`, `mobile`, `email`, `username`, `password`, `nickname`, `gender`, `avatar`, `score`, `status`, `level`, `is_activated`, `create_user`, `create_time`, `update_user`, `update_time`, `version`, `is_deleted`) VALUES (1641433038623604737, NULL, '376102636@qq.com', 'root', '$2a$10$6Es0WbA1VlOVQ39iMWcZcOlP4DGpTrMRBSERXR3CkyxbH.SP3O79G', 'root', 'N', 'https://oss.zjnbit.com/static/default_avatar.jpeg', 0, 'normal', 'normal', 0, 0, '2023-03-30 21:31:47', 0, '2023-03-30 21:31:47', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_score_item
-- ----------------------------
DROP TABLE IF EXISTS `user_score_item`;
CREATE TABLE `user_score_item` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `item_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '积分项名称',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='积分项';

-- ----------------------------
-- Records of user_score_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_score_record
-- ----------------------------
DROP TABLE IF EXISTS `user_score_record`;
CREATE TABLE `user_score_record` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `modify_score` int NOT NULL COMMENT '发生的积分',
  `before_score` int NOT NULL COMMENT '发生前积分',
  `result_score` int NOT NULL COMMENT '发生后积分',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户积分明细';

-- ----------------------------
-- Records of user_score_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_social
-- ----------------------------
DROP TABLE IF EXISTS `user_social`;
CREATE TABLE `user_social` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID，user_base.id',
  `social_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方应用标识',
  `social_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方应用(wxma:小程序,qq:QQ,wxmp:微信公众号)',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方内的用户标识',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '多应用统一标识',
  `user_secret_key` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '第三方应用内的用户密钥',
  `userinfo` json DEFAULT NULL COMMENT '第三方应用内的用户信息',
  `create_user` bigint NOT NULL COMMENT '数据创建者ID',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_user` bigint NOT NULL COMMENT '数据修改者ID',
  `update_time` datetime NOT NULL COMMENT '数据修改时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '数据版本',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_social_social_id_social_type_user_id_uindex` (`social_id`,`social_type`,`user_id`) USING BTREE,
  UNIQUE KEY `user_social_social_id_social_type_open_id_uindex` (`social_id`,`social_type`,`open_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户社会化登录';

SET FOREIGN_KEY_CHECKS = 1;
