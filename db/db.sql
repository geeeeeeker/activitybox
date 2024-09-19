-- 活动信息表包含了营销活动的所有信息：活动名称、活动描述、活动状态、活动开始时间、活动结束时间、活动配置JSON等。
CREATE TABLE `t_activity`
(
    `id`           bigint(11) NOT NULL AUTO_INCREMENT,
    `name`         varchar(255) NOT NULL COMMENT '活动名称',
    `desc`         varchar(255) NULL COMMENT '活动描述',
    `status`       varchar(16)  NOT NULL DEFAULT '0' COMMENT '活动状态',
    `start_time`   datetime NULL COMMENT '活动开始时间',
    `end_time`     datetime NULL COMMENT '活动结束时间',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `gmt_create`   datetime     NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL COMMENT '修改时间',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动信息表';

