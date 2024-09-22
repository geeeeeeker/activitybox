package com.uxiangtech.activitybox.engine.modules.variable;

import java.util.HashMap;
import java.util.Map;

public class VariablesImpl implements Variables {

  private final Map<String, Object> m = new HashMap<>();

  public VariablesImpl(Map<String, Object> data) {
    this.m.putAll(data);
  }

  public VariablesImpl() {
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
