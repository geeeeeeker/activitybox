package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool.AbstractAwardPool;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;
import com.uxiangtech.activitybox.sdk.award.AwardOption;

/**
 * 直接抽奖规则奖池
 */
public class DirectAwardPool extends AbstractAwardPool {

  public DirectAwardPool(AwardPoolAttribute attribute, Activity activity) {
    super(attribute, activity);
  }

  @Override
  public AwardDrawingResult draw(AwardDrawingContext context) {

    System.out.println("直接抽奖");


    // 直接抽取某个奖品，如果奖品不足，这降级至谢谢参与




    return null;
  }

  @Override
  protected AwardOption chooseAwardOption(AwardDrawingContext context) {
    return null;
  }
}
