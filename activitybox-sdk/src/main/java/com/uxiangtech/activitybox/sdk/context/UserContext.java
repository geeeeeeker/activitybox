package com.uxiangtech.activitybox.sdk.context;

public interface UserContext extends Context<UserContext> {

  /**
   * 获取租户ID
   *
   * @return 租户ID
   */
  Long getTenantId();

  /**
   * 获取用户ID
   *
   * @return 用户ID
   */
  Long getUserId();

  /**
   * 获取外部合作方用户ID
   *
   * @return 外部合作方用户ID
   */
  String getOutUserId();

  /**
   * 获取外部合作方用户名
   *
   * @return 外部合作方用户名
   */
  String getOutUsername();


}
