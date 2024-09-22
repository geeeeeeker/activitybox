package com.uxiangtech.activitybox.sdk.award;

import com.uxiangtech.activitybox.sdk.activity.ActivityException;

public class AwardDrawingException extends ActivityException {
  public AwardDrawingException(String errcode, String message) {
    super(errcode, message);
  }
}
