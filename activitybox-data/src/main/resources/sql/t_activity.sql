CREATE TABLE `t_activity`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `name`         varchar(255) NOT NULL COMMENT '活动名称',
    `rule_desc`    varchar(1024) NULL COMMENT '活动规则描述',
    `start_time`   datetime NULL COMMENT '活动开始时间',
    `end_time`     datetime NULL COMMENT '活动结束时间',
    `status`       varchar(16)  NOT NULL COMMENT '活动状态: DRAFT-草稿 ONLINE-已上线 OFFLINE-已下线',
    `attribute`    text NULL COMMENT '活动配置JSON',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动信息表';

INSERT INTO `t_activity` (`name`, `rule_desc`, `start_time`, `end_time`, `status`, `attributes`) VALUES (样例活动', NULL, NULL, NULL, 'ONLINE', '');

