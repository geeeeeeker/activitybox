package com.uxiangtech.activitybox.engine.modules.award.option;

import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.pool.AwardPool;

import java.math.BigDecimal;

public class CustomAwardOption implements AwardOption {


  private Award award;

  private AwardPool awardPool;





  @Override
  public String getId() {
    return null;
  }

  @Override
  public String getName() {
    return null;
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
