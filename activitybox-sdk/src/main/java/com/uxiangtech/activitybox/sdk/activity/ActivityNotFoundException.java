package com.uxiangtech.activitybox.sdk.activity;

public class ActivityNotFoundException extends ActivityException {
  public ActivityNotFoundException() {
    super("ACTIVITY.NOT_FOUND", "活动不存在");
  }
}
