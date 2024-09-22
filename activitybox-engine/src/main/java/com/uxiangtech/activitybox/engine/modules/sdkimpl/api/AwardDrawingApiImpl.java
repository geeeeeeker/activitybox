package com.uxiangtech.activitybox.engine.modules.sdkimpl.api;

import com.uxiangtech.activitybox.sdk.api.AwardDrawingApi;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import org.springframework.stereotype.Component;

@Component
public class AwardDrawingApiImpl extends ApiImpl implements AwardDrawingApi {

  @Override
  public AwardDrawingResult directDrawAwardPool() {
    return null;
  }

  @Override
  public AwardDrawingResult randomDrawAwardPool(String awardPoolId) {
    return null;
  }

  @Override
  public AwardDrawingResult randomDrawAwardPool(Long userId, String awardPoolId) {
    return null;
  }
}
