package com.uxiangtech.activitybox.engine.modules.activity.event.listener;

import com.uxiangtech.activitybox.engine.modules.activity.event.ActivityOnlineEvent;
import com.uxiangtech.activitybox.engine.modules.activity.registry.ActivityRegistry;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ActivityOnlineEventListener implements ApplicationListener<ActivityOnlineEvent> {
  @Override
  public void onApplicationEvent(ActivityOnlineEvent event) {
    Activity activity = event.getActivity();
    ActivityRegistry.getInstance().register(activity);
  }
}
