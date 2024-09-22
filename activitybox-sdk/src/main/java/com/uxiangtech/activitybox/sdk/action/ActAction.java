package com.uxiangtech.activitybox.sdk.action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 活动动作注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActAction {

  /**
   * 动作ID
   * @return
   */
  String id();

  /**
   * 动作名称
   * @return
   */
  String name() default "";

}
