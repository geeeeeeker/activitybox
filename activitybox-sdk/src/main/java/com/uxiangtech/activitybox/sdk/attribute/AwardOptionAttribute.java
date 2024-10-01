package com.uxiangtech.activitybox.sdk.attribute;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AwardOptionAttribute {
  private String id;

  /**
   * 奖品ID
   */
  @JSONField(name = "award_id")
  private String awardId;
  /**
   * 奖项名称
   */
  private String name;
  /**
   * 出奖概率
   */
  private BigDecimal probability;
  /**
   * 奖项库存，库存不足则降级至谢谢参与
   */
  private Long stock;

  // TODO 未来增加各种抽象限制

  @JSONField(name = "grant_qty")
  private Long grantQuantity;
}
