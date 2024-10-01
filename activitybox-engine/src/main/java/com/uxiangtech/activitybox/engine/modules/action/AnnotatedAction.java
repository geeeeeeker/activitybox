package com.uxiangtech.activitybox.engine.modules.action;

import com.uxiangtech.activitybox.sdk.action.Action;

import java.lang.reflect.Method;

/**
 * 标记 @ActAction 注解的动作
 */
public interface AnnotatedAction extends Action {

  /**
   * 获取动作对应方法
   *
   * @return
   */
  Method getExecutableMethod();
}
