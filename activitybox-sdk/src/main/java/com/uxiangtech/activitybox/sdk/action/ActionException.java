package com.uxiangtech.activitybox.sdk.action;

import com.uxiangtech.activitybox.sdk.activity.ActivityException;

public class ActionException extends ActivityException {
  public ActionException(String errcode, String message) {
    super(errcode, message);
  }
}
