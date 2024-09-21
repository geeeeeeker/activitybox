package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
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
    return null;
  }
}
