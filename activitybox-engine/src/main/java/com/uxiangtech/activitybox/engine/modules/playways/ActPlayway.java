package com.uxiangtech.activitybox.engine.modules.playways;

import com.uxiangtech.activitybox.sdk.playways.StdPlaywayConf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 活动玩法注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActPlayway {

  /**
   * 玩法ID
   * @return
   */
  String id();

  /**
   * 玩法名称
   * @return
   */
  String name() default "";

  /**
   * 玩法类型，解析时根据该类型确定必须包含的方法
   * @return
   */
  String type();
}
