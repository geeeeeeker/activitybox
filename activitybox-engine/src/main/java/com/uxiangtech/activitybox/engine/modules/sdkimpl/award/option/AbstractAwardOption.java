package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option;

import com.uxiangtech.activitybox.sdk.award.Award;
import com.uxiangtech.activitybox.sdk.award.AwardOption;
import com.uxiangtech.activitybox.sdk.award.AwardPool;
import com.uxiangtech.activitybox.sdk.attribute.AwardOptionAttribute;

import java.math.BigDecimal;

public abstract class AbstractAwardOption implements AwardOption {

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

  /**
   * 奖项对应的奖品
   */
  private final Award award;

  /**
   * 奖项归属的奖池
   */
  private final AwardPool awardPool;

  public AbstractAwardOption(String id, String name, BigDecimal probability, Long stock, Award award, AwardPool awardPool) {
    this.id = id;
    this.name = name;
    this.probability = probability;
    this.stock = stock;
    this.award = award;
    this.awardPool = awardPool;
  }

  public AbstractAwardOption(AwardOptionAttribute attribute, Award award, AwardPool awardPool) {
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
  public String getIcon() {
    return this.award.getIcon();
  }

  @Override
  public BigDecimal getProbability() {
    return this.probability;
  }

  @Override
  public Long getTotalStock() {
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
