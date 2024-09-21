package com.uxiangtech.activitybox.sdk.action;

public class ActionNotFoundException extends ActionException {
  public ActionNotFoundException() {
    super("接口不存在");
  }
}
