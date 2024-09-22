package com.uxiangtech.activitybox.sdk.context;

public interface ActivityContext<C extends ActivityContext<?>> extends Context<C> {

  /**
   * 获取租户ID
   *
   * @return
   */
  String getTenantId();

  /**
   * 获取活动ID
   *
   * @return
   */
  Long getActivityId();
}
