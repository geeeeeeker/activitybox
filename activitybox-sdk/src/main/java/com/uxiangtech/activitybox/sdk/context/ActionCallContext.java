package com.uxiangtech.activitybox.sdk.context;


import com.uxiangtech.activitybox.sdk.action.Action;
import com.uxiangtech.activitybox.sdk.playway.Playway;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户请求上下文，请求的都是某个活动
 */
public interface ActionCallContext extends ActivityContext<ActionCallContext> {

  /**
   * 获取Servlet请求对象
   *
   * @return
   */
  HttpServletRequest getRequest();

  /**
   * 获取请求参数值
   *
   * @param paramName 参数名
   * @return
   */
  String getRequestParam(final String paramName);

  /**
   * 获取用户唯一性标识
   *
   * @return
   */
  Long getUserId();

  /**
   * 获取外部合作方用户唯一标识
   *
   * @return
   */
  String getOutUserId();

  /**
   * 获取外部合作方用户名
   *
   * @return
   */
  String getOutUsername();

  /**
   * 获取玩法ID
   *
   * @return
   */
  String getPlaywayId();

  /**
   * 获取玩法定义
   *
   * @return
   */
  Playway getPlayway();

  /**
   * 获取动作ID
   *
   * @return
   */
  String getActionId();

  /**
   * 获取动作定义
   *
   * @return
   */
  Action getAction();


}
