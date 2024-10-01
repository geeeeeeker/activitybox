package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

@Data
public class AwardAttribute {

  /**
   * 奖品ID
   */
  private String id;
  /**
   * 奖品名称
   */
  private String name;
  /**
   * 奖品类型：道具-PROP, 商品-GOODS
   */
  private String type;

  /**
   * 奖品图标
   */
  private String icon;

  /**
   * 奖品对应的商品ID，支持对接商品中心或权益中心
   */
  private String refId;

  /**
   * 奖品库存，即各个规则可以配置的总库存
   */
  private Long stock;
}
