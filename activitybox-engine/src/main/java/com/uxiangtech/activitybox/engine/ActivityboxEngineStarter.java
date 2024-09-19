package com.uxiangtech.activitybox.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.uxiangtech.activitybox.data")
@SpringBootApplication
public class ActivityboxEngineStarter {
  public static void main(String[] args) {
    SpringApplication.run(ActivityboxEngineStarter.class, args);
  }
}
