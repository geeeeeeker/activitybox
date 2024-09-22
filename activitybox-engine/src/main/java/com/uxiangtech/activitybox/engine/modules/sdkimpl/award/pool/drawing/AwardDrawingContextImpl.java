package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool.drawing;

import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;

public class AwardDrawingContextImpl implements AwardDrawingContext {

  private final Long userId;

  public AwardDrawingContextImpl(final Long userId) {
    this.userId = userId;
  }

  @Override
  public Long getUserId() {
    return this.userId;
  }

  @Override
  public String getAwardPoolId() {
    return null;
  }

  @Override
  public String getAwardOptionId() {
    return null;
  }
}
