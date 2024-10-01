package com.uxiangtech.activitybox.engine.modules.award.limiter;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.modules.award.service.AwardService;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.AwardOption;
import com.uxiangtech.activitybox.sdk.award.AwardOutOfStockException;
import com.uxiangtech.activitybox.sdk.award.AwardPool;

/**
 * 奖项库存限制
 */
public class AwardDrawingAwardOptionStockLimiter implements AwardDrawingLimiter {
  
  private final AwardService awardService;

  public AwardDrawingAwardOptionStockLimiter() {
    this.awardService = SpringBeanHolder.getBean(AwardService.class);
  }

  @Override
  public void doPipe(AwardDrawingLimiterContext context, AwardDrawingLimiterPipeline pipeline) throws AwardDrawingLimitedException {

    // 抽中奖项
    final AwardOption awardOption = context.getAwardOption();

    final AwardPool awardPool = awardOption.getAwardPool();

    final Activity activity = awardPool.getActivity();

    // 本奖项发放数量
    final Long grantQuantity = awardOption.getGrantQuantity();

    // 奖项库存总数
    final Long totalStock = awardOption.getTotalStock();

    // 查询剩余奖项数量
    final Long unusedAwardOptionStock =
      this.awardService.countUnusedAwardOptionStock(
        activity.getId(), awardPool.getId(), awardOption.getId(), totalStock);

    if (unusedAwardOptionStock < grantQuantity) {
      throw new AwardOutOfStockException();
    }

    pipeline.doPipe(context);

  }
}
