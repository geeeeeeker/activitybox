package com.uxiangtech.activitybox.engine.modules.page;

public abstract class AbstractPage implements Page {
  protected final String id;
  protected final String name;
  protected final String code;

  public AbstractPage(String id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getCode() {
    return code;
  }
}
