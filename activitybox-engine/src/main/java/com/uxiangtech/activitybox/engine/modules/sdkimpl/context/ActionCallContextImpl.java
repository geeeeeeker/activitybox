package com.uxiangtech.activitybox.engine.modules.sdkimpl.context;

import com.uxiangtech.activitybox.sdk.action.Action;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.context.UserContext;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户请求上下文，请求的都是某个活动
 */
public class ActionCallContextImpl implements ActionCallContext {

  private final Map<String, Object> m = new HashMap<>();

  private final Activity activity;
  private final Playway playway;
  private final Action action;

  private final UserContext userContext;

  private final HttpServletRequest request;


  public ActionCallContextImpl(Activity activity, Playway<?> playway, Action action, UserContext userContext, HttpServletRequest request) {
    this.activity = activity;
    this.playway = playway;
    this.action = action;
    this.userContext = userContext;
    this.request = request;
  }

  @Override
  public HttpServletRequest getRequest() {
    return this.request;
  }

  @Override
  public String getRequestParam(String param) {
    return this.getRequest().getParameter(param);
  }

  @Override
  public Long getUserId() {
    return this.userContext.getUserId();
  }

  @Override
  public String getOutUserId() {
    return this.userContext.getOutUserId();
  }

  @Override
  public String getOutUsername() {
    return this.userContext.getOutUsername();
  }

  @Override
  public String getPlaywayId() {
    return this.getPlayway().getId();
  }

  @Override
  public Playway getPlayway() {
    return this.playway;
  }

  @Override
  public String getActionId() {
    return this.getAction().getId();
  }

  @Override
  public Action getAction() {
    return this.action;
  }

  @Override
  public Long getTenantId() {
    return this.userContext.getTenantId();
  }

  @Override
  public Long getActivityId() {
    return this.activity.getId();
  }

  @Override
  public Activity getActivity() {
    return this.activity;
  }

  @Override
  public ActionCallContext addData(String key, Object value) {
    this.m.put(key, value);
    return this;
  }

  @Override
  public Object getData(String key) {
    return this.m.get(key);
  }
}
