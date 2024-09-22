package com.uxiangtech.activitybox.sdk.award;

public class AwardPoolBadTypeException extends AwardDrawingException {
  public AwardPoolBadTypeException() {
    super("AWARD_POOL.BAD_TYPE", "奖池类型错误");
  }
}
