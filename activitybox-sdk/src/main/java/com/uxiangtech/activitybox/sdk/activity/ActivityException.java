package com.uxiangtech.activitybox.sdk.activity;

public class ActivityException extends RuntimeException {

  private final String errcode;

  public ActivityException(final String errcode, String message) {
    super(message);
    this.errcode = errcode;
  }

  public String getErrcode() {
    return errcode;
  }
}
