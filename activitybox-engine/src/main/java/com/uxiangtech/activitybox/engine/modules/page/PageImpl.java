package com.uxiangtech.activitybox.engine.modules.page;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.PageAttribute;

public class PageImpl extends AbstractPage {
  public PageImpl(String id, String name, String code, Activity activity) {
    super(id, name, code, activity);
  }
  public PageImpl(PageAttribute attribute, Activity activity) {
    this(attribute.getId(), attribute.getName(), attribute.getCode(), activity);
  }
}
