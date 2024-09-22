package com.uxiangtech.activitybox.sdk.playway;

import com.uxiangtech.activitybox.sdk.activity.ActivityException;

public class PlaywayException extends ActivityException {
  public PlaywayException(String errcode, String message) {
    super(errcode, message);
  }
}
