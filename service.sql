/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : localhost:3306
 Source Schema         : fnk-service

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 25/12/2023 18:14:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
                              `id` varchar(32) NOT NULL COMMENT 'id',
                              `phone` varchar(32) NOT NULL COMMENT '手机号',
                              `password` varchar(255) NOT NULL COMMENT '密码',
                              `salt` varchar(32) NOT NULL COMMENT '盐',
                              `username` varchar(255) DEFAULT NULL COMMENT '用户名称',
                              `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
                              `sex` varchar(2) DEFAULT NULL COMMENT '用户性别',
                              `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
                              `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
                              `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态;0正常 1不可用',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
BEGIN;
INSERT INTO `admin_user` (`id`, `phone`, `password`, `salt`, `username`, `avatar`, `sex`, `login_ip`, `dept_id`, `status`, `create_time`, `update_time`, `deleted`) VALUES ('1736671483388059649', '18888888888', '4066604c3f5a33d64aada1466abc55cf', 'Q48G', 'admin', NULL, '0', '', NULL, 1, '2023-12-18 16:55:22', '2023-12-18 16:55:22', 0);
INSERT INTO `admin_user` (`id`, `phone`, `password`, `salt`, `username`, `avatar`, `sex`, `login_ip`, `dept_id`, `status`, `create_time`, `update_time`, `deleted`) VALUES ('1737764212557844482', '18100000000', '1498b77199645ec7cb68c9a40e013eeb', 'A1KM', '游客', NULL, '0', NULL, NULL, 1, '2023-12-21 17:17:29', '2023-12-21 17:20:05', 1);
INSERT INTO `admin_user` (`id`, `phone`, `password`, `salt`, `username`, `avatar`, `sex`, `login_ip`, `dept_id`, `status`, `create_time`, `update_time`, `deleted`) VALUES ('1739226400963280898', '18800000000', 'e500074630aba91e8b346c09c14387d4', '5LW5', 'guest', NULL, '0', NULL, NULL, 1, '2023-12-25 18:07:42', '2023-12-25 18:07:42', 0);
COMMIT;

-- ----------------------------
-- Table structure for dept_info
-- ----------------------------
DROP TABLE IF EXISTS `dept_info`;
CREATE TABLE `dept_info` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `root_id` varchar(255) DEFAULT NULL COMMENT '上级ID',
                             `all_root_id` json DEFAULT NULL COMMENT '所有的上级ID集合列表',
                             `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
                             `order_sort` int(2) DEFAULT NULL COMMENT '排序',
                             `leader` varchar(255) DEFAULT NULL COMMENT '管理用户名称',
                             `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
                             `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                             `status` tinyint(1) DEFAULT '0' COMMENT '状态',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门信息';

-- ----------------------------
-- Records of dept_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for post_info
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `name` varchar(255) DEFAULT NULL COMMENT '名称',
                             `key_word` varchar(90) DEFAULT NULL COMMENT '岗位key',
                             `order_sort` int(2) DEFAULT NULL COMMENT '排序',
                             `status` tinyint(1) DEFAULT '0' COMMENT '状态',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='岗位信息';

-- ----------------------------
-- Records of post_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role_dept
-- ----------------------------
DROP TABLE IF EXISTS `role_dept`;
CREATE TABLE `role_dept` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `role_id` varchar(255) NOT NULL COMMENT '角色ID',
                             `dept_id` varchar(255) NOT NULL COMMENT '部门ID',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `role_dept_index` (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色关联部门';

-- ----------------------------
-- Records of role_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `role_name` varchar(255) DEFAULT NULL COMMENT '名称',
                             `role_key` varchar(90) DEFAULT NULL COMMENT '角色key',
                             `order_sort` int(4) DEFAULT NULL COMMENT '显示排序',
                             `role_scope` json DEFAULT NULL COMMENT '角色数据范围',
                             `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态;0',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `role_key` (`role_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色信息';

