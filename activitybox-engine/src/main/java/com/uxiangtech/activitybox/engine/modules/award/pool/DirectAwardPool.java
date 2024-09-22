package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.award.pool.drawing.AwardDrawingContext;
import com.uxiangtech.activitybox.engine.modules.award.pool.drawing.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;

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
}
