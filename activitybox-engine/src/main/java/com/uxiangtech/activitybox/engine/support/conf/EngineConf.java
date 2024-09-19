package com.uxiangtech.activitybox.engine.support.conf;

import com.uxiangtech.activitybox.engine.support.SpringBeanHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConf {

  @Bean
  public SpringBeanHolder springBeanHolder() {
    return new SpringBeanHolder();
  }
}
