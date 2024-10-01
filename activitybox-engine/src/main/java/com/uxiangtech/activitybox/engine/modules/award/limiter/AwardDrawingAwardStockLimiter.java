package com.uxiangtech.activitybox.engine.modules.award.limiter;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.modules.award.service.AwardService;

/**
 * 奖品库存限制
 */
public class AwardDrawingAwardStockLimiter implements AwardDrawingLimiter {

  private final AwardService awardService;

  public AwardDrawingAwardStockLimiter() {
    this.awardService = SpringBeanHolder.getBean(AwardService.class);
  }

  @Override
  public void doPipe(AwardDrawingLimiterContext context, AwardDrawingLimiterPipeline pipeline) throws AwardDrawingLimitedException {


  }
}
