package com.uxiangtech.activitybox.engine.modules.page;

/**
 * 不存在的页面
 */
public class NullPage extends AbstractPage {
  private static final String HTML_CODE = "";

  public NullPage() {
    super("null", "页面不存在", HTML_CODE);
  }
}
