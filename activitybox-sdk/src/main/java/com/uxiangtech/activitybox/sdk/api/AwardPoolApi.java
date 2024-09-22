package com.uxiangtech.activitybox.sdk.api;

import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.context.ActionCallContextCapable;

/**
 * 抽奖API
 */
public interface AwardPoolApi extends ActionCallContextCapable {

  // TODO 是否要支持直接发奖？


  // 奖池抽奖

  /**
   * 随机抽奖池，给上下文用户随机分配奖项
   *
   * @param awardPoolId 奖池ID
   * @return 抽奖结果
   */
  AwardDrawingResult randomDraw(final String awardPoolId);

  /**
   * 随机抽奖池，给指定用户随机分配奖项
   * @param userId 指定用户ID
   * @param awardPoolId 奖池ID
   * @return 抽奖结果
   */
  AwardDrawingResult randomDraw(final Long userId, final String awardPoolId);


  // TODO 支持异步抽奖，是否有这个必要？

}
