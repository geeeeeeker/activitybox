package com.uxiangtech.activitybox.admin.modules.activity;

import com.alibaba.fastjson2.JSONObject;

import java.time.LocalDateTime;

public class ActivityDO {
  private Long id;
  private String name;
  private String desc;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private JSONObject attributes;
  private ActivityStatus status;
  private String region;
  protected Boolean isDeleted;
  protected LocalDateTime gmtCreate;
  protected LocalDateTime gmtModified;

}
