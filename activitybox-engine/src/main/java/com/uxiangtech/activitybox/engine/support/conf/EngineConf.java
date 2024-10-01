package com.uxiangtech.activitybox.engine.support.conf;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.support.ActivityboxEngineInitializingListener;
import com.uxiangtech.activitybox.engine.support.mvc.MvcConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EngineConf {

  @Bean
  public SpringBeanHolder springBeanHolder() {
    return new SpringBeanHolder();
  }

  @Bean
  public ActivityboxEngineInitializingListener engineInitializer() {
    return new ActivityboxEngineInitializingListener();
  }

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new MvcConfigurerAdapter();
  }




}
