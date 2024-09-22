package com.uxiangtech.activitybox.sdk.award;

public class AwardPoolNotFoundException extends AwardDrawingException {
  public AwardPoolNotFoundException() {
    super("AWARD_POOL.NOT_FOUND", "奖池类型配置错误");
  }
}
