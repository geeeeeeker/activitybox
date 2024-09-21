package com.uxiangtech.activitybox.engine.modules.page;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;

/**
 * 前端页面
 */
public interface Page {
  Page INVALID = new InvalidPage();

  /**
   * 获取页面标识
   * @return
   */
  String getId();

  /**
   * 获取页面名称
   * @return
   */
  String getName();

  /**
   * 获取页面代码
   * @return
   */
  String getCode();

  /**
   * 获取活动
   * @return
   */
  Activity getActivity();


  class InvalidPage implements Page {
    private static final String HTML_CODE = "";

    @Override
    public String getId() {
      return "invalid";
    }

    @Override
    public String getName() {
      return "无效页面";
    }

    @Override
    public String getCode() {
      return null;
    }

    @Override
    public Activity getActivity() {
      return null;
    }
  }
}
