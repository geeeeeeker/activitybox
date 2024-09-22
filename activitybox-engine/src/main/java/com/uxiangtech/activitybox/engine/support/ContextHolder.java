package com.uxiangtech.activitybox.engine.support;

public interface ContextHolder<C> {

  C getContext();

  void setContext(final C context);

  void removeContext();
}
