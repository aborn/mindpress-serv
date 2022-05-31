# mindpress-serv
[mindpress](https://github.com/aborn/mindpress) servce java language implementation.

## How to deploy
1. config your db  
   chang your db info /src/main/resources/*.yml
2. create tables 
   using following ddl to create tables)
3. start it. (boot your app)

## Create MYSQL tables (DDL)

1. **md_markdown_content**
url: **/api/mindpress/content**
```sql
CREATE TABLE `md_markdown_content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `articleid` varchar(255) NOT NULL COMMENT 'markdown article uniq id',
  `content` text COMMENT 'Markdown content',
  `create_by` varchar(255) DEFAULT NULL COMMENT 'caretor',
  `update_by` varchar(255) DEFAULT NULL COMMENT 'updater',
  `create_time` datetime DEFAULT NULL COMMENT 'create time',
  `update_time` datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_articleid` (`articleid`),
  KEY `log_create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='Markdown content info'
```

2. **md_markdown_meta**
url: **/api/mindpress/meta**
```sql
CREATE TABLE `md_markdown_meta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `articleid` varchar(255) NOT NULL COMMENT 'markdown article uniq id',
  `title` varchar(255) NOT NULL COMMENT 'title',
  `desc` varchar(255) NOT NULL COMMENT 'description',
  `tags` varchar(255) DEFAULT NULL COMMENT 'description',
  `space` varchar(255) DEFAULT 'default' COMMENT 'the space file belongs to',
  `is_public` tinyint(1) DEFAULT '0' COMMENT 'is public access, default no',
  `create_by` varchar(255) DEFAULT NULL COMMENT 'markdown creator',
  `update_by` varchar(255) DEFAULT NULL COMMENT 'markdown updater',
  `create_time` datetime DEFAULT NULL COMMENT 'crate time',
  `update_time` datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_articleid` (`articleid`),
  KEY `log_create_time_index` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='Markdown meta infos'
```

3. **md_markdown_space**
url: **/api/mindpress/space**
```sql
CREATE TABLE `md_markdown_space` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT 'space uniq name',
  `desc` varchar(255) DEFAULT NULL COMMENT 'space dscription',
  `owner` varchar(255) NOT NULL COMMENT 'space owner',
  `create_by` varchar(255) DEFAULT NULL COMMENT 'markdown creator',
  `update_by` varchar(255) DEFAULT NULL COMMENT 'markdown updater',
  `create_time` datetime DEFAULT NULL COMMENT 'create time',
  `update_time` datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_name` (`name`),
  KEY `log_create_time_index` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='Markdown space info'
```