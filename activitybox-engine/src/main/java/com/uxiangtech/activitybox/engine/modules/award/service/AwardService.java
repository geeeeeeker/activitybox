package com.uxiangtech.activitybox.engine.modules.award.service;

public interface AwardService {

  /**
   * 发放奖项
   * @return
   */
  boolean grantAwardOption(Long activityId, String playwayId, String actionId, Long userId, String awardPoolId, Long awardId, String awardOptionId, Long totalStock, Long quantity);

  /**
   * 统计未使用的奖项库存
   * @param activityId
   * @param awardPoolId
   * @param awardOptionId
   * @param totalStock
   * @return
   */
  Long countUnusedAwardOptionStock(Long activityId, String awardPoolId, String awardOptionId, Long totalStock);
}
