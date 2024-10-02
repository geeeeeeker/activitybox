package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool.drawing;

import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.award.AwardOption;

public class AwardDrawingContextImpl implements AwardDrawingContext {

  private final Long activityId;

  private final String playwayId;

  private final String actionId;

  private final Long userId;

  private final String awardPoolId;

  private AwardOption awardOption;

  public AwardDrawingContextImpl(Long activityId, String playwayId, String actionId, Long userId, String awardPoolId) {
    this.activityId = activityId;
    this.playwayId = playwayId;
    this.actionId = actionId;
    this.userId = userId;
    this.awardPoolId = awardPoolId;
  }

  @Override
  public Long getUserId() {
    return this.userId;
  }

  @Override
  public String getAwardPoolId() {
    return this.awardPoolId;
  }

  @Override
  public void setAwardOption(AwardOption awardOption) {
    this.awardOption = awardOption;
  }

  @Override
  public AwardOption getAwardOption() {
    return this.awardOption;
  }

  @Override
  public Long getActivityId() {
    return activityId;
  }

  @Override
  public String getPlaywayId() {
    return playwayId;
  }

  @Override
  public String getActionId() {
    return actionId;
  }
}
