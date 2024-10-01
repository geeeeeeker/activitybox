package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool;

import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option.ThanksAwardOption;
import com.uxiangtech.activitybox.sdk.award.Award;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.award.AwardOption;
import com.uxiangtech.activitybox.sdk.award.AwardPool;

public class AwardDrawingResultImpl implements AwardDrawingResult {

  private final AwardOption awardOption;

  public AwardDrawingResultImpl(final AwardOption awardOption) {
    this.awardOption = awardOption;
  }

  @Override
  public String getAwardId() {
    final Award award =
      this.awardOption.getAward();
    if (null == award) {
      return null;
    } else {
      return award.getId();
    }
  }

  @Override
  public String getAwardPoolId() {
    final AwardPool awardPool =
      this.awardOption.getAwardPool();
    if (null == awardPool) {
      return null;
    } else {
      return awardPool.getId();
    }
  }

  @Override
  public String getAwardOptionId() {
    return this.awardOption.getId();
  }

  @Override
  public String getAwardOptionName() {
    return this.awardOption.getName();
  }

  @Override
  public Boolean isThanks() {
    return this.awardOption instanceof ThanksAwardOption;
  }

  @Override
  public String getIcon() {
    return this.awardOption.getIcon();
  }

  /**
   *
   * @return
   */
  @Override
  public String getLink() {
    return null;
  }
}
