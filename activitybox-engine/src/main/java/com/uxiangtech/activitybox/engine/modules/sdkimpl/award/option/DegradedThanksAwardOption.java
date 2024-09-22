package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option;

import com.uxiangtech.activitybox.sdk.award.Award;
import com.uxiangtech.activitybox.sdk.award.AwardOption;
import com.uxiangtech.activitybox.sdk.award.AwardPool;

import java.math.BigDecimal;

public class DegradedThanksAwardOption implements AwardOption {
  @Override
  public String getId() {
    return "thanks";
  }

  @Override
  public String getName() {
    return "谢谢参与";
  }

  /**
   * TODO 配置谢谢参与图片
   * @return
   */
  @Override
  public String getIcon() {
    return "暂时未配置";
  }

  @Override
  public BigDecimal getProbability() {
    return null;
  }

  @Override
  public Long getTotalStock() {
    return Long.MAX_VALUE;
  }

  @Override
  public AwardPool getAwardPool() {
    return null;
  }

  @Override
  public Award getAward() {
    return null;
  }
}
