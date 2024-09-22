package com.uxiangtech.activitybox.engine.modules.sdkimpl.context;

import com.uxiangtech.activitybox.sdk.context.UserContext;

/**
 * 用户上下文，用户承载Cookie解析结果
 */
public class UserContextImpl implements UserContext {

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
    return null;
  }

  @Override
  public Object getData(String key) {
    return null;
  }
}
