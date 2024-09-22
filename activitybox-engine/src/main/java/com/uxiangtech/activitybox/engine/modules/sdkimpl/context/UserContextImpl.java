package com.uxiangtech.activitybox.engine.modules.sdkimpl.context;

import com.uxiangtech.activitybox.sdk.context.UserContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户上下文，用户承载Cookie解析结果
 */
public class UserContextImpl implements UserContext {

  private final Map<String, Object> m = new HashMap<>();

  private final Long tenantId;
  private final Long userId;

  private final String outUserId;

  private final String outUsername;

  public UserContextImpl(Long tenantId, Long userId, String outUserId, String outUsername) {
    this.tenantId = tenantId;
    this.userId = userId;
    this.outUserId = outUserId;
    this.outUsername = outUsername;
  }

  @Override
  public UserContext addData(String key, Object value) {
    m.put(key, value);
    return this;
  }

  @Override
  public Object getData(String key) {
    return m.get(key);
  }

  @Override
  public Long getTenantId() {
    return this.tenantId;
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
  public String getOutUsername() {
    return this.outUsername;
  }
}
