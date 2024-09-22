package com.uxiangtech.activitybox.engine.modules.award.option;

import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.pool.AwardPool;
import com.uxiangtech.activitybox.sdk.attribute.AwardOptionAttribute;

import java.math.BigDecimal;

/**
 * 奖项实现
 */
public class AwardOptionImpl extends AbstractAwardOption {

  public AwardOptionImpl(String id, String name, BigDecimal probability, Long stock, Award award, AwardPool awardPool) {
    super(id, name, probability, stock, award, awardPool);
  }

  public AwardOptionImpl(AwardOptionAttribute attribute, Award award, AwardPool awardPool) {
    super(attribute, award, awardPool);
  }
}
