package com.uxiangtech.activitybox.sdk.playway;

public abstract class AbstractStdPlaywayConf implements StdPlaywayConf {

  private final String id;

  private final String name;

  public AbstractStdPlaywayConf(String id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }
}
