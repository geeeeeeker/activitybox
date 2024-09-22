package com.uxiangtech.activitybox.sdk.action;

public class ActionNotFoundException extends ActionException {
  public ActionNotFoundException() {
    super("ACTION.NOT_FOUND", "动作不存在");
  }
}
