package com.uxiangtech.activitybox.engine.modules.actions;

import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.sdk.context.UserRequestContext;

/**
 * 动作接口，如邀请玩法包含生成邀请码，接受邀请
 */
public interface Action {

  // id, name, desc 来自于注解 @ActAction

  /**
   * 动作ID
   * @return
   */
  String getId();

  /**
   * 动作名称
   * @return
   */
  String getName();

  /**
   * 归属的玩法
   * @return
   */
  Playway getPlayway();

//  /**
//   * 映射的方法
//   * @return
//   */
//  Method getExecutableMethod();

  /**
   * 执行动作
   * @param context 用户请求上下文
   * @return
   */
  Object execute(UserRequestContext context);

}
