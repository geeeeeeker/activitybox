package com.uxiangtech.activitybox.sdk.context;

public interface ContextHolder<C> {

  C getContext();

  void setContext(final C context);

  void removeContext();
}
