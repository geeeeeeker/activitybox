package com.uxiangtech.activitybox.sdk.context;


import javax.servlet.http.HttpServletRequest;

/**
 * 用户请求上下文，请求的都是某个活动
 */
public interface UserRequestContext extends ActivityContext {

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
  String getUserId();



}
