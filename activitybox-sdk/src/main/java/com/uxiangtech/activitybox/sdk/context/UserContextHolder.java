package com.uxiangtech.activitybox.sdk.context;

public final class UserContextHolder implements ContextHolder<UserContext> {

  private static volatile UserContextHolder INSTANCE;

  private UserContextHolder() {
  }

  public static UserContextHolder getInstance() {
    if (INSTANCE == null) {
      synchronized (UserContextHolder.class) {
        if (INSTANCE == null) {
          INSTANCE = new UserContextHolder();
        }
      }
    }
    return INSTANCE;
  }

  private static final ThreadLocal<UserContext> USER_LOCAL = new ThreadLocal<>();


  @Override
  public UserContext getContext() {
    return USER_LOCAL.get();
  }

  @Override
  public void setContext(UserContext context) {
    USER_LOCAL.set(context);
  }

  @Override
  public void removeContext() {
    USER_LOCAL.remove();
  }
}
