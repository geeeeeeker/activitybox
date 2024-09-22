package com.uxiangtech.activitybox.sdk.context;


import javax.servlet.http.HttpServletRequest;

/**
 * 用户请求上下文，请求的都是某个活动
 */
public interface ActionCallContext extends ActivityContext<ActionCallContext> {

  /**
   * 获取Servlet请求对象
   * @return
   */
  HttpServletRequest getRequest();

  String getRequestParam(final String param);

  /**
   * 获取用户唯一性标识
   * @return
   */
  Long getUserId();

  /**
   * 外部合作方用户唯一标识
   * @return
   */
  String getOutUserId();



  String getPlaywayId();

  String getActionId();





}
