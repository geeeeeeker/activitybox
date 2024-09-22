package com.uxiangtech.activitybox.sdk.action;

import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;

import java.lang.reflect.Method;

/**
 * 动作接口，如邀请玩法包含生成邀请码，接受邀请
 */
public interface Action {

  // id, name, 来自于注解 @ActAction

  /**
   * 获取动作ID
   * @return
   */
  String getId();

  /**
   * 获取动作名称
   * @return
   */
  String getName();

  /**
   * 获取归属玩法
   * @return
   */
  Playway getPlayway();

  /**
   * 获取动作对应的Method
   *
   * 方法上的注解信息等都要依赖于Method对象才能获取，因此必须包含
   * @return
   */
  Method getExecutableMethod() throws NoSuchMethodException;

  /**
   * 执行动作
   * @param context 用户请求上下文
   * @return
   */
  Object execute(ActionCallContext context);

}
