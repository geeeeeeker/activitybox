package com.uxiangtech.activitybox.engine.modules.sdkimpl.api;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.sdk.api.Api;
import com.uxiangtech.activitybox.sdk.api.AwardDrawingApi;
import com.uxiangtech.activitybox.sdk.api.PropApi;

public class ApiImpl implements Api {

  private final AwardDrawingApi awardDrawingApi;
  private final PropApi propApi;

  public ApiImpl() {
    this.awardDrawingApi = SpringBeanHolder.getBean(AwardDrawingApi.class);
    this.propApi = SpringBeanHolder.getBean(PropApi.class);
  }

  @Override
  public AwardDrawingApi awardDrawingApi() {
    return this.awardDrawingApi;
  }

  @Override
  public PropApi awardPropApi() {
    return this.propApi;
  }
}
