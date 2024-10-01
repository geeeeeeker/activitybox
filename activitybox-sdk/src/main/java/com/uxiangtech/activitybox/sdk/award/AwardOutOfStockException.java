package com.uxiangtech.activitybox.sdk.award;

public class AwardOutOfStockException extends AwardDrawingException {
  public AwardOutOfStockException() {
    super("AWARD.OUT_OF_STOCK", "奖品库存不足");
  }
}
