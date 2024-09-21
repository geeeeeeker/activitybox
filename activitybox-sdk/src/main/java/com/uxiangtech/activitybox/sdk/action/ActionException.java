package com.uxiangtech.activitybox.sdk.action;

import com.uxiangtech.activitybox.sdk.activity.ActivityException;

public class ActionException extends ActivityException {
  public ActionException() {
  }

  public ActionException(String message) {
    super(message);
  }

  public ActionException(String message, Throwable cause) {
    super(message, cause);
  }

  public ActionException(Throwable cause) {
    super(cause);
  }
}
