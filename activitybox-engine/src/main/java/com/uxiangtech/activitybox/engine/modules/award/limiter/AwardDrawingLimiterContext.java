package com.uxiangtech.activitybox.engine.modules.award.limiter;

import com.uxiangtech.activitybox.common.pipeline.PipelineContext;
import com.uxiangtech.activitybox.sdk.award.AwardOption;

public class AwardDrawingLimiterContext implements PipelineContext<AwardDrawingLimiterContext> {

  /**
   * 抽中的奖项
   */
  private final AwardOption awardOption;

  public AwardDrawingLimiterContext(AwardOption awardOption) {
    this.awardOption = awardOption;
  }

  @Override
  public void setValue(String key, Object value) {

  }

  @Override
  public Object getValue(String key) {
    return null;
  }

  public AwardOption getAwardOption() {
    return this.awardOption;
  }

}
