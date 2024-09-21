package com.uxiangtech.activitybox.engine.support;

import com.uxiangtech.activitybox.data.activity.ActivityDAO;
import com.uxiangtech.activitybox.data.activity.ActivityPO;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.activity.ActivityFactory;
import com.uxiangtech.activitybox.engine.modules.activity.event.ActivityOfflineEvent;
import com.uxiangtech.activitybox.engine.modules.activity.event.ActivityOnlineEvent;
import com.uxiangtech.activitybox.engine.modules.activity.registry.ActivityRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 引擎初始化器
 * <p>
 * 初始化时，异步读取活动配置数据到内存，解析生成Activity对象
 */
public class ActivityboxEngineInitializer implements ApplicationRunner {

  private LocalDateTime lastLoadTime = null;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    Executors.newScheduledThreadPool(1)
      .scheduleAtFixedRate(() -> {

        final LocalDateTime now = LocalDateTime.now();

        final ActivityDAO activityDAO = SpringBeanHolder.getBean(ActivityDAO.class);

        List<ActivityPO> activities = null;

        Boolean isIncrementalLoading = null;

        // 首次加载时，全量加载状态是在线的活动；其余增量加载修改过的活动
        if (null == this.lastLoadTime) {
          activities = activityDAO.findAllOnlineActivities();
          isIncrementalLoading = false;
        } else {
          activities = activityDAO.findAllModifiedActivities(this.lastLoadTime);
          isIncrementalLoading = true;
        }

        for (ActivityPO activityPO : activities) {

          final Activity activity =
            ActivityFactory.getInstance().newActivity(activityPO);

          final Activity.Status status =
            Activity.Status.valueOf(activityPO.getStatus());

          if (Activity.Status.OFFLINE == status) {
            SpringBeanHolder.publishEvent(new ActivityOfflineEvent(activity, isIncrementalLoading));
          } else if (Activity.Status.ONLINE == status) {
            SpringBeanHolder.publishEvent(new ActivityOnlineEvent(activity, isIncrementalLoading));
          } else {
            // TODO 未来可能支持发布审批等，暂时不应该有状态
          }
        }

        this.lastLoadTime = now;

      }, 10, 30, TimeUnit.SECONDS);


  }
}
