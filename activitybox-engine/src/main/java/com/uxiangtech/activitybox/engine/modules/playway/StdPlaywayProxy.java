package com.uxiangtech.activitybox.engine.modules.playway;

import com.uxiangtech.activitybox.sdk.playways.StdPlayway;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于处理基于注解的玩法
 */
public class StdPlaywayProxy implements StdPlayway, InvocationHandler {
  private final String id;
  private final String name;
  private final Object playwayInstance;

  public StdPlaywayProxy(final String id, final String name, final Object playwayInstance) {
    this.id = id;
    this.name = name;
    this.playwayInstance = playwayInstance;
    Proxy.newProxyInstance(playwayInstance.getClass().getClassLoader(), new Class<?>[]{StdPlayway.class}, this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return null;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

}
