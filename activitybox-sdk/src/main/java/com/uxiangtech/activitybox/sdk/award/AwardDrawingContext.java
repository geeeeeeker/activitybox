package com.uxiangtech.activitybox.sdk.award;

/**
 * 发奖上下文，涉及定时出奖及手动抽奖
 */
public interface AwardDrawingContext {

  /**
   * 获取抽奖用户ID
   * @return
   */
  Long getUserId();

  /**
   * 获取奖池ID
   * @return
   */
  String getAwardPoolId();

  /**
   * 获取奖项ID
   * @return
   */
  String getAwardOptionId();

}