-- ----------------------------
-- Records of role_info
-- ----------------------------
BEGIN;
INSERT INTO `role_info` (`id`, `role_name`, `role_key`, `order_sort`, `role_scope`, `status`, `create_time`, `update_time`, `deleted`) VALUES ('1737408771218792450', '超级管理员', 'SuperAdmin', 1, '[\"173736381381221581\", \"1737363813812215810\", \"173736381381221583\", \"1737364028824821762\", \"1737369714325528577\", \"1738099497552429057\", \"1739115016615215105\", \"1739223870027972609\", \"1739224144796827649\", \"1739224376339185665\", \"1739224547890413570\", \"1739224770612150273\", \"1739225110254305282\", \"1739225241577963521\", \"1739225337644302337\", \"1739225546147348481\"]', 1, '2023-12-20 17:45:05', '2023-12-20 17:45:05', 0);
INSERT INTO `role_info` (`id`, `role_name`, `role_key`, `order_sort`, `role_scope`, `status`, `create_time`, `update_time`, `deleted`) VALUES ('1737760149430677506', 'test', 'test', 3, '[\"173736381381221581\", \"173736381381221583\", \"1737363813812215810\", \"1737369714325528577\"]', 1, '2023-12-21 17:01:20', '2023-12-21 17:01:20', 1);
INSERT INTO `role_info` (`id`, `role_name`, `role_key`, `order_sort`, `role_scope`, `status`, `create_time`, `update_time`, `deleted`) VALUES ('1737763732809142273', '游客', 'guest', 2, '[\"1737364028824821762\", \"1739224770612150273\", \"1739225110254305282\", \"1739225241577963521\", \"1739225337644302337\", \"1739225546147348481\", \"1738099497552429057\", \"1739115016615215105\", \"1739223870027972609\", \"1739224144796827649\", \"1739224376339185665\", \"1739224547890413570\"]', 1, '2023-12-21 17:15:35', '2023-12-21 17:15:35', 0);
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `role_id` varchar(255) NOT NULL COMMENT '角色id',
                             `menu_id` varchar(255) NOT NULL COMMENT '菜单ID',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `role_menu_index` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073748848641', '1737408771218792450', '173736381381221581', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073753042945', '1737408771218792450', '1737363813812215810', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073757237249', '1737408771218792450', '173736381381221583', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073765625857', '1737408771218792450', '1737364028824821762', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073774014465', '1737408771218792450', '1737369714325528577', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073778208770', '1737408771218792450', '1738099497552429057', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073778208771', '1737408771218792450', '1739115016615215105', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073778208772', '1737408771218792450', '1739223870027972609', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073778208773', '1737408771218792450', '1739224144796827649', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073782403073', '1737408771218792450', '1739224376339185665', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073782403074', '1737408771218792450', '1739224547890413570', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073782403075', '1737408771218792450', '1739224770612150273', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073782403076', '1737408771218792450', '1739225110254305282', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073786597378', '1737408771218792450', '1739225241577963521', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073786597379', '1737408771218792450', '1739225337644302337', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226073786597380', '1737408771218792450', '1739225546147348481', '2023-12-25 18:06:24', '2023-12-25 18:06:24', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237507059713', '1737763732809142273', '1737364028824821762', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237511254018', '1737763732809142273', '1739224770612150273', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237511254019', '1737763732809142273', '1739225110254305282', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237511254020', '1737763732809142273', '1739225241577963521', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237515448321', '1737763732809142273', '1739225337644302337', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237515448322', '1737763732809142273', '1739225546147348481', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237523836930', '1737763732809142273', '1738099497552429057', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237523836931', '1737763732809142273', '1739115016615215105', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237523836932', '1737763732809142273', '1739223870027972609', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237532225538', '1737763732809142273', '1739224144796827649', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237532225539', '1737763732809142273', '1739224376339185665', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226237532225540', '1737763732809142273', '1739224547890413570', '2023-12-25 18:07:03', '2023-12-25 18:07:03', 0);
