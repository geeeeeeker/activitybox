package com.uxiangtech.activitybox.common.pipeline;

/**
 * 责任链上下文信息，责任链执行过程中需要的获取的资源及中间临时保存的信息
 */
public interface PipelineContext<PC extends PipelineContext<?>> {
  void setValue(final String key, final Object value);
  default PC fluentAddValue(final String key, final Object value) {
    setValue(key, value);
    return (PC) this;
  }
  Object getValue(final String key);
}
