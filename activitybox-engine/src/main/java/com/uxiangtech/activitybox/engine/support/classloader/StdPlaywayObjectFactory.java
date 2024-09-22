package com.uxiangtech.activitybox.engine.support.classloader;

import com.uxiangtech.activitybox.sdk.playways.StdPlayway;

import java.util.Map;

/**
 * 标准玩法对象工厂
 */
public interface StdPlaywayObjectFactory {

  /**
   * 根据源码加载类
   * @param code 源代码
   * @return
   */
   Map<String, StdPlayway> newInstances(final String code);
}
