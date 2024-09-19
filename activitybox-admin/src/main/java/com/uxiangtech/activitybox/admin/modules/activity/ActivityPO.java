package com.uxiangtech.activitybox.admin.modules.activity;

import cn.org.atool.fluent.mybatis.annotation.TableField;
import com.uxiangtech.activitybox.admin.modules.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ActivityPO extends BasePO {

  /**
   * 活动名称
   */
  @TableField("name")
  private String name;

  /**
   * 活动描述
   */
  @TableField("desc")
  private String desc;

  /**
   * 活动开始时间
   */
  @TableField("start_time")
  private LocalDateTime startTime;

  /**
   * 活动结束时间
   */
  @TableField("end_time")
  private LocalDateTime endTime;

  /**
   * 活动配置属性，使用JSON格式
   */
  @TableField("attributes")
  private String attributes;

  /**
   * 活动状态: NEW, ONLINE, OFFLINE, STOPPED
   */
  @TableField("status")
  private String status;

  /**
   * 活动载入分区，暂时不用
   */
  @TableField("region")
  private String region;

}
