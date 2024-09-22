package com.uxiangtech.activitybox.engine.modules.sdkimpl.playway;

import com.uxiangtech.activitybox.sdk.action.Action;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.attribute.PlaywayAttribute;

import java.util.function.Supplier;

public abstract class AbstractPlayway<P extends Playway<?>> implements Playway<P> {

  private final String id;

  private final String name;

  private final Integer version;

  private PlaywayAttribute playwayAttribute;

  private final Activity activity;

  private final ClassLoader classLoader;

  public AbstractPlayway(final String id, final String name, PlaywayAttribute attribute, Activity activity, ClassLoader classLoader) {
    this.id = id;
    this.name = name;
    this.version = attribute.getVersion();
    this.activity = activity;
    this.classLoader = classLoader;
    this.playwayAttribute = attribute;

    String code = attribute.getCode();
    // 构建代码
  }

  public AbstractPlayway(PlaywayAttribute attribute, Activity activity, ClassLoader classLoader) {
    this(attribute.getId(), attribute.getName(), attribute, activity, classLoader);
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Integer version() {
    return this.version;
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
  public Language getLang() {
    return Playway.Language.valueOf(this.playwayAttribute.getLang());
  }

  @Override
  public Integer getVersion() {
    return this.version;
  }
}
