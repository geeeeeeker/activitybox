package com.uxiangtech.activitybox.admin.modules.activity;

import com.uxiangtech.activitybox.admin.support.SpringBeanHolder;
import com.uxiangtech.activitybox.sdk.activity.Activity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 活动管理器
 */
public final class ActivityMgr {

  private static final ActivityMgr SINGLETON = new ActivityMgr();

  private ActivityMgr() {}

  public static ActivityMgr getInstance() {
    return SINGLETON;
  }

  private final Map<Long, Activity> activityCache = new ConcurrentHashMap<>();

  /**
   * 加载活动
   * @param id 活动ID
   * @return
   */
  public Activity getActivity(final Long id) {

    Activity activity = this.activityCache.get(id);

    if (null == activity) {

      activity = this.loadActivity(id);

      this.activityCache.put(id, activity);

    } else {

      // 如果活动信息被修改，重新加载活动

      final LocalDateTime cachedGmtModified =
        activity.getGmtModified();

      final ActivityService activityService =
        SpringBeanHolder.getBean(ActivityService.class);

      final LocalDateTime lastModifiedTime =
        activityService.getModifiedTime(id);

      if (lastModifiedTime.isAfter(cachedGmtModified)) {

        activity = this.loadActivity(id);

      }

    }

    return activity;

  }

  public Activity loadActivity(final Long id) {



  }
}
