package com.uxiangtech.activitybox.sdk.activity;

public class ActivityNotFoundException extends ActivityException {
  public ActivityNotFoundException() {
    super("活动不存在");
  }
}
