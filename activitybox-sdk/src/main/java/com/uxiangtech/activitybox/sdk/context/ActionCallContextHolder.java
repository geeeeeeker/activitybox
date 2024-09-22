package com.uxiangtech.activitybox.sdk.context;

public class ActionCallContextHolder implements ContextHolder<ActionCallContext> {

  private static volatile ActionCallContextHolder INSTANCE;

  private ActionCallContextHolder() {
  }

  public static ActionCallContextHolder getInstance() {
    if (INSTANCE == null) {
      synchronized (ActionCallContextHolder.class) {
        if (INSTANCE == null) {
          INSTANCE = new ActionCallContextHolder();
        }
      }
    }
    return INSTANCE;
  }

  private static final ThreadLocal<ActionCallContext> CALL_LOCAL = new ThreadLocal<>();

  @Override
  public ActionCallContext getContext() {
    return CALL_LOCAL.get();
  }

  @Override
  public void setContext(final ActionCallContext context) {
    CALL_LOCAL.set(context);
  }

  @Override
  public void removeContext() {
    CALL_LOCAL.remove();
  }
}


