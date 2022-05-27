DROP TABLE IF EXISTS `md_content`;
CREATE TABLE `md_content`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `articleid`   varchar(255) NOT NULL COMMENT '文章唯一标识',
    `title`       varchar(255) DEFAULT NULL COMMENT '文章标题',
    `desc`        varchar(255) NOT NULL COMMENT '文章描述',
    `content`     text COMMENT 'Markdown文本内容',
    `create_by`   varchar(255) DEFAULT NULL COMMENT '创建者，文章作者',
    `update_by`   varchar(255) DEFAULT NULL COMMENT '更新者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_articleid` (`articleid`),
    KEY           `log_create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='Markdown文件内容表'