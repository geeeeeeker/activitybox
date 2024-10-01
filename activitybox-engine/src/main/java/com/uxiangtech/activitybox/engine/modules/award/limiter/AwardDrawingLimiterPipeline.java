package com.uxiangtech.activitybox.engine.modules.award.limiter;

import com.uxiangtech.activitybox.common.pipeline.AbstractPipeline;

import java.util.List;

public class AwardDrawingLimiterPipeline extends AbstractPipeline<AwardDrawingLimiterContext, AwardDrawingLimiter, AwardDrawingLimitedException> {
  public AwardDrawingLimiterPipeline(List<AwardDrawingLimiter> nodes) {
    super(nodes);
  }
}
