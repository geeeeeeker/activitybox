package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;

/**
 * 道具奖品
 *
 * 道具奖品是虚拟奖品，用于游戏道具等计数场景，没有奖品库存限制。
 */
public class PropAward implements Award {


  @Override
  public String getId() {
    return "prop";
  }

  @Override
  public String getName() {
    return "道具奖品";
  }

  @Override
  public String getIcon() {
    return null;
  }

  @Override
  public String getLink() {
    return null;
  }

  @Override
  public Activity getActivity() {
    return null;
  }

  @Override
  public Long getStock() {
    return null;
  }

  @Override
  public Object execute() {
    return null;
  }
}
