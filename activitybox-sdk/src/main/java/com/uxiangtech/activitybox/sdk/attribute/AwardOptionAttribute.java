package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AwardOptionAttribute {
  private String id;
  private String name;
  /**
   * 概率
   */
  private BigDecimal probability;
  /**
   * 奖项库存，库存不足则降级至谢谢参与
   */
  private Long stock;

  // TODO 未来增加各种抽象限制
}
