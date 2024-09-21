package com.uxiangtech.activitybox.sdk.playways;

public class PlaywayNotFoundException extends PlaywayException {
  public PlaywayNotFoundException() {
    super("玩法不存在");
  }
}
