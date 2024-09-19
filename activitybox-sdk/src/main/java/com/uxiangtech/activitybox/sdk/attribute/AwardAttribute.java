package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

@Data
public class AwardAttribute {
  private String id;
  private String name;
  /**
   * 奖品类型：道具-PROP,
   */
  private String type;
  private String icon;
  private String link;
  /**
   * 奖品库存，即各个规则可以配置的总库存
   */
  private Long stock;
}
