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

 Date: 16/12/2023 16:08:32
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`
(
    `id`           varchar(32)  NOT NULL COMMENT 'id',
    `phone`        varchar(32)  NOT NULL COMMENT '手机号',
    `password`     varchar(255) NOT NULL COMMENT '密码',
    `salt`         varchar(32)  NOT NULL COMMENT '盐',
    `username`     varchar(255) DEFAULT NULL COMMENT '用户名称',
    `avatar`       varchar(255) DEFAULT NULL COMMENT '用户头像',
    `sex`          varchar(2)   DEFAULT NULL COMMENT '用户性别',
    `login_ip`     varchar(255) DEFAULT NULL COMMENT '登录ip',
    `dept_id`      varchar(32)  DEFAULT NULL COMMENT '部门ID',
    `status`       tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态;0正常 1不可用',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Table structure for dept_info
-- ----------------------------
DROP TABLE IF EXISTS `dept_info`;
CREATE TABLE `dept_info`
(
    `id`           varchar(32) NOT NULL COMMENT 'id',
    `root_id`      varchar(255) DEFAULT NULL COMMENT '上级ID',
    `all_root_id`  json         DEFAULT NULL COMMENT '所有的上级ID集合列表',
    `name`         varchar(255) DEFAULT NULL COMMENT '部门名称',
    `order_sort`   int(2) DEFAULT NULL COMMENT '排序',
    `leader`       varchar(255) DEFAULT NULL COMMENT '管理用户名称',
    `phone`        varchar(255) DEFAULT NULL COMMENT '手机号码',
    `email`        varchar(255) DEFAULT NULL COMMENT '邮箱',
    `status`       tinyint(1) DEFAULT '0' COMMENT '状态',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门信息';

-- ----------------------------
-- Table structure for post_info
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info`
(
    `id`           varchar(32) NOT NULL COMMENT 'id',
    `name`         varchar(255) DEFAULT NULL COMMENT '名称',
    `key_word`     varchar(90)  DEFAULT NULL COMMENT '岗位key',
    `order_sort`   int(2) DEFAULT NULL COMMENT '排序',
    `status`       tinyint(1) DEFAULT '0' COMMENT '状态',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='岗位信息';

-- ----------------------------
-- Table structure for role_dept
-- ----------------------------
DROP TABLE IF EXISTS `role_dept`;
CREATE TABLE `role_dept`
(
    `id`           varchar(32)  NOT NULL COMMENT 'id',
    `role_id`      varchar(255) NOT NULL COMMENT '角色ID',
    `dept_id`      varchar(255) NOT NULL COMMENT '部门ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `role_dept_index` (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色关联部门';

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`
(
    `id`           varchar(32) NOT NULL COMMENT 'id',
    `role_name`    varchar(255) DEFAULT NULL COMMENT '名称',
    `role_key`     varchar(90)  DEFAULT NULL COMMENT '角色key',
    `order_sort`   int(4) DEFAULT NULL COMMENT '显示排序',
    `role_scope`   varchar(1)   DEFAULT NULL COMMENT '角色数据范围',
    `status`       tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态;0',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色信息';

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`
(
    `id`           varchar(32)  NOT NULL COMMENT 'id',
    `role_id`      varchar(255) NOT NULL COMMENT '角色id',
    `menu_id`      varchar(255) NOT NULL COMMENT '菜单ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `role_menu_index` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联';

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`
(
    `id`           varchar(32) NOT NULL COMMENT 'id',
    `root_id`      varchar(32)  DEFAULT NULL COMMENT '上级ID',
    `name`         varchar(255) DEFAULT NULL COMMENT '菜单名称',
    `order_sort`   int(3) DEFAULT NULL COMMENT '显示顺序',
    `url`          varchar(255) DEFAULT NULL COMMENT '请求URL',
    `icon`         varchar(90)  DEFAULT NULL COMMENT '菜单icon',
    `visible`      tinyint(1) DEFAULT NULL COMMENT '是否显示',
    `permission`   varchar(90)  DEFAULT NULL COMMENT '权限标识',
    `is_blank`     tinyint(1) DEFAULT NULL COMMENT '是否新开页面',
    `type`         varchar(90)  DEFAULT NULL COMMENT '菜单类型（table目录 menu菜单 button按钮）',
    `remark`       varchar(900) DEFAULT NULL COMMENT '备注',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统菜单';

-- ----------------------------
-- Table structure for user_post
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post`
(
    `id`           varchar(32)  NOT NULL COMMENT 'id',
    `role_id`      varchar(255) NOT NULL COMMENT '角色ID',
    `post_id`      varchar(255) NOT NULL COMMENT '部门ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `user_post_index` (`role_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户关联岗位';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`           varchar(32)  NOT NULL COMMENT 'id',
    `user_id`      varchar(255) NOT NULL COMMENT '系统用户ID',
    `role_id`      varchar(255) NOT NULL COMMENT '角色ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `user_role_index` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联';

