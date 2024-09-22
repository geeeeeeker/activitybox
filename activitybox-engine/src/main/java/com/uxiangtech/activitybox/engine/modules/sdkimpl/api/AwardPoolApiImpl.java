package com.uxiangtech.activitybox.engine.modules.sdkimpl.api;

import com.uxiangtech.activitybox.engine.modules.award.AwardService;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool.RandomAwardPool;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool.drawing.AwardDrawingContextImpl;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.api.AwardPoolApi;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.award.AwardPool;
import com.uxiangtech.activitybox.sdk.award.AwardPoolBadTypeException;
import com.uxiangtech.activitybox.sdk.award.AwardPoolNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AwardPoolApiImpl implements AwardPoolApi {
  private static final Logger LOGGER =
    LoggerFactory.getLogger(AwardPoolApiImpl.class);

  @Override
  public AwardDrawingResult randomDraw(String awardPoolId) {
    return this.randomDraw(getContext().getUserId(), awardPoolId);
  }

  @Override
  public AwardDrawingResult randomDraw(Long userId, String awardPoolId) {

    final Activity activity = getContext().getActivity();

    final AwardPool awardPool =
      activity.getAwardPoolMap().get(awardPoolId);

    // 校验奖池是否存在
    if (null == awardPool) {
      LOGGER.error("奖池配置错误，奖池[{}]不存在 >>> 活动ID={}",
        awardPoolId, activity.getId());
      // TODO 抽奖失败？还是直接出谢谢参与
      throw new AwardPoolNotFoundException();
    }

    // 校验奖池是否是随机
    if (!(awardPool instanceof RandomAwardPool)) {
      LOGGER.error("奖池配置错误，奖池[{}]不是随机奖池 >>> 活动ID={}",
        awardPoolId, activity.getId());
      throw new AwardPoolBadTypeException();
    }

    // 构建抽奖上下文
    final AwardDrawingContext context = new AwardDrawingContextImpl(getContext().getActivityId(),
      getContext().getPlaywayId(), getContext().getActionId(), userId, awardPoolId);

    return awardPool.draw(context);
  }
}
