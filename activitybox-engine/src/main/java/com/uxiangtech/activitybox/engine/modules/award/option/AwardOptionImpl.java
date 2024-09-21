package com.uxiangtech.activitybox.engine.modules.award.option;

import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.pool.AwardPool;
import com.uxiangtech.activitybox.sdk.attribute.AwardOptionAttribute;

import java.math.BigDecimal;

public class AwardOptionImpl implements AwardOption {


  private final String id;
  /**
   * 奖项名称
   */
  private final String name;
  /**
   * 出奖概率
   */
  private BigDecimal probability;
  /**
   * 奖项库存
   */
  private Long stock;

  private Award award;

  private AwardPool awardPool;

  public AwardOptionImpl(String id, String name, BigDecimal probability, Long stock, Award award, AwardPool awardPool) {
    this.id = id;
    this.name = name;
    this.probability = probability;
    this.stock = stock;
    this.award = award;
    this.awardPool = awardPool;
  }

  public AwardOptionImpl(AwardOptionAttribute attribute, Award award, AwardPool awardPool) {
    this(attribute.getId(), attribute.getName(), attribute.getProbability(), attribute.getStock(), award, awardPool);
  }


  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }


  @Override
  public BigDecimal getProbability() {
    return this.probability;
  }

  @Override
  public Long getStock() {
    return this.stock;
  }

  @Override
  public AwardPool getAwardPool() {
    return this.awardPool;
  }

  @Override
  public Award getAward() {
    return this.award;
  }
}
