package com.uxiangtech.activitybox.engine.modules.activity;

import com.alibaba.fastjson2.JSON;
import com.uxiangtech.activitybox.data.activity.ActivityPO;
import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.pool.AwardPool;
import com.uxiangtech.activitybox.engine.modules.page.Page;
import com.uxiangtech.activitybox.engine.modules.playway.Playway;
import com.uxiangtech.activitybox.engine.modules.variable.Variables;
import com.uxiangtech.activitybox.engine.support.classloader.ActivityClassLoader;
import com.uxiangtech.activitybox.sdk.attribute.ActivityAttribute;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ActivityImpl implements Activity {

  private Long id;                      // 活动ID
  private String name;                  // 活动名称
  private String ruleDesc;              // 活动规则描述
  private LocalDateTime startTime;      // 活动开始时间
  private LocalDateTime endTime;        // 活动结束时间
  private Status status;                // 活动状态
  private ActivityAttribute attribute;  // 活动属性
  private LocalDateTime gmtCreate;
  private LocalDateTime gmtModified;

  private final PlatformTransactionManager txManager;

  public ActivityImpl(final ActivityPO activityPO, final PlatformTransactionManager txManager) {
    this.id = activityPO.getId();
    this.name = activityPO.getName();
    this.ruleDesc = activityPO.getRuleDesc();
    this.startTime = activityPO.getStartTime();
    this.endTime = activityPO.getEndTime();
    this.status = Status.valueOf(activityPO.getStatus());
    this.attribute = JSON.parseObject(activityPO.getAttribute(), ActivityAttribute.class);
    this.gmtCreate = activityPO.getGmtCreate();
    this.gmtModified = activityPO.getGmtModified();
    this.txManager = txManager;
  }

  // 以下信息间接从活动属性中解析得到

  private Map<String, Page> pageMap;            // 页面集合
  private Map<String, Playway<?>> playwayMap;   // 玩法集合
  private Map<String, Award> awardMap;          // 奖品集合
  private Map<String, AwardPool> awardPoolMap;  // 奖池集合

  private Variables variables;                  // 变量集合

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getRuleDesc() {
    return this.ruleDesc;
  }

  @Override
  public ActivityAttribute getAttribute() {
    return this.attribute;
  }

  @Override
  public void setVariables(Variables variables) {
    this.variables = variables;
  }

  @Override
  public Variables getVariables() {
    return this.variables;
  }

  @Override
  public void setPageMap(Map<String, Page> pageMap) {
    this.pageMap = pageMap;
  }

  @Override
  public Map<String, Page> getPageMap() {
    if (null == this.pageMap) {
      this.pageMap = new HashMap<>();
    }
    return this.pageMap;
  }

  @Override
  public Page getPageOrDegradeToNullPage(final String pageId) {
    return this.pageMap.getOrDefault(pageId, Page.INVALID);
  }

  @Override
  public void setPlaywayMap(Map<String, Playway<?>> playwayMap) {
    this.playwayMap = playwayMap;
  }

  @Override
  public Map<String, Playway<?>> getPlaywayMap() {
    if (null == this.playwayMap) {
      this.playwayMap = new HashMap<>();
    }
    return this.playwayMap;
  }

  @Override
  public Playway getPlayway(final String playwayId) {
    return this.playwayMap.get(playwayId);
  }

  @Override
  public Playway getPlaywayOrThrow(final String playwayId, final Supplier<RuntimeException> supplier) {
    final Playway playway = this.playwayMap.get(playwayId);
    if (null == playway) {
      throw supplier.get();
    } else {
      return playway;
    }
  }

  @Override
  public void setAwardMap(Map<String, Award> awardMap) {
    this.awardMap = awardMap;
  }

  @Override
  public Map<String, Award> getAwardMap() {
    if (null == this.awardMap) {
      this.awardMap = new HashMap<>();
    }
    return this.awardMap;
  }

  @Override
  public void setAwardPoolMap(Map<String, AwardPool> awardPoolMap) {
    this.awardPoolMap = awardPoolMap;
  }

  @Override
  public Map<String, AwardPool> getAwardPoolMap() {
    if (null == this.awardPoolMap) {
      this.awardPoolMap = new HashMap<>();
    }
    return this.awardPoolMap;
  }

  @Override
  public LocalDateTime getStartTime() {
    return this.startTime;
  }

  @Override
  public LocalDateTime getEndTime() {
    return this.endTime;
  }

  @Override
  public Status getStatus() {
    return this.status;
  }

  @Override
  public LocalDateTime getGmtCreate() {
    return this.gmtCreate;
  }


  @Override
  public LocalDateTime getGmtModified() {
    return this.gmtModified;
  }

  @Override
  public ClassLoader getClassLoader() {
    return new ActivityClassLoader();
  }

  @Override
  public PlatformTransactionManager getTxManager() {
    return this.txManager;
  }
}
