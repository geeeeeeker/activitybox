package com.uxiangtech.activitybox.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public class SpringBeanHolder implements ApplicationContextAware {

  private static ApplicationContext AC;

  public static <T> T getBean(Class<T> clazz) {
    return AC.getBean(clazz);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    AC = applicationContext;
  }

  public static void publishEvent(final ApplicationEvent event) {
    AC.publishEvent(event);
  }
}