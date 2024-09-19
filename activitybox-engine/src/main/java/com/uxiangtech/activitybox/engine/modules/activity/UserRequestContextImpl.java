package com.uxiangtech.activitybox.engine.modules.activity;

import com.uxiangtech.activitybox.sdk.context.UserRequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户请求上下文，请求的都是某个活动
 */
public class UserRequestContextImpl implements UserRequestContext {

  @Override
  public HttpServletRequest getRequest() {
    HttpServletRequest request = null;
    return null;
  }

  @Override
  public String getRequestParam(String param) {
    return this.getRequest().getParameter(param);
  }

  @Override
  public String getUserId() {
    return null;
  }
}
