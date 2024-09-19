package com.uxiangtech.activitybox.engine.modules.activity;

import com.uxiangtech.activitybox.data.activity.ActivityPO;
import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.engine.modules.playways.invitation.InvitationPlaywayImpl;
import com.uxiangtech.activitybox.engine.support.classloader.PlaywayJavaInstanceFactory;
import com.uxiangtech.activitybox.sdk.attribute.PlaywayAttribute;
import com.uxiangtech.activitybox.sdk.playways.PlaywayType;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlayway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由于活动构建是一个复杂的过程，涉及活动代码加载编译以及活动配置解析，因此采用工厂模式实现
 */
public class ActivityFactory {

  public static Activity newActivity(final ActivityPO activityPO) {

    // 活动构建过程涉及代码重新编译

    final Activity activity = new ActivityImpl(activityPO);

    buildPlayways(activity);

    return activity;
  }

  public static void buildPlayways(final Activity activity) {
    final Map<String, Playway<?>> playwayMap = new HashMap<>();

    final List<PlaywayAttribute> playwayAttributes = activity.getAttribute().getPlayways();

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    final PlaywayJavaInstanceFactory playwayJavaInstanceFactory =
      new PlaywayJavaInstanceFactory(classLoader);

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

  private static Playway<?> buildPlayway(Activity activity, StdPlayway stdPlaywayInstance, PlaywayType playwayType) {
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
