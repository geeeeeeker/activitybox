package com.uxiangtech.activitybox.sdk.award;

public class AwardOptionOutOfStockException extends AwardDrawingException {
  public AwardOptionOutOfStockException() {
    super("AWARD_OPTION.OUT_OF_STOCK", "奖项库存不足");
  }
}
