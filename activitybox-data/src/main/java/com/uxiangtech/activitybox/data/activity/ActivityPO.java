package com.uxiangtech.activitybox.data.activity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_activity")
public class ActivityPO {

  /**
   * 活动ID
   */
  @TableId
  private Long id;

//  /**
//   * 租户ID
//   */
//  private Long tenantId;

  /**
   * 活动名称
   */
  private String name;

  /**
   * 活动文案描述
   */
  private String ruleDesc;

  /**
   * 活动开始时间
   */
  private LocalDateTime startTime;

  /**
   * 活动结束时间
   */
  private LocalDateTime endTime;

  /**
   * 活动状态: DRAFT-草稿 ONLINE-已上线 OFFLINE-已下线
   */
  private String status;

  /**
   * 活动配置JSON：包含玩法、页面、奖品、奖池（内部包含奖项）、变量
   */
  private String attribute;

  /**
   * 删除标记
   */
  private Boolean isDeleted;

  /**
   * 创建时间
   */
  private LocalDateTime gmtCreate;

  /**
   * 更新时间
   */
  private LocalDateTime gmtModified;

}