COMMIT;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
                               `id` varchar(32) NOT NULL COMMENT 'id',
                               `root_id` varchar(32) NOT NULL COMMENT '上级ID',
                               `name` varchar(255) NOT NULL COMMENT '菜单名称',
                               `route_key` varchar(255) DEFAULT NULL COMMENT '路由key全局唯一',
                               `order_sort` int(3) NOT NULL COMMENT '显示顺序',
                               `is_iframe` tinyint(1) DEFAULT NULL COMMENT '是否为网页',
                               `path` varchar(255) DEFAULT NULL COMMENT '请求路径',
                               `icon` varchar(90) DEFAULT NULL COMMENT 'icones ',
                               `local_icon` varchar(255) DEFAULT NULL COMMENT '本地icon',
                               `visible` tinyint(1) DEFAULT NULL COMMENT '是否显示',
                               `permission` varchar(90) DEFAULT NULL COMMENT '权限标识',
                               `type` varchar(90) NOT NULL COMMENT '菜单类型（table目录 menu菜单 button按钮）',
                               `remark` varchar(900) DEFAULT NULL COMMENT '备注',
                               `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
BEGIN;
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('173736381381221581', '0', '系统管理', 'system', 2, 0, '', 'tdesign:system-setting', NULL, 1, '', 'TABLE', NULL, 0, '2023-12-19 14:38:13', '2023-12-19 14:38:19');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1737363813812215810', '173736381381221581', '角色管理', 'system_role', 2, 0, '/system/role', 'mingcute:safety-certificate-fill', NULL, 1, 'system:role:view', 'MENU', NULL, 0, '2023-12-20 14:46:27', '2023-12-20 14:46:27');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('173736381381221583', '173736381381221581', '菜单管理', 'system_menu', 2, 0, '/system/menu', 'line-md:list-3-filled', NULL, 1, 'system:menu:view', 'MENU', NULL, 0, NULL, '2023-12-20 14:57:03');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1737364028824821762', '0', '首页', 'home', 1, 0, '/home', 'line-md:emoji-smile-wink', NULL, 1, 'home:view', 'MENU', NULL, 0, '2023-12-20 14:47:18', '2023-12-20 14:47:18');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1737369714325528577', '173736381381221581', '用户管理', 'system_user', 2, 0, '/system/user', 'line-md:person-search-twotone', NULL, 1, 'system:user:view', 'MENU', NULL, 0, '2023-12-20 15:09:53', '2023-12-20 15:09:53');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1738099497552429057', '0', '文档管理', 'doc', 5, 0, '', 'line-md:text-box', NULL, 1, '', 'TABLE', NULL, 0, '2023-12-22 15:29:47', '2023-12-22 15:29:47');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739115016615215105', '1738099497552429057', 'Naive UI', 'doc_naive', 5, 1, 'https://www.naiveui.com/zh-CN/os-theme', 'line-md:menu', NULL, 1, 'doc:show', 'MENU', NULL, 0, '2023-12-25 10:45:06', '2023-12-25 10:45:06');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739223870027972609', '0', '组件', 'component', 3, 0, '', 'line-md:coffee-twotone', NULL, 1, '', 'TABLE', NULL, 0, '2023-12-25 17:57:38', '2023-12-25 17:57:38');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739224144796827649', '1739223870027972609', '图标', 'component_icon', 3, 0, '/component/icon', 'line-md:emoji-smile-wink', NULL, 1, 'component:icon:view', 'MENU', NULL, 0, '2023-12-25 17:58:44', '2023-12-25 17:58:44');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739224376339185665', '1739223870027972609', '表单', 'component_form', 3, 0, '/component/form', 'line-md:emoji-smile-wink', NULL, 1, 'component:form:view', 'MENU', NULL, 0, '2023-12-25 17:59:39', '2023-12-25 17:59:39');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739224547890413570', '1739223870027972609', '表单', 'component_table', 3, 0, '/component/table', 'line-md:emoji-smile-wink', NULL, 1, 'component:table:view', 'MENU', NULL, 0, '2023-12-25 18:00:20', '2023-12-25 18:00:20');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739224770612150273', '0', '异常页', 'exception', 4, 0, '', 'line-md:alert-circle', NULL, 1, '', 'TABLE', NULL, 0, '2023-12-25 18:01:13', '2023-12-25 18:01:13');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739225110254305282', '1739224770612150273', '404', 'exception_404', 4, 0, '/exception/404', 'line-md:alert-circle', NULL, 1, '', 'MENU', NULL, 0, '2023-12-25 18:02:34', '2023-12-25 18:02:34');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739225241577963521', '1739224770612150273', '403', 'exception_403', 4, 0, '/exception/403', 'line-md:alert-circle', NULL, 1, '', 'MENU', NULL, 0, '2023-12-25 18:03:05', '2023-12-25 18:03:05');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739225337644302337', '1739224770612150273', '500', 'exception_500', 4, 0, '/exception/500', 'line-md:alert-circle', NULL, 1, '', 'MENU', NULL, 0, '2023-12-25 18:03:28', '2023-12-25 18:03:28');
INSERT INTO `system_menu` (`id`, `root_id`, `name`, `route_key`, `order_sort`, `is_iframe`, `path`, `icon`, `local_icon`, `visible`, `permission`, `type`, `remark`, `deleted`, `create_time`, `update_time`) VALUES ('1739225546147348481', '0', '关于', 'about', 6, 0, '/about', 'line-md:lightbulb-twotone', NULL, 1, 'about:view', 'MENU', NULL, 0, '2023-12-25 18:04:18', '2023-12-25 18:04:18');
COMMIT;

-- ----------------------------
-- Table structure for user_post
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `role_id` varchar(255) NOT NULL COMMENT '角色ID',
                             `post_id` varchar(255) NOT NULL COMMENT '部门ID',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `user_post_index` (`role_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户关联岗位';

-- ----------------------------
-- Records of user_post
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` varchar(32) NOT NULL COMMENT 'id',
                             `user_id` varchar(255) NOT NULL COMMENT '系统用户ID',
                             `role_id` varchar(255) NOT NULL COMMENT '角色ID',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `user_role_index` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `deleted`) VALUES ('1737762638733332482', '1736671483388059649', '1737408771218792450', '2023-12-21 17:11:14', '2023-12-21 17:11:14', 0);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `deleted`) VALUES ('1737764212616564737', '1737764212557844482', '1737763732809142273', '2023-12-21 17:17:29', '2023-12-21 17:17:29', 0);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `deleted`) VALUES ('1739226401051361281', '1739226400963280898', '1737763732809142273', '2023-12-25 18:07:42', '2023-12-25 18:07:42', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
