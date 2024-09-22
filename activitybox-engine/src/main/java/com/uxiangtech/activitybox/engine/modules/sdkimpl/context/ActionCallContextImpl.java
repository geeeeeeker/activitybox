package com.uxiangtech.activitybox.engine.modules.sdkimpl.context;

import com.uxiangtech.activitybox.sdk.action.Action;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户请求上下文，请求的都是某个活动
 */
public class ActionCallContextImpl implements ActionCallContext {

  private Map<String, Object> properties = new HashMap<>();

  private String tenantId;
  private final Activity activity;
  private final Playway playway;
  private final Action action;

  private Long userId;
  private String outUserId;

  private final HttpServletRequest request;


  public ActionCallContextImpl(Activity activity, Playway<?> playway, Action action, HttpServletRequest request) {
    this.activity = activity;
    this.playway = playway;
    this.action = action;
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
    return this.userId;
  }

  @Override
  public String getOutUserId() {
    return this.outUserId;
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
  public String getTenantId() {
    return this.tenantId;
  }

  @Override
  public Long getActivityId() {
    return this.activity.getId();
  }

  @Override
  public Activity getActivity() {
    return null;
  }

  @Override
  public ActionCallContext addData(String key, Object value) {
    this.properties.put(key, value);
    return this;
  }

  @Override
  public Object getData(String key) {
    return this.properties.get(key);
  }
}
