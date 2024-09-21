package com.uxiangtech.activitybox.engine.modules.page;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;

public abstract class AbstractPage implements Page {
  protected final String id;
  protected final String name;
  protected final String code;
  protected final Activity activity;

  public AbstractPage(String id, String name, String code, final Activity activity) {
    this.id = id;
    this.name = name;
    this.code = code;
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
  public String getCode() {
    return code;
  }

  @Override
  public Activity getActivity() {
    return activity;
  }
}
