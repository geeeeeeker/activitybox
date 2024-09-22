package com.uxiangtech.activitybox.engine.modules.sdkimpl.variable;

import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.variable.Variables;

import java.util.HashMap;
import java.util.Map;

public class VariablesImpl implements Variables {

  private final Map<String, Object> m = new HashMap<>();

  private final Activity activity;

  public VariablesImpl(Map<String, Object> data, Activity activity) {
    this.m.putAll(data);
    this.activity = activity;
  }

  @Override
  public Activity getActivity() {
    return this.activity;
  }

  @Override
  public void putValue(String key, Object value) {
    this.putValue(key, value);
  }

  @Override
  public Variables fluentPutValue(String key, Object value) {
    this.putValue(key, value);
    return this;
  }

  @Override
  public Object getValue(String key) {
    return this.m.get(key);
  }
}
