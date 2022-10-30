/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : geshanzsq_blog

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 31/10/2022 00:03:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_about_me
-- ----------------------------
DROP TABLE IF EXISTS `blog_about_me`;
CREATE TABLE `blog_about_me`  (
  `id` bigint NOT NULL COMMENT '关于 id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `editor_type` tinyint(1) NULL DEFAULT 1 COMMENT '编辑器类型（1 Markdown编辑器，2 富文本编辑器）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '关于我' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_about_me
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章 id',
  `article_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '文章标题',
  `article_description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章描述',
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '封面地址',
  `flag` tinyint(1) NULL DEFAULT 1 COMMENT '标识（1 原创，2 转载，3 整理）',
  `fk_category_id` bigint NULL DEFAULT NULL COMMENT '分类 id',
  `has_appreciation` tinyint(1) NULL DEFAULT 1 COMMENT '赞赏是否开启（1 开启，2 不开启）',
  `has_comment` tinyint(1) NULL DEFAULT 1 COMMENT '评论是否开启（1 开启，2 不开启）',
  `has_reprint_statement` tinyint(1) NULL DEFAULT 1 COMMENT '转载声明是否开启（1 开启，2 不开启）',
  `has_sticky` tinyint(1) NULL DEFAULT 2 COMMENT '是否置顶（1 是，2 否） ',
  `view_count` bigint NULL DEFAULT 0 COMMENT '浏览数量',
  `editor_type` tinyint(1) NULL DEFAULT 1 COMMENT '编辑器类型（1 Markdown编辑器，2 富文本编辑器）',
  `gmt_publish` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `gmt_last_publish` datetime NULL DEFAULT NULL COMMENT '最后发布时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1 草稿，2 发布，3 下架）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_collect
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_collect`;
CREATE TABLE `blog_article_collect`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '收藏 id',
  `fk_article_id` bigint NOT NULL COMMENT '文章 id',
  `fk_create_user_id` bigint NOT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1580904419548086274 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_collect
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_content
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_content`;
CREATE TABLE `blog_article_content`  (
  `id` bigint NOT NULL COMMENT '文章 id',
  `article_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章内容' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article_content
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_footprint
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_footprint`;
CREATE TABLE `blog_article_footprint`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '足迹 id',
  `fk_article_id` bigint NOT NULL COMMENT '文章 id',
  `fk_create_user_id` bigint NOT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1581144307853824001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章足迹' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_footprint
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_like
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_like`;
CREATE TABLE `blog_article_like`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '点赞 id',
  `fk_article_id` bigint NOT NULL COMMENT '文章 id',
  `fk_create_user_id` bigint NOT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1581269177640058883 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章点赞' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_like
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_recommend
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_recommend`;
CREATE TABLE `blog_article_recommend`  (
  `id` bigint NOT NULL COMMENT '推荐 id',
  `recommend_level` tinyint(1) NULL DEFAULT 1 COMMENT '推荐等级',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `fk_article_id` bigint NULL DEFAULT NULL COMMENT '文章 id',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章推荐' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article_recommend
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`  (
  `id` bigint NOT NULL COMMENT '文章标签 id',
  `fk_article_id` bigint NULL DEFAULT NULL COMMENT '文章 id',
  `fk_tag_id` bigint NULL DEFAULT NULL COMMENT '标签 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章标签' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类 id',
  `category_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `click_count` bigint NULL DEFAULT 0 COMMENT '点击数',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63597109596651521 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_category
-- ----------------------------

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` bigint NOT NULL COMMENT '评论 id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '上级 id',
  `fk_article_id` bigint NOT NULL COMMENT '文章 id',
  `has_sticky` tinyint(1) NULL DEFAULT 2 COMMENT '是否置顶（1 是，2 否） ',
  `comment_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 待审核，2 通过，3 驳回）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for blog_friendship_link
-- ----------------------------
DROP TABLE IF EXISTS `blog_friendship_link`;
CREATE TABLE `blog_friendship_link`  (
  `id` bigint NOT NULL COMMENT '链接 id',
  `link_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接名称',
  `link_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接简介',
  `link_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接地址',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友情链接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_friendship_link
-- ----------------------------
INSERT INTO `blog_friendship_link` VALUES (70588185381437440, '格姗知识圈', '专注于技术分享的博客平台', 'https://geshanzsq.com', 1, 1, 43728307660783616, '2022-10-06 18:52:15', 43728307660783616, '2022-10-06 18:53:19');
INSERT INTO `blog_friendship_link` VALUES (70590991626665984, '格姗导航', '可自定义的简洁导航网站', 'https://gesdh.cn', 2, 1, 43728307660783616, '2022-10-06 19:03:24', NULL, NULL);

-- ----------------------------
-- Table structure for blog_picture
-- ----------------------------
DROP TABLE IF EXISTS `blog_picture`;
CREATE TABLE `blog_picture`  (
  `id` bigint NOT NULL COMMENT '图片 id',
  `picture_original_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片原名称',
  `picture_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片路径',
  `picture_md5` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片 md5',
  `fk_category_id` bigint NULL DEFAULT NULL COMMENT '图片分类 id',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_picture
-- ----------------------------

-- ----------------------------
-- Table structure for blog_picture_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_picture_category`;
CREATE TABLE `blog_picture_category`  (
  `id` bigint NOT NULL COMMENT '分类 id',
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_picture_category
-- ----------------------------
INSERT INTO `blog_picture_category` VALUES (55074074266697728, '默认', 1, 1, 43728307660783616, '2022-08-24 23:24:43', NULL, NULL);
INSERT INTO `blog_picture_category` VALUES (55080822486597632, '标题', 2, 1, 43728307660783616, '2022-08-24 23:51:31', NULL, NULL);

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签 id',
  `tag_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `click_count` bigint NULL DEFAULT 0 COMMENT '点击数',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63597496722522113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_third_login_config
-- ----------------------------
DROP TABLE IF EXISTS `blog_third_login_config`;
CREATE TABLE `blog_third_login_config`  (
  `id` bigint NOT NULL COMMENT '配置 id',
  `third_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '第三方名称',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '第三方来源',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '第三方 id',
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '第三方密钥',
  `has_open_login` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启登录（1 是，2 否）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '第三方登录配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_third_login_config
-- ----------------------------
INSERT INTO `blog_third_login_config` VALUES (78259887838068736, 'QQ', 'QQ', 'qq clientId', 'qq clientSecret', 1, 43728307660783616, '2022-10-27 22:56:51', 43728307660783616, '2022-10-27 23:22:17');
INSERT INTO `blog_third_login_config` VALUES (78260986619887616, '码云', 'Gitee', 'gitee clientId', 'gitee clientSecret', 1, 43728307660783616, '2022-10-27 23:01:13', 43728307660783616, '2022-10-27 23:23:38');
INSERT INTO `blog_third_login_config` VALUES (78261101506068480, 'Github', 'Github', 'github clientId', 'github clientSecret', 1, 43728307660783616, '2022-10-27 23:01:41', 43728307660783616, '2022-10-27 23:29:56');

-- ----------------------------
-- Table structure for blog_third_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_third_user`;
CREATE TABLE `blog_third_user`  (
  `id` bigint NOT NULL COMMENT '博客用户 id',
  `fk_user_id` bigint NULL DEFAULT NULL COMMENT '用户 id',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '第三方账号来源',
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '第三方平台 uuid',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '第三方用户名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_uuid`(`uuid`) USING BTREE COMMENT '第三方平台 uuid 唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客第三方用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_third_user
-- ----------------------------

-- ----------------------------
-- Table structure for log_login
-- ----------------------------
DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login`  (
  `id` bigint NOT NULL COMMENT '登录日志 id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录用户名',
  `ip_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录 ip 地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '登录位置',
  `browser_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `operate_system` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '登录状态（1 成功，2 失败）',
  `hint_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '提示消息',
  `gmt_login` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_login
-- ----------------------------

-- ----------------------------
-- Table structure for log_operation
-- ----------------------------
DROP TABLE IF EXISTS `log_operation`;
CREATE TABLE `log_operation`  (
  `id` bigint NOT NULL COMMENT '操作日志 id',
  `module_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '模块名称',
  `business_type` tinyint(1) NULL DEFAULT 1 COMMENT '业务类型（1 其它，2 新增，3 修改，4 删除）',
  `operate_type` tinyint(1) NULL DEFAULT 1 COMMENT '操作类型（1 其它，2 后台用户，3 手机端用户，4 博客用户）',
  `fk_user_id` bigint NOT NULL COMMENT '操作用户 id',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求方式',
  `class_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '类方法',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求地址',
  `ip_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作 ip 地址',
  `operate_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作位置',
  `request_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `return_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '返回结果',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '登录状态（1 成功，2 异常）',
  `gmt_operate` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `error_message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '错误消息',
  `browser_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `operate_system` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作系统',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_operation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` bigint NOT NULL COMMENT '接口 id',
  `api_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '接口名称',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '接口地址',
  `api_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '接口请求方式（如：get，post）',
  `fk_api_category_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '所属分类 id',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统接口' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api` VALUES (43759552742555648, '分页列表', '/system/user/page', 'GET', '43757041415618560', 1, 1, '', 43728307660783616, '2022-07-24 18:04:50', NULL, NULL);
INSERT INTO `sys_api` VALUES (43759676889759744, '详情', '/system/user/getById/*', 'GET', '43757041415618560', 2, 1, '', 43728307660783616, '2022-07-24 18:05:20', NULL, NULL);
INSERT INTO `sys_api` VALUES (43761152815005696, '新增', '/system/user', 'POST', '43757041415618560', 3, 1, '', 43728307660783616, '2022-07-24 18:11:12', NULL, NULL);
INSERT INTO `sys_api` VALUES (43761187950690304, '修改', '/system/user', 'PUT', '43757041415618560', 4, 1, '', 43728307660783616, '2022-07-24 18:11:20', NULL, NULL);
INSERT INTO `sys_api` VALUES (43761278916755456, '删除', '/system/user/delete/*', 'DELETE', '43757041415618560', 5, 1, '', 43728307660783616, '2022-07-24 18:11:42', NULL, NULL);
INSERT INTO `sys_api` VALUES (43762647782391808, '重置密码', '/system/user/resetPassword', 'PUT', '43757041415618560', 6, 1, '', 43728307660783616, '2022-07-24 18:17:08', NULL, NULL);
INSERT INTO `sys_api` VALUES (43762807694426112, '分页列表', '/system/role/page', 'GET', '43757069869776896', 1, 1, '', 43728307660783616, '2022-07-24 18:17:46', NULL, NULL);
INSERT INTO `sys_api` VALUES (43762858923655168, '详情', '/system/role/getById/*', 'GET', '43757069869776896', 2, 1, '', 43728307660783616, '2022-07-24 18:17:59', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763188797276160, '新增', '/system/role', 'POST', '43757069869776896', 3, 1, '', 43728307660783616, '2022-07-24 18:19:17', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763232950714368, '修改', '/system/role', 'PUT', '43757069869776896', 4, 1, '', 43728307660783616, '2022-07-24 18:19:28', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763311845572608, '删除', '/system/role/delete/*', 'DELETE', '43757069869776896', 5, 1, '', 43728307660783616, '2022-07-24 18:19:47', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763442175180800, '获取最大排序', '/system/role/getMaxSort', 'GET', '43757069869776896', 6, 1, '', 43728307660783616, '2022-07-24 18:20:18', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763523695673344, '已分配用户分页', '/system/role/auth/user/page', 'GET', '43757069869776896', 7, 1, '', 43728307660783616, '2022-07-24 18:20:37', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763593107210240, '未分配用户分页', '/system/role/auth/user/not/page', 'GET', '43757069869776896', 8, 1, '', 43728307660783616, '2022-07-24 18:20:54', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763693447544832, '授权用户', '/system/role/auth/user', 'POST', '43757069869776896', 9, 1, '', 43728307660783616, '2022-07-24 18:21:18', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763818857234432, '取消授权', '/system/role/auth/user/delete', 'DELETE', '43757069869776896', 10, 1, '', 43728307660783616, '2022-07-24 18:21:48', NULL, NULL);
INSERT INTO `sys_api` VALUES (43763961681674240, '列表', '/system/menu/list', 'GET', '43758191430860800', 1, 1, '', 43728307660783616, '2022-07-24 18:22:22', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764022897541120, '树形', '/system/menu/tree', 'GET', '43758191430860800', 2, 1, '', 43728307660783616, '2022-07-24 18:22:36', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764081659740160, '详情', '/system/menu/getById/*', 'GET', '43758191430860800', 3, 1, '', 43728307660783616, '2022-07-24 18:22:50', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764130418524160, '新增', '/system/menu', 'POST', '43758191430860800', 4, 1, '', 43728307660783616, '2022-07-24 18:23:02', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764160827228160, '修改', '/system/menu', 'PUT', '43758191430860800', 5, 1, '', 43728307660783616, '2022-07-24 18:23:09', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764235854938112, '删除', '/system/menu/delete/*', 'DELETE', '43758191430860800', 6, 1, '', 43728307660783616, '2022-07-24 18:23:27', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764326162497536, '获取最大排序', '/system/menu/getMaxSortByParentId', 'GET', '43758191430860800', 7, 1, '', 43728307660783616, '2022-07-24 18:23:48', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764505833897984, '分页列表', '/system/dictionary/page', 'GET', '43758342685851648', 1, 1, '', 43728307660783616, '2022-07-24 18:24:31', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764571281817600, '列表', '/system/dictionary/list', 'GET', '43758342685851648', 2, 1, '', 43728307660783616, '2022-07-24 18:24:47', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764610037186560, '新增', '/system/dictionary', 'POST', '43758342685851648', 3, 1, '', 43728307660783616, '2022-07-24 18:24:56', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764642895364096, '修改', '/system/dictionary', 'PUT', '43758342685851648', 5, 1, '', 43728307660783616, '2022-07-24 18:25:04', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764689900929024, '删除', '/system/dictionary/delete/*', 'DELETE', '43758342685851648', 6, 1, '', 43728307660783616, '2022-07-24 18:25:15', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764765633282048, '获取最大排序', '/system/dictionary/getMaxSort', 'GET', '43758342685851648', 7, 1, '', 43728307660783616, '2022-07-24 18:25:33', NULL, NULL);
INSERT INTO `sys_api` VALUES (43764977332387840, '分页列表', '/system/dictionary/data/page', 'GET', '43758386155618304', 1, 1, '', 43728307660783616, '2022-07-24 18:26:24', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765061612732416, '详情', '/system/dictionary/data/getById/*', 'GET', '43758386155618304', 2, 1, '', 43728307660783616, '2022-07-24 18:26:44', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765127371030528, '新增', '/system/dictionary/data', 'POST', '43758386155618304', 3, 1, '', 43728307660783616, '2022-07-24 18:26:59', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765165673414656, '修改', '/system/dictionary/data', 'PUT', '43758386155618304', 4, 1, '', 43728307660783616, '2022-07-24 18:27:09', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765212116942848, '删除', '/system/dictionary/data/delete/*', 'DELETE', '43758386155618304', 5, 1, '', 43728307660783616, '2022-07-24 18:27:20', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765276512092160, '获取最大排序', '/system/dictionary/data/getMaxSortByDictionaryId', 'GET', '43758386155618304', 6, 1, '', 43728307660783616, '2022-07-24 18:27:35', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765395617742848, '分页列表', '/system/api/category/page', 'GET', '43758515155632128', 1, 1, '', 43728307660783616, '2022-07-24 18:28:03', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765435992113152, '列表', '/system/api/category/list', 'GET', '43758515155632128', 2, 1, '', 43728307660783616, '2022-07-24 18:28:13', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765499997192192, '详情', '/system/api/category/getById/*', 'GET', '43758515155632128', 3, 1, '', 43728307660783616, '2022-07-24 18:28:28', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765537020313600, '新增', '/system/api/category', 'POST', '43758515155632128', 4, 1, '', 43728307660783616, '2022-07-24 18:28:37', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765569287094272, '修改', '/system/api/category', 'PUT', '43758515155632128', 5, 1, '', 43728307660783616, '2022-07-24 18:28:45', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765629844455424, '删除', '/system/api/category/delete/*', 'DELETE', '43758515155632128', 6, 1, '', 43728307660783616, '2022-07-24 18:28:59', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765696206733312, '获取最大排序', '/system/api/category/getMaxSort', 'GET', '43758515155632128', 7, 1, '', 43728307660783616, '2022-07-24 18:29:15', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765773721665536, '分页列表', '/system/api/page', 'GET', '43758723595763712', 1, 1, '', 43728307660783616, '2022-07-24 18:29:34', NULL, NULL);
INSERT INTO `sys_api` VALUES (43765827249373184, '详情', '/system/api/getById/*', 'GET', '43758723595763712', 2, 1, '', 43728307660783616, '2022-07-24 18:29:46', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766245937381376, '新增', '/system/api', 'POST', '43758723595763712', 3, 1, '', 43728307660783616, '2022-07-24 18:31:26', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766278116081664, '修改', '/system/api', 'PUT', '43758723595763712', 4, 1, '', 43728307660783616, '2022-07-24 18:31:34', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766323594919936, '删除', '/system/api/delete/*', 'DELETE', '43758723595763712', 5, 1, '', 43728307660783616, '2022-07-24 18:31:45', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766380444516352, '获取最大排序', '/system/api/getMaxSortByCategoryId', 'GET', '43758723595763712', 6, 1, '', 43728307660783616, '2022-07-24 18:31:58', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766697940746240, '分页列表', '/system/log/login/page', 'GET', '43766546228576256', 1, 1, '', 43728307660783616, '2022-07-24 18:33:14', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766812147449856, '分页列表', '/system/log/operation/page', 'GET', '43766567304953856', 1, 1, '', 43728307660783616, '2022-07-24 18:33:41', NULL, NULL);
INSERT INTO `sys_api` VALUES (43766869047377920, '详情', '/system/log/operation/getById/*', 'GET', '43766567304953856', 2, 1, '', 43728307660783616, '2022-07-24 18:33:55', NULL, NULL);
INSERT INTO `sys_api` VALUES (45287586729558016, '详情', '/system/dictionary/getById/*', 'GET', '43758342685851648', 4, 1, '', 43728307660783616, '2022-07-28 23:16:42', NULL, NULL);
INSERT INTO `sys_api` VALUES (52141509008424960, '分页列表', '/system/param/page', 'GET', '52133147512406016', 1, 1, '', 43728307660783616, '2022-08-16 21:11:44', NULL, NULL);
INSERT INTO `sys_api` VALUES (52141659554578432, '详情', '/system/param/getById/*', 'GET', '52133147512406016', 2, 1, '', 43728307660783616, '2022-08-16 21:12:20', NULL, NULL);
INSERT INTO `sys_api` VALUES (52141709437435904, '新增', '/system/param', 'POST', '52133147512406016', 3, 1, '', 43728307660783616, '2022-08-16 21:12:32', NULL, NULL);
INSERT INTO `sys_api` VALUES (52141758401740800, '修改', '/system/param', 'PUT', '52133147512406016', 4, 1, '', 43728307660783616, '2022-08-16 21:12:44', NULL, NULL);
INSERT INTO `sys_api` VALUES (52141803498897408, '删除', '/system/param/delete/*', 'DELETE', '52133147512406016', 5, 1, '', 43728307660783616, '2022-08-16 21:12:55', NULL, NULL);
INSERT INTO `sys_api` VALUES (52141938496765952, '获取最大排序', '/system/param/getMaxSort', 'GET', '52133147512406016', 6, 1, '', 43728307660783616, '2022-08-16 21:13:27', NULL, NULL);
INSERT INTO `sys_api` VALUES (61847900916809728, '分页列表', '/blog/article/page', 'POST', '61847418269859840', 1, 1, '', 43728307660783616, '2022-09-12 16:01:29', NULL, NULL);
INSERT INTO `sys_api` VALUES (61847972400332800, '详情', '/blog/article/getById/*', 'GET', '61847418269859840', 2, 1, '', 43728307660783616, '2022-09-12 16:01:46', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848012527239168, '新增', '/blog/article', 'POST', '61847418269859840', 3, 1, '', 43728307660783616, '2022-09-12 16:01:55', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848043829329920, '修改', '/blog/article', 'PUT', '61847418269859840', 4, 1, '', 43728307660783616, '2022-09-12 16:02:03', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848102243401728, '删除', '/blog/article/delete/*', 'DELETE', '61847418269859840', 5, 1, '', 43728307660783616, '2022-09-12 16:02:17', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848228076716032, '分页列表', '/blog/category/page', 'GET', '61847515535769600', 1, 1, '', 43728307660783616, '2022-09-12 16:02:47', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848287396757504, '列表', '/blog/category/list', 'GET', '61847515535769600', 2, 1, '', 43728307660783616, '2022-09-12 16:03:01', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848362231529472, '详情', '/blog/category/getById/*', 'GET', '61847515535769600', 3, 1, '', 43728307660783616, '2022-09-12 16:03:19', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848415608242176, '新增', '/blog/category', 'POST', '61847515535769600', 4, 1, '', 43728307660783616, '2022-09-12 16:03:31', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848451385655296, '修改', '/blog/category', 'PUT', '61847515535769600', 5, 1, '', 43728307660783616, '2022-09-12 16:03:40', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848509799727104, '删除', '/blog/category/delete/*', 'DELETE', '61847515535769600', 6, 1, '', 43728307660783616, '2022-09-12 16:03:54', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848615525548032, '获取最大排序', '/blog/category/getMaxSort', 'GET', '61847515535769600', 7, 1, '', 43728307660783616, '2022-09-12 16:04:19', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848739882467328, '分页列表', '/blog/tag/page', 'GET', '61847552537919488', 1, 1, '', 43728307660783616, '2022-09-12 16:04:49', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848787538149376, '列表', '/blog/tag/list', 'GET', '61847552537919488', 2, 1, '', 43728307660783616, '2022-09-12 16:05:00', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848847705440256, '详情', '/blog/tag/getById/*', 'GET', '61847552537919488', 3, 1, '', 43728307660783616, '2022-09-12 16:05:14', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848881259872256, '新增', '/blog/tag', 'POST', '61847552537919488', 4, 1, '', 43728307660783616, '2022-09-12 16:05:22', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848916974370816, '修改', '/blog/tag', 'PUT', '61847552537919488', 5, 1, '', 43728307660783616, '2022-09-12 16:05:31', NULL, NULL);
INSERT INTO `sys_api` VALUES (61848963224961024, '删除', '/blog/tag/delete/*', 'DELETE', '61847552537919488', 6, 1, '', 43728307660783616, '2022-09-12 16:05:42', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849058452439040, '获取最大排序', '/blog/tag/getMaxSort', 'GET', '61847552537919488', 7, 1, '', 43728307660783616, '2022-09-12 16:06:05', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849212110766080, '分页列表', '/blog/picture/category/page', 'GET', '61847614164828160', 1, 1, '', 43728307660783616, '2022-09-12 16:06:41', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849257535078400, '列表', '/blog/picture/category/list', 'GET', '61847614164828160', 2, 1, '', 43728307660783616, '2022-09-12 16:06:52', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849324270649344, '详情', '/blog/picture/category/getById/*', 'GET', '61847614164828160', 3, 1, '', 43728307660783616, '2022-09-12 16:07:08', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849358387118080, '新增', '/blog/picture/category', 'POST', '61847614164828160', 4, 1, '', 43728307660783616, '2022-09-12 16:07:16', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849385406824448, '修改', '/blog/picture/category', 'PUT', '61847614164828160', 5, 1, '', 43728307660783616, '2022-09-12 16:07:23', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849447339917312, '删除', '/blog/picture/category/delete/*', 'GET', '61847614164828160', 6, 1, '', 43728307660783616, '2022-09-12 16:07:37', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849533935517696, '获取最大排序', '/blog/picture/category/getMaxSort', 'GET', '61847614164828160', 7, 1, '', 43728307660783616, '2022-09-12 16:07:58', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849626013073408, '分页列表', '/blog/picture/page', 'GET', '61847660100845568', 1, 1, '', 43728307660783616, '2022-09-12 16:08:20', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849706564681728, '上传', '/blog/picture/upload', 'POST', '61847660100845568', 2, 1, '', 43728307660783616, '2022-09-12 16:08:39', NULL, NULL);
INSERT INTO `sys_api` VALUES (61849779214221312, '删除', '/blog/picture/delete/*', 'DELETE', '61847660100845568', 3, 1, '', 43728307660783616, '2022-09-12 16:08:56', NULL, NULL);
INSERT INTO `sys_api` VALUES (70575017208840192, '分页列表', '/blog/friendship/page', 'GET', '70574816393953280', 1, 1, '', 43728307660783616, '2022-10-06 17:59:55', NULL, NULL);
INSERT INTO `sys_api` VALUES (70575082941972480, '详情', '/blog/friendship/getById/*', 'GET', '70574816393953280', 2, 1, '', 43728307660783616, '2022-10-06 18:00:11', NULL, NULL);
INSERT INTO `sys_api` VALUES (70575146867359744, '新增', '/blog/friendship', 'POST', '70574816393953280', 3, 1, '', 43728307660783616, '2022-10-06 18:00:26', NULL, NULL);
INSERT INTO `sys_api` VALUES (70575186964905984, '修改', '/blog/friendship', 'PUT', '70574816393953280', 4, 1, '', 43728307660783616, '2022-10-06 18:00:36', NULL, NULL);
INSERT INTO `sys_api` VALUES (70575233257439232, '删除', '/blog/friendship/delete/*', 'DELETE', '70574816393953280', 5, 1, '', 43728307660783616, '2022-10-06 18:00:47', NULL, NULL);
INSERT INTO `sys_api` VALUES (70575309300170752, '获取最大排序', '/blog/friendship/getMaxSort', 'GET', '70574816393953280', 6, 1, '', 43728307660783616, '2022-10-06 18:01:05', NULL, NULL);
INSERT INTO `sys_api` VALUES (78231761150017536, '分页列表', '/blog/comment/page', 'GET', '78231613502128128', 1, 1, '', 43728307660783616, '2022-10-27 21:05:05', NULL, NULL);
INSERT INTO `sys_api` VALUES (78231849494642688, '详情', '/blog/comment/getById/*', 'GET', '78231613502128128', 2, 1, '', 43728307660783616, '2022-10-27 21:05:26', NULL, NULL);
INSERT INTO `sys_api` VALUES (78231919132672000, '审核', '/blog/comment/audit', 'PUT', '78231613502128128', 3, 1, '', 43728307660783616, '2022-10-27 21:05:43', NULL, NULL);
INSERT INTO `sys_api` VALUES (78231989534064640, '删除', '/blog/comment/delete/*', 'DELETE', '78231613502128128', 4, 1, '', 43728307660783616, '2022-10-27 21:06:00', NULL, NULL);
INSERT INTO `sys_api` VALUES (78261557221392384, '分页列表', '/blog/third/config/page', 'GET', '78261458097405952', 1, 1, '', 43728307660783616, '2022-10-27 23:03:29', NULL, NULL);
INSERT INTO `sys_api` VALUES (78261623420092416, '详情', '/blog/third/config/getById/*', 'GET', '78261458097405952', 2, 1, '', 43728307660783616, '2022-10-27 23:03:45', NULL, NULL);
INSERT INTO `sys_api` VALUES (78261660229304320, '新增', '/blog/third/config', 'POST', '78261458097405952', 3, 1, '', 43728307660783616, '2022-10-27 23:03:54', NULL, NULL);
INSERT INTO `sys_api` VALUES (78261687618109440, '修改', '/blog/third/config', 'PUT', '78261458097405952', 4, 1, '', 43728307660783616, '2022-10-27 23:04:00', NULL, NULL);
INSERT INTO `sys_api` VALUES (78261740994822144, '删除', '/blog/third/config/delete/*', 'DELETE', '78261458097405952', 5, 1, '', 43728307660783616, '2022-10-27 23:04:13', NULL, NULL);
INSERT INTO `sys_api` VALUES (78594147014934528, '获取内容', '/blog/about/getContent', 'GET', '78594014642700288', 1, 1, '', 43728307660783616, '2022-10-28 21:05:05', NULL, NULL);
INSERT INTO `sys_api` VALUES (78594285489881088, '编辑', '/blog/about/edit', 'POST', '78594014642700288', 2, 1, '', 43728307660783616, '2022-10-28 21:05:38', NULL, NULL);
INSERT INTO `sys_api` VALUES (78867282280841216, '列表', '/blog/recommend/list', 'GET', '78866987261886464', 1, 1, '', 43728307660783616, '2022-10-29 15:10:25', NULL, NULL);
INSERT INTO `sys_api` VALUES (78867348030750720, '更新排序', '/blog/recommend/updateSort', 'PUT', '78866987261886464', 2, 1, '', 43728307660783616, '2022-10-29 15:10:41', NULL, NULL);

-- ----------------------------
-- Table structure for sys_api_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_category`;
CREATE TABLE `sys_api_category`  (
  `id` bigint NOT NULL COMMENT '接口分类 id',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '分类名称',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统接口分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_api_category
-- ----------------------------
INSERT INTO `sys_api_category` VALUES (43757041415618560, '用户管理', 1, 1, '', 43728307660783616, '2022-07-24 17:54:52', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43757069869776896, '角色管理', 2, 1, '', 43728307660783616, '2022-07-24 17:54:58', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43758191430860800, '菜单管理', 3, 1, '', 43728307660783616, '2022-07-24 17:59:26', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43758342685851648, '数据字典', 4, 1, '', 43728307660783616, '2022-07-24 18:00:02', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43758386155618304, '数据字典数据', 5, 1, '', 43728307660783616, '2022-07-24 18:00:12', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43758515155632128, 'API 接口分类', 6, 1, '', 43728307660783616, '2022-07-24 18:00:43', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43758723595763712, 'API 接口', 7, 1, '\n', 43728307660783616, '2022-07-24 18:01:33', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43766546228576256, '登录日志', 9, 1, '', 43728307660783616, '2022-07-24 18:32:38', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (43766567304953856, '操作日志', 10, 1, '', 43728307660783616, '2022-07-24 18:32:43', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (52133147512406016, '参数配置', 8, 1, '', 43728307660783616, '2022-08-16 20:38:31', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (61847418269859840, '博客文章管理', 11, 1, '', 43728307660783616, '2022-09-12 15:59:34', 43728307660783616, '2022-09-12 15:59:50');
INSERT INTO `sys_api_category` VALUES (61847515535769600, '博客分类管理', 12, 1, '', 43728307660783616, '2022-09-12 15:59:57', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (61847552537919488, '博客标签管理', 13, 1, '', 43728307660783616, '2022-09-12 16:00:06', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (61847614164828160, '博客图片分类管理', 14, 1, '', 43728307660783616, '2022-09-12 16:00:20', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (61847660100845568, '博客图片管理', 15, 1, '', 43728307660783616, '2022-09-12 16:00:31', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (70574816393953280, '友情链接', 16, 1, '', 43728307660783616, '2022-10-06 17:59:07', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (78231613502128128, '评论管理', 17, 1, '', 43728307660783616, '2022-10-27 21:04:30', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (78261458097405952, '第三方登录配置', 18, 1, '', 43728307660783616, '2022-10-27 23:03:06', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (78594014642700288, '关于我', 19, 1, '', 43728307660783616, '2022-10-28 21:04:33', NULL, NULL);
INSERT INTO `sys_api_category` VALUES (78866987261886464, '推荐管理', 20, 1, '', 43728307660783616, '2022-10-29 15:09:15', NULL, NULL);

-- ----------------------------
-- Table structure for sys_api_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_menu`;
CREATE TABLE `sys_api_menu`  (
  `id` bigint NOT NULL COMMENT '接口菜单 id',
  `fk_api_id` bigint NOT NULL COMMENT '接口 id',
  `fk_menu_id` bigint NOT NULL COMMENT '菜单 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统接口菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_api_menu
-- ----------------------------
INSERT INTO `sys_api_menu` VALUES (44934431223316480, 43759552742555648, 43738261293629440);
INSERT INTO `sys_api_menu` VALUES (44941778175918080, 43761152815005696, 43779604086784000);
INSERT INTO `sys_api_menu` VALUES (44942911996952576, 43759676889759744, 43779675532558336);
INSERT INTO `sys_api_menu` VALUES (44942911996952577, 43761187950690304, 43779675532558336);
INSERT INTO `sys_api_menu` VALUES (44943459030663168, 43761278916755456, 43779734387032064);
INSERT INTO `sys_api_menu` VALUES (44943477556903936, 43762647782391808, 43779823738290176);
INSERT INTO `sys_api_menu` VALUES (44943667110084608, 43762807694426112, 43738420110950400);
INSERT INTO `sys_api_menu` VALUES (44943839235932160, 43762858923655168, 43780015896133632);
INSERT INTO `sys_api_menu` VALUES (44943839235932161, 43763232950714368, 43780015896133632);
INSERT INTO `sys_api_menu` VALUES (44943888078602240, 43763311845572608, 43780063644090368);
INSERT INTO `sys_api_menu` VALUES (44943942797492224, 43763188797276160, 43779962246791168);
INSERT INTO `sys_api_menu` VALUES (44943942797492225, 43763442175180800, 43779962246791168);
INSERT INTO `sys_api_menu` VALUES (45286549805334528, 43763523695673344, 43780156136882176);
INSERT INTO `sys_api_menu` VALUES (45286549805334529, 43763593107210240, 43780156136882176);
INSERT INTO `sys_api_menu` VALUES (45286549805334530, 43763693447544832, 43780156136882176);
INSERT INTO `sys_api_menu` VALUES (45286600837431296, 43763818857234432, 43780326794723328);
INSERT INTO `sys_api_menu` VALUES (45286731506778112, 43763961681674240, 43738641683447808);
INSERT INTO `sys_api_menu` VALUES (45286823873740800, 43764022897541120, 43780571997929472);
INSERT INTO `sys_api_menu` VALUES (45286823873740801, 43764081659740160, 43780571997929472);
INSERT INTO `sys_api_menu` VALUES (45286823873740802, 43764160827228160, 43780571997929472);
INSERT INTO `sys_api_menu` VALUES (45286823873740803, 43764326162497536, 43780571997929472);
INSERT INTO `sys_api_menu` VALUES (45286924520259584, 43764022897541120, 43780526653308928);
INSERT INTO `sys_api_menu` VALUES (45286924520259585, 43764130418524160, 43780526653308928);
INSERT INTO `sys_api_menu` VALUES (45286924520259586, 43764326162497536, 43780526653308928);
INSERT INTO `sys_api_menu` VALUES (45286959903408128, 43764235854938112, 43780625399808000);
INSERT INTO `sys_api_menu` VALUES (45287084751060992, 43765435992113152, 43780699760623616);
INSERT INTO `sys_api_menu` VALUES (45287084751060993, 43765773721665536, 43780699760623616);
INSERT INTO `sys_api_menu` VALUES (45287155194396672, 43764505833897984, 43738896395141120);
INSERT INTO `sys_api_menu` VALUES (45287205698011136, 43764610037186560, 43780957378969600);
INSERT INTO `sys_api_menu` VALUES (45287205698011137, 43764765633282048, 43780957378969600);
INSERT INTO `sys_api_menu` VALUES (45287965244522496, 43764642895364096, 43781009912627200);
INSERT INTO `sys_api_menu` VALUES (45287965244522497, 43764765633282048, 43781009912627200);
INSERT INTO `sys_api_menu` VALUES (45287965244522498, 45287586729558016, 43781009912627200);
INSERT INTO `sys_api_menu` VALUES (45288010920493056, 43764689900929024, 43781060101668864);
INSERT INTO `sys_api_menu` VALUES (45288359009976320, 43765127371030528, 43781756515516416);
INSERT INTO `sys_api_menu` VALUES (45288359009976321, 43765276512092160, 43781756515516416);
INSERT INTO `sys_api_menu` VALUES (45288394263101440, 43765061612732416, 43781829500600320);
INSERT INTO `sys_api_menu` VALUES (45288394263101441, 43765165673414656, 43781829500600320);
INSERT INTO `sys_api_menu` VALUES (45288394263101442, 43765276512092160, 43781829500600320);
INSERT INTO `sys_api_menu` VALUES (45288418313240576, 43765212116942848, 43781877487632384);
INSERT INTO `sys_api_menu` VALUES (45288563960446976, 43764571281817600, 43781692732735488);
INSERT INTO `sys_api_menu` VALUES (45288563960446977, 43764977332387840, 43781692732735488);
INSERT INTO `sys_api_menu` VALUES (45288684953534464, 43765395617742848, 43739074015526912);
INSERT INTO `sys_api_menu` VALUES (45288725575368704, 43765537020313600, 43820727559782400);
INSERT INTO `sys_api_menu` VALUES (45288725575368705, 43765696206733312, 43820727559782400);
INSERT INTO `sys_api_menu` VALUES (45288799676137472, 43765499997192192, 43820963283861504);
INSERT INTO `sys_api_menu` VALUES (45288799676137473, 43765569287094272, 43820963283861504);
INSERT INTO `sys_api_menu` VALUES (45288799676137474, 43765696206733312, 43820963283861504);
INSERT INTO `sys_api_menu` VALUES (45288869704237056, 43765629844455424, 43821061610930176);
INSERT INTO `sys_api_menu` VALUES (45288915245989888, 43765435992113152, 43821227462098944);
INSERT INTO `sys_api_menu` VALUES (45288915245989889, 43765773721665536, 43821227462098944);
INSERT INTO `sys_api_menu` VALUES (45288959261016064, 43766245937381376, 43821354134274048);
INSERT INTO `sys_api_menu` VALUES (45288959261016065, 43766380444516352, 43821354134274048);
INSERT INTO `sys_api_menu` VALUES (45288998154797056, 43765827249373184, 43821418193879040);
INSERT INTO `sys_api_menu` VALUES (45288998154797057, 43766278116081664, 43821418193879040);
INSERT INTO `sys_api_menu` VALUES (45288998154797058, 43766380444516352, 43821418193879040);
INSERT INTO `sys_api_menu` VALUES (45289037971324928, 43766323594919936, 43821478692519936);
INSERT INTO `sys_api_menu` VALUES (45289082435141632, 43766697940746240, 43746395802304512);
INSERT INTO `sys_api_menu` VALUES (45289104912416768, 43766812147449856, 43746859121901568);
INSERT INTO `sys_api_menu` VALUES (45289104912416769, 43766869047377920, 43746859121901568);
INSERT INTO `sys_api_menu` VALUES (52142019916595200, 52141509008424960, 52073868344426496);
INSERT INTO `sys_api_menu` VALUES (52142059640848384, 52141709437435904, 52132922030817280);
INSERT INTO `sys_api_menu` VALUES (52142059640848385, 52141938496765952, 52132922030817280);
INSERT INTO `sys_api_menu` VALUES (52142106021462016, 52141659554578432, 52133024904511488);
INSERT INTO `sys_api_menu` VALUES (52142106025656320, 52141758401740800, 52133024904511488);
INSERT INTO `sys_api_menu` VALUES (52142106025656321, 52141938496765952, 52133024904511488);
INSERT INTO `sys_api_menu` VALUES (52142131011125248, 52141803498897408, 52133076263763968);
INSERT INTO `sys_api_menu` VALUES (61849910575628288, 61848012527239168, 52099447332536320);
INSERT INTO `sys_api_menu` VALUES (61850065005707264, 61848012527239168, 52099936145113088);
INSERT INTO `sys_api_menu` VALUES (61850124438994944, 61847972400332800, 52100202487611392);
INSERT INTO `sys_api_menu` VALUES (61850124438994945, 61848043829329920, 52100202487611392);
INSERT INTO `sys_api_menu` VALUES (61850152461139968, 61848102243401728, 52100286625349632);
INSERT INTO `sys_api_menu` VALUES (61850374583091200, 61847900916809728, 52099788358811648);
INSERT INTO `sys_api_menu` VALUES (61850374583091201, 61848287396757504, 52099788358811648);
INSERT INTO `sys_api_menu` VALUES (61850374587285504, 61848787538149376, 52099788358811648);
INSERT INTO `sys_api_menu` VALUES (61850447798861824, 61848228076716032, 52114381915291648);
INSERT INTO `sys_api_menu` VALUES (61850558289412096, 61848362231529472, 52114568226275328);
INSERT INTO `sys_api_menu` VALUES (61850558289412097, 61848451385655296, 52114568226275328);
INSERT INTO `sys_api_menu` VALUES (61850558289412098, 61848615525548032, 52114568226275328);
INSERT INTO `sys_api_menu` VALUES (61850616942559232, 61848415608242176, 52114517684912128);
INSERT INTO `sys_api_menu` VALUES (61850616942559233, 61848615525548032, 52114517684912128);
INSERT INTO `sys_api_menu` VALUES (61850648638914560, 61848509799727104, 52114614602694656);
INSERT INTO `sys_api_menu` VALUES (61850731228954624, 61848739882467328, 52114878701240320);
INSERT INTO `sys_api_menu` VALUES (61850770877710336, 61848881259872256, 52114929196466176);
INSERT INTO `sys_api_menu` VALUES (61850770881904640, 61849058452439040, 52114929196466176);
INSERT INTO `sys_api_menu` VALUES (61850869674541056, 61848847705440256, 52114968178327552);
INSERT INTO `sys_api_menu` VALUES (61850869674541057, 61848916974370816, 52114968178327552);
INSERT INTO `sys_api_menu` VALUES (61850869674541058, 61849058452439040, 52114968178327552);
INSERT INTO `sys_api_menu` VALUES (61850908538961920, 61848963224961024, 52115022117076992);
INSERT INTO `sys_api_menu` VALUES (61851034003177472, 61849212110766080, 55073494739714048);
INSERT INTO `sys_api_menu` VALUES (61852091756314624, 61849358387118080, 61851202979102720);
INSERT INTO `sys_api_menu` VALUES (61852091760508928, 61849533935517696, 61851202979102720);
INSERT INTO `sys_api_menu` VALUES (61852136819916800, 61849324270649344, 61851247690383360);
INSERT INTO `sys_api_menu` VALUES (61852136819916801, 61849385406824448, 61851247690383360);
INSERT INTO `sys_api_menu` VALUES (61852136819916802, 61849533935517696, 61851247690383360);
INSERT INTO `sys_api_menu` VALUES (61852179429851136, 61849447339917312, 61851299972382720);
INSERT INTO `sys_api_menu` VALUES (61852306055888896, 61849257535078400, 61851660971933696);
INSERT INTO `sys_api_menu` VALUES (61852306055888897, 61849626013073408, 61851660971933696);
INSERT INTO `sys_api_menu` VALUES (61852336573644800, 61849706564681728, 61851798276669440);
INSERT INTO `sys_api_menu` VALUES (61852362548969472, 61849779214221312, 61851972713578496);
INSERT INTO `sys_api_menu` VALUES (70577613185220608, 70575017208840192, 70575761538416640);
INSERT INTO `sys_api_menu` VALUES (70591990315614208, 70575146867359744, 70591784899575808);
INSERT INTO `sys_api_menu` VALUES (70591990324002816, 70575309300170752, 70591784899575808);
INSERT INTO `sys_api_menu` VALUES (70592031403016192, 70575082941972480, 70591842319597568);
INSERT INTO `sys_api_menu` VALUES (70592031415599104, 70575186964905984, 70591842319597568);
INSERT INTO `sys_api_menu` VALUES (70592031423987712, 70575309300170752, 70591842319597568);
INSERT INTO `sys_api_menu` VALUES (70592061300015104, 70575233257439232, 70591937538686976);
INSERT INTO `sys_api_menu` VALUES (78232707716349952, 78231849494642688, 78232604175761408);
INSERT INTO `sys_api_menu` VALUES (78232707724738560, 78231919132672000, 78232604175761408);
INSERT INTO `sys_api_menu` VALUES (78232738980691968, 78231989534064640, 78232661448982528);
INSERT INTO `sys_api_menu` VALUES (78261840311746560, 78261557221392384, 78243661845889024);
INSERT INTO `sys_api_menu` VALUES (78262187121967104, 78261660229304320, 78261905201823744);
INSERT INTO `sys_api_menu` VALUES (78262232294621184, 78261623420092416, 78261973308932096);
INSERT INTO `sys_api_menu` VALUES (78262232294621185, 78261687618109440, 78261973308932096);
INSERT INTO `sys_api_menu` VALUES (78262259926695936, 78261740994822144, 78262028635996160);
INSERT INTO `sys_api_menu` VALUES (78594360760860672, 78594147014934528, 78582377730801664);
INSERT INTO `sys_api_menu` VALUES (78594494852759552, 78594285489881088, 78594439798325248);
INSERT INTO `sys_api_menu` VALUES (78867418193068032, 78867282280841216, 78595081065463808);
INSERT INTO `sys_api_menu` VALUES (78867418205650944, 78867348030750720, 78595081065463808);

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint NOT NULL COMMENT '字典 id',
  `dictionary_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典名称',
  `dictionary_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典编码',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (43739630905851904, '通用状态', 'commonStatus', 1, 1, '', 43728307660783616, '2022-07-24 16:45:41', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43739903539806208, '用户类型', 'systemUserType', 2, 1, '', 43728307660783616, '2022-07-24 16:46:46', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43740085404827648, '用户性别', 'systemUserSex', 3, 1, '', 43728307660783616, '2022-07-24 16:47:29', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43740399080046592, '菜单是否缓存', 'systemMenuIsCache', 4, 1, '', 43728307660783616, '2022-07-24 16:48:44', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43740514784116736, '菜单是否外链', 'systemMenuIsFrame', 5, 1, '', 43728307660783616, '2022-07-24 16:49:11', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43740632493064192, '菜单显示状态', 'systemMenuShowStatus', 6, 1, '', 43728307660783616, '2022-07-24 16:49:39', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43741186304770048, '数据字典样式类型', 'dictionaryClassType', 7, 1, '', 43728307660783616, '2022-07-24 16:51:51', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43741186479310919, 'API 请求方式', 'apiRequestMethod', 8, 1, '', 43728307660783616, '2022-07-24 18:07:28', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43747174940409856, '登录日志状态', 'logLoginStatus', 9, 1, '', 43728307660783616, '2022-07-24 17:15:39', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43749175543726080, '操作日志业务类型', 'logOperationBusinessType', 10, 1, '', 43728307660783616, '2022-07-24 17:23:36', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43749667023880192, '操作日志操作类型', 'logOperationType', 11, 1, '', 43728307660783616, '2022-07-24 17:25:33', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (43831966709055488, '操作日志状态', 'logOperationStatus', 12, 1, '', 43728307660783616, '2022-07-24 22:52:35', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (52090055367327744, '系统参数配置类型', 'sysParamType', 13, 1, '', 43728307660783616, '2022-08-16 17:47:17', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (53847856938745856, '博客文章标识', 'blogArticleFlag', 14, 1, '', 43728307660783616, '2022-08-21 14:12:10', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (54337945687031808, '博客文章推荐等级', 'blogArticleRecommendLevel', 15, 1, '', 43728307660783616, '2022-08-22 22:39:36', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (61592750092451840, '博客文章状态', 'blogArticleStatus', 16, 1, '', 43728307660783616, '2022-09-11 23:07:36', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (77901314503213056, '评论状态', 'blogCommentStatus', 17, 1, '', 43728307660783616, '2022-10-26 23:12:01', NULL, NULL);
INSERT INTO `sys_dictionary` VALUES (78246041425543168, '通用是否', 'yesNo', 1, 1, '', 43728307660783616, '2022-10-27 22:01:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dictionary_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_data`;
CREATE TABLE `sys_dictionary_data`  (
  `id` bigint NOT NULL COMMENT '字典数据 id',
  `dictionary_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典标签',
  `dictionary_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '字典值',
  `fk_dictionary_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属字典 id',
  `class_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '样式类型（primary，success等）',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统字典数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dictionary_data
-- ----------------------------
INSERT INTO `sys_dictionary_data` VALUES (43739723671273472, '正常', '1', '43739630905851904', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 16:46:03', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43739751852802048, '停用', '2', '43739630905851904', 'danger', 2, 1, '', 43728307660783616, '2022-07-24 16:46:09', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43744355986440192, '后台用户', '1', '43739903539806208', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 17:04:27', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43744417940504576, '博客用户', '2', '43739903539806208', 'warning', 2, 1, '', 43728307660783616, '2022-07-24 17:04:42', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43744494792736768, '保密', '1', '43740085404827648', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 17:05:00', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43744558772649984, '男', '2', '43740085404827648', 'success', 2, 1, '', 43728307660783616, '2022-07-24 17:05:16', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43744612069670912, '女', '3', '43740085404827648', 'warning', 3, 1, '', 43728307660783616, '2022-07-24 17:05:28', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745015746265088, '缓存', '1', '43740399080046592', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 17:07:04', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745054946230272, '不缓存', '2', '43740399080046592', 'success', 2, 1, '', 43728307660783616, '2022-07-24 17:07:14', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745129718087680, '是', '1', '43740514784116736', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 17:07:32', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745161041149952, '否', '2', '43740514784116736', 'success', 2, 1, '', 43728307660783616, '2022-07-24 17:07:39', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745249540964352, '显示', '1', '43740632493064192', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 17:08:00', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745280234881024, '隐藏', '2', '43740632493064192', 'success', 2, 1, '', 43728307660783616, '2022-07-24 17:08:08', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745438393696256, '默认(default)', 'default', '43741186304770048', 'default', 1, 1, '', 43728307660783616, '2022-07-24 17:08:45', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745535164678144, '主要(primary)', 'primary', '43741186304770048', 'primary', 2, 1, '', 43728307660783616, '2022-07-24 17:09:08', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745625509986304, '成功(success)', 'success', '43741186304770048', 'success', 3, 1, '', 43728307660783616, '2022-07-24 17:09:30', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745706313252864, '信息(info)', 'info', '43741186304770048', 'info', 4, 1, '', 43728307660783616, '2022-07-24 17:09:49', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745788475473920, '警告(warning)', 'warning', '43741186304770048', 'warning', 5, 1, '', 43728307660783616, '2022-07-24 17:10:09', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43745861963874304, '危险(danger)', 'danger', '43741186304770048', 'danger', 6, 1, '', 43728307660783616, '2022-07-24 17:10:26', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43747219504889856, '成功', '1', '43747174940409856', 'success', 1, 1, '', 43728307660783616, '2022-07-24 17:15:50', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43747249070538752, '失败', '2', '43747174940409856', 'danger', 2, 1, '', 43728307660783616, '2022-07-24 17:15:57', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749310080221184, '其他', '1', '43749175543726080', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 17:24:08', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749355009605632, '新增', '2', '43749175543726080', 'info', 2, 1, '', 43728307660783616, '2022-07-24 17:24:19', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749395618856960, '修改', '3', '43749175543726080', 'warning', 3, 1, '', 43728307660783616, '2022-07-24 17:24:29', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749433191432192, '删除', '4', '43749175543726080', 'danger', 4, 1, '', 43728307660783616, '2022-07-24 17:24:38', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749748162691072, '其他', '1', '43749667023880192', 'default', 1, 1, '', 43728307660783616, '2022-07-24 17:25:53', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749792488095744, '后台用户', '2', '43749667023880192', 'primary', 2, 1, '', 43728307660783616, '2022-07-24 17:26:03', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749847500587008, '手机端用户', '3', '43749667023880192', 'info', 3, 1, '', 43728307660783616, '2022-07-24 17:26:16', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43749905755275264, '博客用户', '4', '43749667023880192', 'warning', 4, 1, '', 43728307660783616, '2022-07-24 17:26:30', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43760754016387072, 'GET', 'GET', '43741186479310919', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 18:09:37', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43760839513079808, 'POST', 'POST', '43741186479310919', 'info', 2, 1, '', 43728307660783616, '2022-07-24 18:09:57', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43760910166130688, 'PUT', 'PUT', '43741186479310919', 'warning', 3, 1, '', 43728307660783616, '2022-07-24 18:10:14', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43760951937204224, 'DELETE', 'DELETE', '43741186479310919', 'danger', 4, 1, '', 43728307660783616, '2022-07-24 18:10:24', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43832012938674176, '成功', '1', '43831966709055488', 'primary', 1, 1, '', 43728307660783616, '2022-07-24 22:52:46', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (43832045444530176, '异常', '2', '43831966709055488', 'danger', 2, 1, '', 43728307660783616, '2022-07-24 22:52:54', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (52090134002139136, '系统内置', '1', '52090055367327744', 'primary', 1, 1, '', 43728307660783616, '2022-08-16 17:47:36', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (53847922655100928, '原创', '1', '53847856938745856', 'primary', 1, 1, '', 43728307660783616, '2022-08-21 14:12:25', 43728307660783616, '2022-08-21 14:13:01');
INSERT INTO `sys_dictionary_data` VALUES (53848141643907072, '转载', '2', '53847856938745856', 'info', 2, 1, '', 43728307660783616, '2022-08-21 14:13:17', 43728307660783616, '2022-08-21 14:13:34');
INSERT INTO `sys_dictionary_data` VALUES (53848177941413888, '整理', '3', '53847856938745856', 'warning', 3, 1, '', 43728307660783616, '2022-08-21 14:13:26', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (54338027232690176, '一级推荐', '1', '54337945687031808', '', 1, 1, '', 43728307660783616, '2022-08-22 22:39:55', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (54338067770638336, '二级推荐', '2', '54337945687031808', '', 2, 1, '', 43728307660783616, '2022-08-22 22:40:05', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (54338099341164544, '三级推荐', '3', '54337945687031808', '', 3, 1, '', 43728307660783616, '2022-08-22 22:40:12', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (54338131029131264, '四级推荐', '4', '54337945687031808', '', 4, 1, '', 43728307660783616, '2022-08-22 22:40:20', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (61592813778763776, '草稿', '1', '61592750092451840', 'primary', 1, 1, '', 43728307660783616, '2022-09-11 23:07:51', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (61592860348121088, '发布', '2', '61592750092451840', 'success', 2, 1, '', 43728307660783616, '2022-09-11 23:08:02', 43728307660783616, '2022-09-11 23:08:20');
INSERT INTO `sys_dictionary_data` VALUES (61592899577446400, '下架', '3', '61592750092451840', 'danger', 3, 1, '', 43728307660783616, '2022-09-11 23:08:12', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (67409162056761344, '博客文章', '2', '52090055367327744', 'info', 2, 1, '', 43728307660783616, '2022-09-28 00:19:57', 43728307660783616, '2022-10-06 17:10:58');
INSERT INTO `sys_dictionary_data` VALUES (77901485827948544, '待审核', '1', '77901314503213056', 'primary', 1, 1, '', 43728307660783616, '2022-10-26 23:12:42', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (77901539477291008, '通过', '2', '77901314503213056', 'success', 2, 1, '', 43728307660783616, '2022-10-26 23:12:54', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (77901592140972032, '驳回', '3', '77901314503213056', 'info', 3, 1, '', 43728307660783616, '2022-10-26 23:13:07', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (78246089743925248, '是', '1', '78246041425543168', 'primary', 1, 1, '', 43728307660783616, '2022-10-27 22:02:02', NULL, NULL);
INSERT INTO `sys_dictionary_data` VALUES (78246125886242816, '否', '2', '78246041425543168', 'danger', 2, 1, '', 43728307660783616, '2022-10-27 22:02:10', NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL COMMENT '菜单 id',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单类型（D 目录，M 菜单，B 按钮）',
  `permission_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '权限标识',
  `router_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
  `component_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '组件路径',
  `router_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由参数',
  `has_frame` tinyint(1) NULL DEFAULT 2 COMMENT '是否为外链（1是，2否）',
  `has_cache` tinyint(1) NULL DEFAULT 1 COMMENT '是否缓存（1缓存，2不缓存）',
  `menu_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单图标',
  `show_status` tinyint(1) NULL DEFAULT 1 COMMENT '显示状态（1显示，2隐藏）',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (43737833021636608, '系统管理', 0, 1, 'D', NULL, 'system', NULL, NULL, 2, 1, 'system', 1, 1, 43728307660783616, '2022-07-24 16:38:32', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43738261293629440, '用户管理', 43737833021636608, 1, 'M', 'system:user:page', 'user', 'system/user/index', NULL, 2, 1, 'user', 1, 1, 43728307660783616, '2022-07-24 16:40:14', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43738420110950400, '角色管理', 43737833021636608, 2, 'M', 'system:role:page', 'role', 'system/role/index', NULL, 2, 1, 'peoples', 1, 1, 43728307660783616, '2022-07-24 16:40:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43738641683447808, '菜单管理', 43737833021636608, 3, 'M', 'system:menu:list', 'menu', 'system/menu/index', NULL, 2, 1, 'menu', 1, 1, 43728307660783616, '2022-07-24 16:41:45', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43738896395141120, '数据字典', 43737833021636608, 4, 'M', 'system:dictionary:page', 'dictionary', 'system/dictionary/index', NULL, 2, 1, 'dict', 1, 1, 43728307660783616, '2022-07-24 16:42:46', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43739074015526912, 'API 管理', 43737833021636608, 5, 'M', 'system:apiCatetory:page', 'api', 'system/api/category', NULL, 2, 1, 'documentation', 1, 1, 43728307660783616, '2022-07-24 16:43:28', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43746238872420352, '日志审计', 0, 2, 'D', '', 'log', '', '', 2, 1, 'edit', 1, 1, 43728307660783616, '2022-07-24 17:11:56', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43746395802304512, '登录日志', 43746238872420352, 1, 'M', 'log:login:page', 'login', 'system/log/login/index', '', 2, 1, 'logininfor', 1, 1, 43728307660783616, '2022-07-24 17:12:34', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43746859121901568, '操作日志', 43746238872420352, 2, 'M', 'log:operation:page', 'operation', 'system/log/operation/index', '', 2, 1, 'form', 1, 1, 43728307660783616, '2022-07-24 17:14:24', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43779604086784000, '新增', 43738261293629440, 1, 'B', 'system:user:add', '', 'system/log/operation/index', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:24:31', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43779675532558336, '修改', 43738261293629440, 2, 'B', 'system:user:update', '', 'system/log/operation/index', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:24:48', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43779734387032064, '删除', 43738261293629440, 3, 'B', 'system:user:delete', '', 'system/log/operation/index', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:25:02', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43779823738290176, '重置密码', 43738261293629440, 4, 'B', 'system:user:resetPassword', '', 'system/log/operation/index', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:25:23', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43779962246791168, '新增', 43738420110950400, 1, 'B', 'system:role:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:25:56', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780015896133632, '修改', 43738420110950400, 2, 'B', 'system:role:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:26:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780063644090368, '删除', 43738420110950400, 3, 'B', 'system:role:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:26:21', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780156136882176, '分配用户', 43738420110950400, 4, 'B', 'system:role:authUser', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:26:43', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780326794723328, '取消用户授权', 43738420110950400, 5, 'B', 'system:user:deleteAuthUser', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:27:23', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780526653308928, '新增', 43738641683447808, 1, 'B', 'system:menu:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:28:11', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780571997929472, '修改', 43738641683447808, 2, 'B', 'system:menu:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:28:22', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780625399808000, '删除', 43738641683447808, 3, 'B', 'system:menu:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:28:35', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780699760623616, '分配 API', 43738641683447808, 4, 'B', 'system:menu:allocateApi', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:28:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43780957378969600, '新增', 43738896395141120, 1, 'B', 'system:dictionary:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:29:54', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43781009912627200, '修改', 43738896395141120, 2, 'B', 'system:dictionary:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:30:06', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43781060101668864, '删除', 43738896395141120, 3, 'B', 'system:dictionary:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:30:18', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43781692732735488, '分配数据', 43738896395141120, 4, 'B', 'system:dictionary:data:page', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:32:49', 43728307660783616, '2022-09-12 16:32:11');
INSERT INTO `sys_menu` VALUES (43781756515516416, '数据新增', 43738896395141120, 5, 'B', 'system:dictionaryData:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:33:04', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43781829500600320, '数据修改', 43738896395141120, 6, 'B', 'system:dictionary:data:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:33:22', 43728307660783616, '2022-09-12 16:32:18');
INSERT INTO `sys_menu` VALUES (43781877487632384, '数据删除', 43738896395141120, 7, 'B', 'system:dictionary:data:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 19:33:33', 43728307660783616, '2022-09-12 16:32:23');
INSERT INTO `sys_menu` VALUES (43820727559782400, '分类新增', 43739074015526912, 1, 'B', 'system:apiCategory:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:07:56', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43820963283861504, '分类修改', 43739074015526912, 2, 'B', 'system:apiCategory:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:08:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43821061610930176, '分类删除', 43739074015526912, 3, 'B', 'system:apiCategory:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:09:15', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43821227462098944, '分配数据', 43739074015526912, 4, 'B', 'system:api:page', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:09:55', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43821354134274048, '数据新增', 43739074015526912, 5, 'B', 'system:api:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:10:25', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43821418193879040, '数据修改', 43739074015526912, 6, 'B', 'system:api:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:10:40', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43821478692519936, '数据删除', 43739074015526912, 7, 'B', 'system:api:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-07-24 22:10:55', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52073868344426496, '参数配置', 43737833021636608, 6, 'M', 'system:param:page', 'param', 'system/param/index', '', 2, 1, 'edit', 1, 1, 43728307660783616, '2022-08-16 16:42:58', 43728307660783616, '2022-08-16 16:59:24');
INSERT INTO `sys_menu` VALUES (52099144042414080, '博客管理', 0, 3, 'D', '', 'blog', '', '', 2, 1, 'theme', 1, 1, 43728307660783616, '2022-08-16 18:23:24', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52099447332536320, '写文章', 52099144042414080, 1, 'M', 'blog:aritcle:add', 'article/write', 'blog/article/write', '', 2, 1, 'edit', 1, 1, 43728307660783616, '2022-08-16 18:24:36', 43728307660783616, '2022-09-12 09:47:29');
INSERT INTO `sys_menu` VALUES (52099788358811648, '文章管理', 52099144042414080, 2, 'M', 'blog:article:page', 'article', 'blog/article/index', '', 2, 1, 'list', 1, 1, 43728307660783616, '2022-08-16 18:25:58', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52099936145113088, '新增', 52099788358811648, 1, 'B', 'blog:article:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 18:26:33', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52100202487611392, '修改', 52099788358811648, 2, 'B', 'blog:article:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 18:27:36', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52100286625349632, '删除', 52099788358811648, 3, 'B', 'blog:article:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 18:27:56', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114381915291648, '分类管理', 52099144042414080, 3, 'M', 'blog:category:page', 'category', 'blog/category/index', '', 2, 1, 'rate', 1, 1, 43728307660783616, '2022-08-16 19:23:57', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114517684912128, '新增', 52114381915291648, 1, 'B', 'blog:category:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 19:24:29', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114568226275328, '修改', 52114381915291648, 2, 'B', 'blog:category:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 19:24:41', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114614602694656, '删除', 52114381915291648, 3, 'B', 'blog:category:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 19:24:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114878701240320, '标签管理', 52099144042414080, 4, 'M', 'blog:tag:page', 'tag', 'blog/tag/index', '', 2, 1, 'tag', 1, 1, 43728307660783616, '2022-08-16 19:25:55', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114929196466176, '新增', 52114878701240320, 1, 'B', 'blog:tag:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 19:26:07', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52114968178327552, '修改', 52114878701240320, 2, 'B', 'blog:tag:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 19:26:17', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52115022117076992, '删除', 52114878701240320, 3, 'B', 'blog:tag:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 19:26:30', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52132922030817280, '新增', 52073868344426496, 1, 'B', 'system:param:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 20:37:37', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52133024904511488, '修改', 52073868344426496, 2, 'B', 'system:param:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 20:38:02', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52133076263763968, '删除', 52073868344426496, 3, 'B', 'system:param:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-08-16 20:38:14', NULL, NULL);
INSERT INTO `sys_menu` VALUES (55073494739714048, '图片分类管理', 52099144042414080, 4, 'M', 'blog:picture:category:page', 'picture/category', 'blog/picture/category', '', 2, 1, 'validCode', 1, 1, 43728307660783616, '2022-08-24 23:22:24', NULL, NULL);
INSERT INTO `sys_menu` VALUES (61851202979102720, '新增', 55073494739714048, 1, 'B', 'blog:picture:category:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-09-12 16:14:36', NULL, NULL);
INSERT INTO `sys_menu` VALUES (61851247690383360, '修改', 55073494739714048, 2, 'B', 'blog:picture:category:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-09-12 16:14:47', NULL, NULL);
INSERT INTO `sys_menu` VALUES (61851299972382720, '删除', 55073494739714048, 3, 'B', 'blog:picture:category:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-09-12 16:14:59', NULL, NULL);
INSERT INTO `sys_menu` VALUES (61851660971933696, '图片管理', 55073494739714048, 4, 'B', 'blog:picture:page', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-09-12 16:16:25', NULL, NULL);
INSERT INTO `sys_menu` VALUES (61851798276669440, '图片上传', 55073494739714048, 5, 'B', 'blog:picture:upload', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-09-12 16:16:58', 43728307660783616, '2022-09-12 16:17:15');
INSERT INTO `sys_menu` VALUES (61851972713578496, '图片删除', 55073494739714048, 6, 'B', 'blog:picture:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-09-12 16:17:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES (70575761538416640, '友情链接', 52099144042414080, 5, 'M', 'blog:friendship:page', 'friendship', 'blog/friendship/index', '', 2, 1, 'link', 1, 1, 43728307660783616, '2022-10-06 18:02:53', 43728307660783616, '2022-10-06 18:07:57');
INSERT INTO `sys_menu` VALUES (70591784899575808, '新增', 70575761538416640, 1, 'B', 'blog:friendship:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-06 19:06:33', NULL, NULL);
INSERT INTO `sys_menu` VALUES (70591842319597568, '修改', 70575761538416640, 2, 'B', 'blog:friendship:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-06 19:06:47', NULL, NULL);
INSERT INTO `sys_menu` VALUES (70591937538686976, '删除', 70575761538416640, 3, 'B', 'blog:friendship:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-06 19:07:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES (77878458918633472, '评论管理', 52099144042414080, 6, 'M', 'blog:comment:page', 'comment', 'blog/comment/index', '', 2, 1, 'message', 1, 1, 43728307660783616, '2022-10-26 21:41:12', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78232604175761408, '审核', 77878458918633472, 1, 'B', 'blog:comment:audit', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-27 21:08:26', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78232661448982528, '删除', 77878458918633472, 2, 'B', 'blog:comment:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-27 21:08:40', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78243661845889024, '第三方登录配置', 52099144042414080, 7, 'M', 'blog:config:page', 'config', 'blog/config/index', '', 2, 1, 'build', 1, 1, 43728307660783616, '2022-10-27 21:52:23', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78261905201823744, '新增', 78243661845889024, 1, 'B', 'blog:config:add', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-27 23:04:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78261973308932096, '修改', 78243661845889024, 2, 'B', 'blog:config:update', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-27 23:05:08', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78262028635996160, '删除', 78243661845889024, 3, 'B', 'blog:config:delete', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-27 23:05:22', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78582377730801664, '关于我', 52099144042414080, 8, 'M', 'blog:about:get', 'about', 'blog/about/', '', 2, 1, 'edit', 1, 1, 43728307660783616, '2022-10-28 20:18:19', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78594439798325248, '编辑', 78582377730801664, 1, 'B', 'blog:about:edit', '', '', '', 2, 1, '', 1, 1, 43728307660783616, '2022-10-28 21:06:15', NULL, NULL);
INSERT INTO `sys_menu` VALUES (78595081065463808, '推荐管理', 52099144042414080, 9, 'M', 'blog:recommend:list', 'recommend', 'blog/recommend', '', 2, 1, 'example', 1, 1, 43728307660783616, '2022-10-28 21:08:48', NULL, NULL);

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` bigint NOT NULL COMMENT '参数 id',
  `param_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数名称',
  `param_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数键',
  `param_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '参数值',
  `param_type` tinyint(1) NULL DEFAULT NULL COMMENT '参数类型（1 系统参数）',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES (52091717515476992, '默认密码', 'SYS_DEFAULT_PASSWORD', 'geshanzsq@2022', 1, 1, '管理员默认密码', 43728307660783616, '2022-08-16 17:53:53', NULL, NULL);
INSERT INTO `sys_param` VALUES (67408901376573440, '文章二级推荐', 'BLOG_ARTICLE_SECOND_RECOMMEND', '2', 2, 3, '首页二级推荐数量', 43728307660783616, '2022-09-28 00:18:54', 43728307660783616, '2022-09-28 23:49:18');
INSERT INTO `sys_param` VALUES (67409686390898688, '文章三级推荐', 'BLOG_ARTICLE_THIRD_RECOMMEND', '5', 1, 4, '首页右侧三级推荐（好文推荐）数量', 43728307660783616, '2022-09-28 00:22:02', 43728307660783616, '2022-09-28 23:49:43');
INSERT INTO `sys_param` VALUES (67764089916489728, '文章一级推荐', 'BLOG_ARTICLE_ONE_RECOMMEND', '5', 2, 2, '首页一级推荐数量', 43728307660783616, '2022-09-28 23:50:18', 43728307660783616, '2022-09-28 23:50:56');
INSERT INTO `sys_param` VALUES (67765734880575488, '留言板', 'BOARD_OPEN_STATUS', 'true', 1, 5, '留言板开启状态', 43728307660783616, '2022-09-28 23:56:50', 43728307660783616, '2022-09-29 00:21:32');
INSERT INTO `sys_param` VALUES (70629177245564928, '相关文章', 'BLOG_ARTICLE_ABOUT', '6', 2, 6, '相关文章数量', 43728307660783616, '2022-10-06 21:35:08', NULL, NULL);
INSERT INTO `sys_param` VALUES (70810422013329408, '评论', 'BLOG_ARTICLE_COMMENT_OPEN', 'true', 2, 7, '文章评论是否开启', 43728307660783616, '2022-10-07 09:35:20', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT '角色 id',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_role_code`(`role_code`) USING BTREE COMMENT '角色编码唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (43735562707795968, '超级管理员', 'superAdmin', 1, 1, '', 43728307660783616, '2022-07-24 16:29:31', NULL, NULL);
INSERT INTO `sys_role` VALUES (43767332559912960, '普通角色', 'commonRole', 2, 1, '', 43728307660783616, '2022-07-24 18:35:45', 43728307660783616, '2022-10-31 00:02:36');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色菜单 id',
  `fk_role_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色 id',
  `fk_menu_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('79363597171425280', '43767332559912960', '43737833021636608');
INSERT INTO `sys_role_menu` VALUES ('79363597179813888', '43767332559912960', '43746238872420352');
INSERT INTO `sys_role_menu` VALUES ('79363597184008192', '43767332559912960', '52099144042414080');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL COMMENT '用户 id',
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `nick_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT 1 COMMENT '用户性别（1 保密，2 男，3 女）',
  `user_type` tinyint(1) NULL DEFAULT 1 COMMENT '用户类型（1 后台用户，2 博客用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `mobile_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号码',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像地址',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1 正常，2 停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '删除标志（1 未删除，2 已删除）',
  `fk_create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人用户 id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_modify_user_id` bigint NULL DEFAULT NULL COMMENT '修改人用户 id',
  `gmt_modify` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (43728307660783616, 'admin', '管理员', '$2a$10$NbKEKO1fPJvRx1Pto023u.6pGpbFwA4JdWWrtt44nRs9/KO6NX/De', 1, 1, '497301391@qq.com', '', '', 1, '我是管理员', 1, 43728307660783616, '2022-07-24 16:02:02', 43728307660783616, '2022-08-16 16:41:58');
INSERT INTO `sys_user` VALUES (43767104519798784, 'geshanzsq', '格姗知识圈', '$2a$10$rm5jwWc6lRM11Q.OOEH2T.t41GnkzITcvVAI19LdAicn5/AJdD0im', 3, 1, '497301391@qq.com', '', '', 1, '', 1, 43728307660783616, '2022-07-24 18:34:51', 43728307660783616, '2022-08-15 00:03:28');
INSERT INTO `sys_user` VALUES (43767196689629184, 'xgz', '小格子', '$2a$10$mh3A6xcNuwM6zfjQgQ/xHOmilj92Vj1JWbiB9VTktUD7zccynLD02', 2, 1, '497301391@qq.com', '', '', 1, '', 1, 43728307660783616, '2022-07-24 18:35:13', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL COMMENT '用户角色 id',
  `fk_user_id` bigint NOT NULL COMMENT '用户 id',
  `fk_role_id` bigint NOT NULL COMMENT '角色 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (43737619212795904, 43728307660783616, 43735562707795968);
INSERT INTO `sys_user_role` VALUES (43767363438379008, 43767104519798784, 43767332559912960);
INSERT INTO `sys_user_role` VALUES (43767363438379009, 43767196689629184, 43767332559912960);

SET FOREIGN_KEY_CHECKS = 1;
