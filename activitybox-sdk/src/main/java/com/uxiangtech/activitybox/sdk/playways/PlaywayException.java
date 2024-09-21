package com.uxiangtech.activitybox.sdk.playways;

import com.uxiangtech.activitybox.sdk.activity.ActivityException;

public class PlaywayException extends ActivityException {
  public PlaywayException() {
  }

  public PlaywayException(String message) {
    super(message);
  }

  public PlaywayException(String message, Throwable cause) {
    super(message, cause);
  }

  public PlaywayException(Throwable cause) {
    super(cause);
  }
}
