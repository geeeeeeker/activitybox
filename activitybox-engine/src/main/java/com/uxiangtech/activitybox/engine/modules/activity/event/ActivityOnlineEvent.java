package com.uxiangtech.activitybox.engine.modules.activity.event;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import org.springframework.context.ApplicationEvent;

public class ActivityOnlineEvent extends ApplicationEvent {
  private final Activity activity;
  private final Boolean isIncrementalLoading;

  public ActivityOnlineEvent(Activity activity, Boolean isIncrementalLoading) {
    super(activity);
    this.activity = activity;
    this.isIncrementalLoading = isIncrementalLoading;
  }

  public Activity getActivity() {
    return activity;
  }

  public Boolean isIncrementalLoading() {
    return isIncrementalLoading;
  }
}
