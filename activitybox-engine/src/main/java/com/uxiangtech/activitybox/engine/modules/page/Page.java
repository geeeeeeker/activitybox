package com.uxiangtech.activitybox.engine.modules.page;

/**
 * 前端页面
 */
public interface Page {
  Page NULL = new NullPage();

  String getId();
  String getName();
  String getCode();
}
