package com.uxiangtech.activitybox.engine.modules.award.limiter;

import com.uxiangtech.activitybox.common.pipeline.PipeNodeException;

public class AwardDrawingLimitedException extends PipeNodeException {
  public AwardDrawingLimitedException(String message) {
    super(message);
  }
}
