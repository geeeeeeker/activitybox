package com.uxiangtech.activitybox.engine.modules.activity.registry;

import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.activity.ActivityNotFoundException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 活动注册中心
 */
public class ActivityRegistry {

  private final ConcurrentMap<Long, Activity> offlineActivityCache = new ConcurrentHashMap<>();

  /**
   * 活动缓存：包括在线活动、已下线的活动
   */
  private final ConcurrentMap<Long, Activity> onlineActivityCache = new ConcurrentHashMap<>();

  private static volatile ActivityRegistry INSTANCE = null;

  private ActivityRegistry() {
  }

  public static ActivityRegistry getInstance() {
    if (null == INSTANCE) {
      synchronized (ActivityRegistry.class) {
        if (null == INSTANCE) {
          INSTANCE = new ActivityRegistry();
        }
      }
    }
    return INSTANCE;
  }

  /**
   * 活动注册，若活动已存在，重新注册时会替换掉之前的活动
   * @param activity
   */
  public void register(Activity activity) {
    final Long id = activity.getId();
    final Activity offlineActivity = this.offlineActivityCache.get(id);
    if (null != offlineActivity) {
      this.offlineActivityCache.remove(id);
    }
    this.onlineActivityCache.put(id, activity);
  }

  /**
   * 活动取消注册
   * @param activity
   */
  public void deregister(Activity activity) {
    final Long id = activity.getId();
    this.offlineActivityCache.put(id, activity);

    final Activity onlineActivity = this.onlineActivityCache.get(id);
    if (null != onlineActivity) {
      this.onlineActivityCache.remove(id);
    }
  }

  /**
   * 获取活动
   * @param id 活动ID
   * @return
   */
  public Activity getOnline(final Long id) {
    return this.onlineActivityCache.get(id);
  }

  /**
   * 加载活动
   * @param id 活动ID
   * @return
   */
  public Activity load(final Long id) {
    final Activity offlineActivity = this.offlineActivityCache.get(id);
    if (null != offlineActivity) {
      throw new RuntimeException("活动已下线");
    }

    final Activity onlineActivity = this.onlineActivityCache.get(id);
    if (null == onlineActivity) {
      throw new ActivityNotFoundException();
    }

    return onlineActivity;
  }

}
