package com.uxiangtech.activitybox.sdk.playway;

public class PlaywayNotFoundException extends PlaywayException {
  public PlaywayNotFoundException() {
    super("PLAYWAY.NOT_FOUND", "玩法不存在");
  }
}
