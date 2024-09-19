package com.uxiangtech.activitybox.engine.modules.award.option;

import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.pool.AwardPool;

import java.math.BigDecimal;

/**
 * 安慰奖
 */
public class NoAwardOption implements AwardOption {

  @Override
  public String getId() {
    return "no";
  }

  @Override
  public String getName() {
    return "谢谢参与";
  }

  @Override
  public BigDecimal getProbability() {
    return null;
  }

  @Override
  public Long getStock() {
    return null;
  }

  @Override
  public AwardPool getAwardRule() {
    return null;
  }

  @Override
  public Award getAward() {
    return null;
  }
}
