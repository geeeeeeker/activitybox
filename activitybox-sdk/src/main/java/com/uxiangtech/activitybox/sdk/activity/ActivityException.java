package com.uxiangtech.activitybox.sdk.activity;

public class ActivityException extends RuntimeException {
  public ActivityException() {
  }

  public ActivityException(String message) {
    super(message);
  }

  public ActivityException(String message, Throwable cause) {
    super(message, cause);
  }

  public ActivityException(Throwable cause) {
    super(cause);
  }
}
