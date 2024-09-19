package com.uxiangtech.activitybox.engine.modules.activity;

import com.uxiangtech.activitybox.data.activity.ActivityDAO;
import com.uxiangtech.activitybox.data.activity.ActivityPO;
import com.uxiangtech.activitybox.engine.modules.actions.Action;
import com.uxiangtech.activitybox.sdk.context.UserRequestContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActivityService {

  @Resource
  private ActivityDAO activityDAO;

  public Object doAction(Long activityId, String playwayId, String actionId) {

    ActivityPO activityPO = activityDAO.getActivityById(activityId);

    //构建活动上下文

    final UserRequestContext activityContext = new UserRequestContextImpl();

    final Activity activity = ActivityFactory.newActivity(activityPO);

    final Action action =
      activity
        .getPlaywayOrThrow(
          playwayId, () -> new RuntimeException())
        .getActionOrThrow(
          actionId, () -> new RuntimeException());

    return action.execute(activityContext);
  }

  public String getSkin(Long activityId, String skinId) {
    return null;
  }


}
