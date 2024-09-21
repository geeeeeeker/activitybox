package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;

public abstract class AbstractPropAward implements Award {

  private final String id;

  private final String name;

  private final String icon;

  private final Activity activity;

  public AbstractPropAward(String id, String name, String icon, Activity activity) {
    this.id = id;
    this.name = name;
    this.icon = icon;
    this.activity = activity;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getIcon() {
    return icon;
  }

  @Override
  public Activity getActivity() {
    return activity;
  }
}
