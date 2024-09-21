package com.uxiangtech.activitybox.engine.modules.activity.event;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.activity.registry.ActivityRegistry;
import org.springframework.context.ApplicationListener;

public class ActivityOnlineEventListener implements ApplicationListener<ActivityOnlineEvent> {
  @Override
  public void onApplicationEvent(ActivityOnlineEvent event) {
    Activity activity = event.getActivity();
    ActivityRegistry.getInstance().register(activity);
  }
}