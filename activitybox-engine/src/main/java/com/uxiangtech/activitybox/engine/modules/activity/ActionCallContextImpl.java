package com.uxiangtech.activitybox.engine.modules.activity;

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
  private final Long activityId;
  private final String playwayId;
  private final String actionId;

  private Long userId;
  private String outUserId;

  private final HttpServletRequest request;


  public ActionCallContextImpl(Long activityId, String playwayId, String actionId, HttpServletRequest request) {
    this.activityId = activityId;
    this.playwayId = playwayId;
    this.actionId = actionId;
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
    return this.playwayId;
  }

  @Override
  public String getActionId() {
    return this.actionId;
  }

  @Override
  public String getTenantId() {
    return this.tenantId;
  }

  @Override
  public Long getActivityId() {
    return this.activityId;
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
