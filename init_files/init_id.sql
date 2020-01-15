CREATE
DATABASE `spring_blog`

USE `spring_blog`;

/*
//////////////////////////////
//config
//////////////////////////////
 */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config`
(
    `config_name`  VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Name of the configuration item',
    `config_value` VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'Configuration item value',
    `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    `update_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'modification time',
    PRIMARY KEY (`config_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `config` */

insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('footerAbout','your personal blog. have fun.','2020-01-10 20:33:23','2020-01-12 11:58:06');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('footerCopyRight','2019','2020-01-10 20:33:31','2020-01-12 11:58:06');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('footerPoweredBy','https://github.com/hexhoc','2020-01-10 20:33:36','2020-01-12 11:58:06');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('footerPoweredByURL','https://github.com/hexhoc','2020-01-10 20:33:39','2020-01-12 11:58:06');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('websiteDescription','personal blog SpringBoot2+Thymeleaf+Hibernate.','2020-01-10 20:33:04','2020-01-10 22:05:14');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('websiteIcon','/admin/dist/img/favicon.png','2020-01-10 20:33:11','2020-01-10 22:05:14');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('websiteLogo','/admin/dist/img/logo2.png','2020-01-10 20:33:08','2020-01-10 22:05:14');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('websiteName','personal blog','2020-01-10 20:33:01','2020-01-10 22:05:14');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('yourAvatar','/admin/dist/img/hexhoc.png','2020-01-10 20:33:14','2020-01-07 21:56:23');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('yourEmail','hexhoc@gmail.com','2020-01-10 20:33:17','2020-01-07 21:56:23');
insert  into `config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('yourName','hexhoc','2020-01-10 20:33:20','2020-01-07 21:56:23');



/*
//////////////////////////////
//articles
//////////////////////////////
 */

DROP TABLE IF EXISTS `articles`;

CREATE TABLE articles
(
    id             BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'blog table primary key id',
    category_id    INT(11) NOT NULL COMMENT 'blog category id',
    title          VARCHAR(255) COMMENT 'Article title',
    content        MEDIUMTEXT   NOT NULL COMMENT 'blog content',
    sub_url        VARCHAR(255) COMMENT 'Article custom path url',
    cover_image    VARCHAR(200) NOT NULL COMMENT 'blog cover image',
    enable_comment BOOLEAN NOT NULL DEFAULT '0' COMMENT '0-allow comment 1-not allow comment',
    is_deleted     BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes',
    status         BOOLEAN NOT NULL DEFAULT '0' COMMENT '0-draft 1-post',
    create_time    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Add time',
    update_time    datetime              DEFAULT CURRENT_TIMESTAMP COMMENT 'modification time',
    views          BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'How many times the article looked',
    PRIMARY KEY (id)
) engine = innodb;

/*Data for the table `spring_blog` */
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (1, 'Hello all', 'about', '/admin/dist/img/rand/33.jpg', '## About me\n\nI am a Java developer, with average skills and mediocre experience, but I have always been eager to make progress.', 20, 1, 219, 0, 0, '2019-03-12 00:31:15', '2019-11-12 00:31:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (2, 'Spring+SpringMVC+Hibernate(easyUI、AdminLte3)', '', '/admin/dist/img/rand/36.jpg', '## Practical chapter (paid tutorial)\n\n- [SSM builds a beautiful and practical management system]', 22, 1, 55, 0, 0, '2019-04-24 15:46:15', '2019-04-24 15:46:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (3, 'Spring+SpringMVC+Hibernate(easyUI、AdminLte3)', '', '/admin/dist/img/rand/36.jpg', '## Practical chapter (paid tutorial)\n\n- [SSM builds a beautiful and practical management system]', 22, 1, 55, 0, 0, '2019-04-24 15:46:15', '2019-04-24 15:46:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (4, 'SpringBoot. Series of tutorials', '', '/admin/dist/img/rand/29.jpg', '<h2 id=\"springboot2\">23 experiments to take you easy to play with Spring Boot</h2>\n\n- [**Introduction: "23 experiments to take you to play with Spring Boot easily"** ]', 24, 1, 10, 0, 0, '2019-05-13 09:58:54', '2019-05-13 09:58:54');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (5, 'Hello all', 'about', '/admin/dist/img/rand/33.jpg', '## About me\n\nI am a Java developer, with average skills and mediocre experience, but I have always been eager to make progress.', 20, 1, 219, 0, 0, '2019-03-12 00:31:15', '2019-11-12 00:31:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (6, 'Spring+SpringMVC+Hibernate(easyUI、AdminLte3)', '', '/admin/dist/img/rand/36.jpg', '## Practical chapter (paid tutorial)\n\n- [SSM builds a beautiful and practical management system]', 22, 1, 55, 0, 0, '2019-04-24 15:46:15', '2019-04-24 15:46:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (7, 'Spring+SpringMVC+Hibernate(easyUI、AdminLte3)', '', '/admin/dist/img/rand/36.jpg', '## Practical chapter (paid tutorial)\n\n- [SSM builds a beautiful and practical management system]', 22, 1, 55, 0, 0, '2019-04-24 15:46:15', '2019-04-24 15:46:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (8, 'SpringBoot. Series of tutorials', '', '/admin/dist/img/rand/29.jpg', '<h2 id=\"springboot2\">23 experiments to take you easy to play with Spring Boot</h2>\n\n- [**Introduction: "23 experiments to take you to play with Spring Boot easily"** ]', 24, 1, 10, 0, 0, '2019-05-13 09:58:54', '2019-05-13 09:58:54');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (9, 'Hello all', 'about', '/admin/dist/img/rand/33.jpg', '## About me\n\nI am a Java developer, with average skills and mediocre experience, but I have always been eager to make progress.', 20, 1, 219, 0, 0, '2019-03-12 00:31:15', '2019-11-12 00:31:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (10, 'Spring+SpringMVC+Hibernate(easyUI、AdminLte3)', '', '/admin/dist/img/rand/36.jpg', '## Practical chapter (paid tutorial)\n\n- [SSM builds a beautiful and practical management system]', 22, 1, 55, 0, 0, '2019-04-24 15:46:15', '2019-04-24 15:46:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (11, 'Spring+SpringMVC+Hibernate(easyUI、AdminLte3)', '', '/admin/dist/img/rand/36.jpg', '## Practical chapter (paid tutorial)\n\n- [SSM builds a beautiful and practical management system]', 22, 1, 55, 0, 0, '2019-04-24 15:46:15', '2019-04-24 15:46:15');
INSERT INTO `articles` (`id`, `title`, `sub_url`, `cover_image`, `content`, `category_id`, `status`, `views`, `enable_comment`, `is_deleted`, `create_time`, `update_time`) VALUES (12, 'SpringBoot. Series of tutorials', '', '/admin/dist/img/rand/29.jpg', '<h2 id=\"springboot2\">23 experiments to take you easy to play with Spring Boot</h2>\n\n- [**Introduction: "23 experiments to take you to play with Spring Boot easily"** ]', 24, 1, 10, 0, 0, '2019-05-13 09:58:54', '2019-05-13 09:58:54');

/*
//////////////////////////////
//articles_tags_relation
//////////////////////////////
 */

DROP TABLE IF EXISTS `articles_tags_relation`;

CREATE TABLE articles_tags_relation
(
    article_id BIGINT  NOT NULL,
    tag_id     INTEGER NOT NULL,
    PRIMARY KEY (article_id, tag_id)
) engine=innodb;


/*
//////////////////////////////
//categories
//////////////////////////////
*/

DROP TABLE IF EXISTS `categories`;

CREATE TABLE categories
(
    id          INT(11) NOT NULL AUTO_INCREMENT COMMENT 'category table primary key',
    name        VARCHAR(50) NOT NULL COMMENT 'category name',
    create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    grade       INT(11) NOT NULL DEFAULT '1' COMMENT 'The sort value of the category, the more used, the larger the value',
    icon        VARCHAR(50) NOT NULL COMMENT 'category icon',
    is_deleted  BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes',
    PRIMARY KEY (id)
) engine=innodb;

/*Data for the table `categories` */
INSERT INTO `categories` (`id`, `name`, `icon`, `grade`, `is_deleted`, `create_time`) VALUES (20, 'About', '/admin/dist/img/category/10.png', 8, 0, '2019-11-12 00:28:49');
INSERT INTO `categories` (`id`, `name`, `icon`, `grade`, `is_deleted`, `create_time`) VALUES (22, 'Advanced SSM Integration', '/admin/dist/img/category/02.png', 19, 0, '2019-11-12 10:42:25');
INSERT INTO `categories` (`id`, `name`, `icon`, `grade`, `is_deleted`, `create_time`) VALUES (24, 'Daily Essay', '/admin/dist/img/category/06.png', 22, 0, '2019-11-12 10:43:21');

/*
//////////////////////////////
//tags
//////////////////////////////
*/

DROP TABLE IF EXISTS `tags`;

CREATE TABLE tags
(
    id          INT(11) NOT NULL AUTO_INCREMENT COMMENT 'tag table primary key id',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    is_deleted  BOOLEAN NOT NULL DEFAULT '0' COMMENT 'Whether to delete 0=No 1=Yes',
    name        VARCHAR(100) NOT NULL COMMENT 'tag name',
    PRIMARY KEY (id)
) engine=innodb;

/*Data for the table `tb_blog_tag` */

INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (57,'cute_person',0,'2019-11-12 00:31:15');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (58,'now_reading_this',0,'2019-11-12 00:31:15');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (66,'Spring',0,'2019-11-12 10:55:14');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (67,'SpringMVC',0,'2019-11-12 10:55:14');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (68,'Hibernate',0,'2019-11-12 10:55:14');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (69,'easyUI',0,'2019-11-12 10:55:14');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (127,'contents',0,'2019-04-24 15:41:39');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (128,'AdminLte3',0,'2019-04-24 15:46:16');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (130,'SpringBoot',0,'2019-05-13 09:58:54');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (131,'Getting_started',0,'2019-05-13 09:58:54');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (132,'Practical_tutorial',0,'2019-05-13 09:58:54');
INSERT INTO `tags`(`id`, `name`, `is_deleted`, `create_time`) VALUES (133,'spring-boot-Enterprise-level-development',0,'2019-05-13 09:58:54');

/*
//////////////////////////////
//FOREIGN KEYS
//////////////////////////////
*/

ALTER TABLE articles
    ADD CONSTRAINT FK_Category FOREIGN KEY (category_id)
        REFERENCES categories (id);

ALTER TABLE articles_tags_relation
    ADD CONSTRAINT FK_Tag FOREIGN KEY (tag_id) REFERENCES
        tags (id);

ALTER TABLE articles_tags_relation
    ADD CONSTRAINT FK_Article FOREIGN KEY (article_id) REFERENCES
        articles (id);