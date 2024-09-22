package com.uxiangtech.activitybox.sdk.context;

import com.uxiangtech.activitybox.sdk.activity.Activity;

public interface ActivityContext<C extends ActivityContext<?>> extends Context<C> {

  /**
   * 获取租户ID
   *
   * @return 租户ID
   */
  String getTenantId();

  /**
   * 获取活动ID
   *
   * @return 活动ID
   */
  Long getActivityId();

  /**
   * 获取活动定义
   *
   * @return 活动定义
   */
  Activity getActivity();
}
