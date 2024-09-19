package com.uxiangtech.activitybox.engine.modules.playways;

import com.uxiangtech.activitybox.engine.modules.actions.Action;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.ActivityAttribute;
import com.uxiangtech.activitybox.sdk.attribute.PlaywayAttribute;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;

import java.util.function.Supplier;

public abstract class AbstractPlayway<P extends Playway<?>> implements Playway<P> {

  private final String playwayId;

  private final String playwayName;

  private PlaywayAttribute playwayAttribute;

  private final Activity activity;

  private final ClassLoader classLoader;

  public AbstractPlayway(String playwayId, String playwayName, Activity activity, ClassLoader classLoader) {
    this.playwayId = playwayId;
    this.playwayName = playwayName;
    this.activity = activity;
    this.classLoader = classLoader;

    // 初始化玩法属性
//    this.initPlaywayAttribute();
  }

//  private void initPlaywayAttribute() {
//    final ActivityAttribute activityAttribute = this.activity.getAttribute();
//    this.playwayAttribute =
//      activityAttribute.getPlayways().stream()
//        .filter(attribute ->
//          attribute.getId().equals(this.playwayId))
//        .findFirst().
//        orElseThrow(() -> new RuntimeException("玩法配置错误：无法找到玩法[" + this.playwayId + "]"));
//  }

  @Override
  public String getId() {
    return this.playwayId;
  }

  @Override
  public String getName() {
    return this.playwayName;
  }

  @Override
  public Activity getActivity() {
    return this.activity;
  }

//  @Override
//  public Object getStdPlaywayInstance() {
//    return this.stdPlaywayInstance;
//  }

  @Override
  public Action getAction(final String actionId) {
    return this.getActionMap().get(actionId);
  }

  @Override
  public Action getActionOrThrow(final String actionId, Supplier<RuntimeException> supplier) {
    final Action action = this.getActionMap().get(actionId);
    if (null == action) {
      throw supplier.get();
    } else {
      return action;
    }
  }

  @Override
  public ClassLoader getClassLoader() {
    return this.classLoader;
  }

  @Override
  public String getCode() {
    return this.playwayAttribute.getCode();
  }

  @Override
  public Language getLanguage() {
    return Playway.Language.valueOf(this.playwayAttribute.getLang());
  }
}
