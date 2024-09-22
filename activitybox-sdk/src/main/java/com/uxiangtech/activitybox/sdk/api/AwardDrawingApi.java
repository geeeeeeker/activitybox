package com.uxiangtech.activitybox.sdk.api;

import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.context.ActionCallContextCapable;

/**
 * 抽奖API
 */
public interface AwardDrawingApi extends ActionCallContextCapable {

  // 非奖池直接发放奖项

  /**
   * 直接抽奖
   */
  AwardDrawingResult directDrawAwardPool();

  // 奖池抽奖

  /**
   * 随机抽奖池，给上下文用户随机分配奖项
   *
   * @param awardPoolId 奖池ID
   * @return 抽奖结果
   */
  AwardDrawingResult randomDrawAwardPool(final String awardPoolId);

  /**
   * 随机抽奖池，给指定用户随机分配奖项
   * @param userId 指定用户ID
   * @param awardPoolId 奖池ID
   * @return 抽奖结果
   */
  AwardDrawingResult randomDrawAwardPool(final Long userId, final String awardPoolId);

}
