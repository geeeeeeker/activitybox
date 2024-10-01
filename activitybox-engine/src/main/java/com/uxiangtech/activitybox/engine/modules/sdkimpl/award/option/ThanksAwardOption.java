package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option;

import com.uxiangtech.activitybox.sdk.award.AwardPool;
import com.uxiangtech.activitybox.sdk.attribute.AwardOptionAttribute;

import java.math.BigDecimal;

/**
 * 安慰奖（谢谢参与）
 */
public class ThanksAwardOption extends AbstractAwardOption {

  public ThanksAwardOption(String id, String name, BigDecimal probability, AwardPool awardPool) {
    super(id, name, probability, 1L, Long.MAX_VALUE, null, awardPool);
  }

  public ThanksAwardOption(AwardOptionAttribute attribute, AwardPool awardPool) {
    super(attribute, null, awardPool);
  }
}
