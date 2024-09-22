package com.uxiangtech.activitybox.engine.modules.sdkimpl.api;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.sdk.api.Api;
import com.uxiangtech.activitybox.sdk.api.AwardPoolApi;
import com.uxiangtech.activitybox.sdk.api.PropApi;

/**
 *
 */
public class ApiImpl implements Api {

  private final AwardPoolApi awardPoolApi;
  private final PropApi propApi;

  public ApiImpl() {
    this.awardPoolApi = SpringBeanHolder.getBean(AwardPoolApi.class);
    this.propApi = SpringBeanHolder.getBean(PropApi.class);
  }

  @Override
  public AwardPoolApi awardPoolApi() {
    return this.awardPoolApi;
  }

  @Override
  public PropApi propApi() {
    return this.propApi;
  }
}
