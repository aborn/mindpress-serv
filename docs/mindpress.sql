DROP TABLE IF EXISTS `md_markdown_content`;
CREATE TABLE `md_markdown_content`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `articleid`   varchar(255) NOT NULL COMMENT 'markdown article uniq id',
    `content`     text COMMENT 'Markdown content',
    `create_by`   varchar(255) DEFAULT NULL COMMENT 'creator,author',
    `update_by`   varchar(255) DEFAULT NULL COMMENT 'updater',
    `create_time` datetime     DEFAULT NULL COMMENT 'create time',
    `update_time` datetime     DEFAULT NULL COMMENT 'update time',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_articleid` (`articleid`),
    KEY `log_create_time_index` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='Markdown content info';

DROP TABLE IF EXISTS `md_markdown_meta`;
CREATE TABLE `md_markdown_meta`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `articleid`   varchar(255) NOT NULL COMMENT 'markdown article uniq id',
    `title`       varchar(255) NOT NULL COMMENT 'title',
    `desc`        varchar(255) NOT NULL COMMENT 'description',
    `tags`        varchar(255) DEFAULT NULL COMMENT 'tags split by comma',
    `space`       varchar(255) DEFAULT 'default' COMMENT 'the space file belongs to',
    `is_public`   TINYINT(1)   DEFAULT 0 COMMENT 'is public access, default no',
    `create_by`   varchar(255) DEFAULT NULL COMMENT 'markdown creator',
    `update_by`   varchar(255) DEFAULT NULL COMMENT 'markdown updater',
    `create_time` datetime     DEFAULT NULL COMMENT 'crate time',
    `update_time` datetime     DEFAULT NULL COMMENT 'update time',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_articleid` (`articleid`),
    KEY `log_create_time_index` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='Markdown meta infos';

DROP TABLE IF EXISTS `md_markdown_space`;
CREATE TABLE `md_markdown_space`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(255) NOT NULL COMMENT 'space uniq name',
    `desc`        varchar(255) DEFAULT NULL COMMENT 'space description',
    `owner`       varchar(255) NOT NULL COMMENT 'space owner',
    `create_by`   varchar(255) DEFAULT NULL COMMENT 'markdown creator',
    `update_by`   varchar(255) DEFAULT NULL COMMENT 'markdown updater',
    `create_time` datetime     DEFAULT NULL COMMENT 'create time',
    `update_time` datetime     DEFAULT NULL COMMENT 'update time',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_name` (`name`),
    KEY `log_create_time_index` (`create_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC COMMENT ='Markdown space info';

