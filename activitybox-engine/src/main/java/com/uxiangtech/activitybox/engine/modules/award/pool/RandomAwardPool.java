package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;

import java.util.Map;

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
