package com.uxiangtech.activitybox.engine.support.classloader;

import com.uxiangtech.activitybox.sdk.playway.StdPlayway;
import groovy.lang.GroovyClassLoader;

import java.util.Map;

public class GroovyBasedStdPlaywayObjectFactory implements StdPlaywayObjectFactory {
  private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

  @Override
  public Map<String, StdPlayway> newInstances(String code) {

    groovyClassLoader.parseClass(code);

    return null;
  }
}
