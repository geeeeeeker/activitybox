CREATE TABLE `t_user`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `tenant_id`    bigint       NOT NULL COMMENT '租户ID',
    `out_user_id`  varchar(255) NOT NULL COMMENT '外部用户ID',
    `out_username` varchar(255) NULL COMMENT '外部用户名',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_outuserid_tenantid` (`out_user_id`, `tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='统一用户表';


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


CREATE TABLE `t_invitation_code`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT,
    `activity_id`  bigint      NOT NULL COMMENT '活动ID',
    `user_id`      bigint      NOT NULL COMMENT '用户ID',
    `code`         varchar(32) NOT NULL COMMENT '邀请码',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    `gmt_create`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code_activityid` (`code`, `activity_id`),
    KEY            `idx_code_userid` (`code`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邀请码表';


CREATE TABLE `t_invitation_record`
(
    `id`           bigint   NOT NULL AUTO_INCREMENT,
    `activity_id`  bigint   NOT NULL COMMENT '活动ID',
    `inviter_id`   bigint   NOT NULL COMMENT '邀请人用户ID',
    `invitee_id`   bigint   NOT NULL COMMENT '受邀人用户ID',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    `gmt_create`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY            `idx_inviterid__activityid` (`inviter_id`, `activity_id`),
    KEY            `idx_inviteeid__activityid` (`invitee_id`, `activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邀请记录表';


CREATE TABLE `t_my_prop`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT,
    `activity_id`  bigint      NOT NULL COMMENT '活动ID',
    `user_id`      bigint      NOT NULL COMMENT '用户ID',
    `prop_id`      varchar(32) NOT NULL COMMENT '道具ID',
    `quantity`     bigint NULL DEFAULT '0' COMMENT '道具数量',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
    `gmt_create`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_propid_userid_activityid` (`prop_id`, `user_id`, `activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='我的道具表';


-- CREATE TABLE `t_my_award`
-- (
--     `id`           bigint      NOT NULL AUTO_INCREMENT,
--     `activity_id`  bigint      NOT NULL COMMENT '活动ID',
--     `user_id`      varchar(64) NOT NULL COMMENT '用户ID',
--     `award_id`     varchar(32) NOT NULL COMMENT '奖品ID',
--     `counter`      bigint NULL DEFAULT '0' COMMENT '计数器',
--     `is_deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
--     `gmt_create`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--     `gmt_modified` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--     PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='我的抽中奖品表';
