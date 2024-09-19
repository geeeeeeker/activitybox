package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;

public abstract class AbstractPropAward implements Award {

  private final String id;

  private final String name;

  private final String iconUrl;

  private Activity activity;

  public AbstractPropAward(String id, String name, String iconUrl, Activity activity) {
    this.id = id;
    this.name = name;
    this.iconUrl = iconUrl;
    this.activity = activity;
  }
}
