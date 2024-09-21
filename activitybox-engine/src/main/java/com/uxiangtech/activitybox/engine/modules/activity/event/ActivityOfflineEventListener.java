package com.uxiangtech.activitybox.engine.modules.activity.event;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.activity.registry.ActivityRegistry;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ActivityOfflineEventListener implements ApplicationListener<ActivityOfflineEvent> {
  @Override
  public void onApplicationEvent(ActivityOfflineEvent event) {
    Activity activity = event.getActivity();
    ActivityRegistry.getInstance().deregister(activity);

    // TODO 发送提醒消息
  }
}
