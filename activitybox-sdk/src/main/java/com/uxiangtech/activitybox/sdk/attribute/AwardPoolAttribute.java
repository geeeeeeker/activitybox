package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

import java.util.List;

/**
 * 奖池属性
 */
@Data
public class AwardPoolAttribute {
  private String id;
  private String name;
  /**
   * 抽奖规则类型：RANDOM, DIRECT
   */
  private String type;
  /**
   * 奖项列表
   */
  private List<AwardOptionAttribute> options;
}
