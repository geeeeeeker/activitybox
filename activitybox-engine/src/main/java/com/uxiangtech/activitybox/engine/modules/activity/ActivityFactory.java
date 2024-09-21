package com.uxiangtech.activitybox.engine.modules.activity;

import com.uxiangtech.activitybox.data.activity.ActivityPO;
import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.PropAward;
import com.uxiangtech.activitybox.engine.modules.award.pool.AwardPool;
import com.uxiangtech.activitybox.engine.modules.award.pool.DirectAwardPool;
import com.uxiangtech.activitybox.engine.modules.award.pool.RandomAwardPool;
import com.uxiangtech.activitybox.engine.modules.page.Page;
import com.uxiangtech.activitybox.engine.modules.page.PageImpl;
import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.engine.modules.playways.invitation.InvitationPlaywayImpl;
import com.uxiangtech.activitybox.engine.support.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.support.classloader.JavaBasedPlaywayFactory;
import com.uxiangtech.activitybox.sdk.attribute.PlaywayAttribute;
import com.uxiangtech.activitybox.sdk.playways.PlaywayType;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlayway;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由于活动构建是一个复杂的过程，涉及活动代码加载编译以及活动配置解析，因此采用工厂模式实现
 */
public final class ActivityFactory {

  private static volatile ActivityFactory INSTANCE = null;

  private ActivityFactory() {
  }

  public static ActivityFactory getInstance() {
    if (INSTANCE == null) {
      synchronized (ActivityFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new ActivityFactory();
        }
      }
    }
    return INSTANCE;
  }

  public Activity newActivity(ActivityPO activityPO) {

    // 事务管理器
    final PlatformTransactionManager txManager =
      SpringBeanHolder.getBean(PlatformTransactionManager.class);

    final Activity activity = new ActivityImpl(activityPO, txManager);

    // 活动构建过程涉及代码重新编译
    this.buildPlayways(activity);

    // 构建页面
    this.buildPages(activity);

    // 构建奖品
    this.buildAwards(activity);

    // 构建奖池，奖池内奖项依赖于奖品
    this.buildPools(activity);

    return activity;

  }

  private void buildPools(Activity activity) {
    activity.getAttribute().getPools().forEach(poolAttribute -> {
      final AwardPool.RuleType ruleType =
        AwardPool.RuleType.valueOf(poolAttribute.getType());
      AwardPool awardPool = null;
      switch (ruleType) {
        case RANDOM:
          awardPool = new RandomAwardPool(poolAttribute, activity);
          break;
        case DIRECT:
          awardPool = new DirectAwardPool(poolAttribute, activity);
      }
      activity.getAwardPoolMap().put(awardPool.getId(), awardPool);
    });
  }

  private void buildAwards(Activity activity) {
    activity.getAttribute().getAwards().forEach(awardAttribute -> {
      Award award = null;
        Award.Type awardType =
        Award.Type.valueOf(awardAttribute.getType());
      switch (awardType) {
        case PROP:
          award = new PropAward(awardAttribute, activity);
      }
      activity.getAwardMap().put(awardAttribute.getId(), award);
    });
  }

  private void buildPages(Activity activity) {
    activity.getAttribute().getPages()
      .forEach(pageAttribute -> {
        final String pageId = pageAttribute.getId();
        final Page page = new PageImpl(pageAttribute, activity);
        activity.getPageMap().put(pageId, page);
      });
  }

  private void buildPlayways(final Activity activity) {
    final Map<String, Playway<?>> playwayMap = new HashMap<>();

    final List<PlaywayAttribute> playwayAttributes = activity.getAttribute().getPlayways();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    final JavaBasedPlaywayFactory playwayJavaInstanceFactory =
      new JavaBasedPlaywayFactory(classLoader);

    for (PlaywayAttribute playwayAttribute : playwayAttributes) {

      final String lang = playwayAttribute.getLang();

      // 代码编译生成标准玩法对象

      if (Playway.Language.JAVA.name().equals(lang)) {
        Map<String, StdPlayway> stdPlaywayMap =
          playwayJavaInstanceFactory.newInstances(playwayAttribute.getCode());
        for (StdPlayway stdPlaywayInstance : stdPlaywayMap.values()) {

          if (stdPlaywayInstance instanceof InvitationStdPlayway) {
            PlaywayType playwayType = PlaywayType.INVITATION;
            // 构建玩法
            final Playway<?> playway = buildPlayway(activity, stdPlaywayInstance, playwayType);
            playwayMap.put(playway.getId(), playway);
          }
        }
      }
    }

    activity.setPlaywayMap(playwayMap);
  }

  private Playway<?> buildPlayway(Activity activity, StdPlayway stdPlaywayInstance, PlaywayType playwayType) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    final String playwayId = stdPlaywayInstance.getId();


    final String playwayName = stdPlaywayInstance.getName();

    // 不同的Playway，Action逻辑并不相同，因此必须要在具体的Playway中实现action初始化逻辑
    switch (playwayType) {
      case INVITATION:
        return new InvitationPlaywayImpl(playwayId, playwayName, (InvitationStdPlayway) stdPlaywayInstance, activity, classLoader);
    }

    return null;
  }
}
