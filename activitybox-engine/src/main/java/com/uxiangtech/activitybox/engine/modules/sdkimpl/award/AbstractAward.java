package com.uxiangtech.activitybox.engine.modules.sdkimpl.award;

import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.Award;

public abstract class AbstractAward implements Award {

  private final String id;

  private final String name;
  private final String type;

  private final String icon;
  private final Long stock;

  private final String refId;

  private final Activity activity;

  public AbstractAward(String id, String name, String type, String icon, Long stock, String refId, Activity activity) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.icon = icon;
    this.stock = stock;
    this.refId = refId;
    this.activity = activity;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getIcon() {
    return this.icon;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public Long getStock() {
    return this.stock;
  }

  @Override
  public String getRefId() {
    return this.refId;
  }


  @Override
  public Activity getActivity() {
    return this.activity;
  }


}
