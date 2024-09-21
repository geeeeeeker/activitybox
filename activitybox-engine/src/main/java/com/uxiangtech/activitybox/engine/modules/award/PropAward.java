package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.AwardAttribute;

/**
 * 道具奖品
 *
 * 道具奖品是虚拟奖品，用于游戏道具等计数场景，没有奖品库存限制。
 */
public class PropAward extends AbstractPropAward {

  public PropAward(String id, String name, String icon, Activity activity) {
    super(id, name, icon, activity);
  }

  public PropAward(AwardAttribute attribute, Activity activity) {
    this(attribute.getId(), attribute.getName(), attribute.getIcon(), activity);
  }

  @Override
  public String getLink() {
    return null;
  }

  @Override
  public Long getStock() {
    return null;
  }
}
