package com.uxiangtech.activitybox.common.pipeline;

import java.util.HashMap;
import java.util.Map;

public class DefaultPipelineContext implements PipelineContext<DefaultPipelineContext> {

  private final Map<String, Object> m = new HashMap<>();

  @Override
  public void setValue(String key, Object value) {
    this.m.put(key, value);
  }

  @Override
  public Object getValue(String key) {
    return this.m.get(key);
  }
}
