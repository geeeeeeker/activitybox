package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool;

import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;

/**
 * 概率抽奖规则
 */
public class RandomAwardPool extends AbstractAwardPool {


  public RandomAwardPool(AwardPoolAttribute attribute, Activity activity) {
    super(attribute, activity);
  }

  @Override
  public AwardDrawingResult draw(AwardDrawingContext context) {
    return null;
  }
}
