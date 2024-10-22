package com.uxiangtech.activitybox.sdk.activity;

import com.uxiangtech.activitybox.sdk.award.Award;
import com.uxiangtech.activitybox.sdk.award.AwardPool;
import com.uxiangtech.activitybox.sdk.page.Page;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.props.Props;
import com.uxiangtech.activitybox.sdk.variable.Variables;
import com.uxiangtech.activitybox.sdk.attribute.ActivityAttribute;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 活动定义接口，每一个活动就是一个交付给客户的项目，封装了项目相关的配置信息及活动代码加载的方式
 */
public interface Activity {

  /**
   * 获取活动ID
   * @return
   */
  Long getId();

  /**
   * 获取活动名称
   * @return
   */
  String getName();

  /**
   * 获取活动规则描述
   * @return
   */
  String getRuleDesc();

  /**
   * 获取活动属性信息
   * @return
   */
  ActivityAttribute getAttribute();

  void setVariables(Variables variables);

  Variables getVariables();

  void setPropsMap(Map<String, Props> propsMap);

  Map<String, Props> getPropsMap();


  void setPageMap(Map<String, Page> pageMap);

  /**
   * 获取活动包含的页面集合
   * @return
   */
  Map<String, Page> getPageMap();

  /**
   * 获取指定ID的页面，如果不存在则降级至空错误页
   * @param pageId 页面ID
   * @return
   */
  Page getPageOrDegradeToNullPage(final String pageId);

  void setPlaywayMap(Map<String, Playway<?>> playwayMap);

  /**
   * 获取活动包含的玩法集合
   * @return
   */
  Map<String, Playway<?>> getPlaywayMap();

  /**
   * 获取指定ID的活动玩法
   * @param playwayId 玩法ID
   * @return
   */
  Playway getPlayway(final String playwayId);

  /**
   * 获取指定ID的活动玩法
   * @param playwayId 玩法ID
   * @param supplier
   * @return
   */
  Playway getPlaywayOrThrow(final String playwayId, Supplier<RuntimeException> supplier);

  void setAwardMap(Map<String, Award> awardMap);

  /**
   * 获取活动包含的奖品集合
   * @return
   */
  Map<String, Award> getAwardMap();

  void setAwardPoolMap(Map<String, AwardPool> awardPoolMap);

  /**
   * 获取活动包含的奖池集合
   * @return
   */
  Map<String, AwardPool> getAwardPoolMap();

  /**
   * 活动开始时间
   * @return
   */
  LocalDateTime getStartTime();

  /**
   * 活动结束时间
   * @return
   */
  LocalDateTime getEndTime();

  /**
   * 活动状态
   * @return
   */
  Status getStatus();

  /**
   * 创建时间
   * @return
   */
  LocalDateTime getGmtCreate();

  /**
   * 更新时间
   * @return
   */
  LocalDateTime getGmtModified();

  /**
   * 获取活动类加载器，每个活动一个类加载器，实现不同活动间玩法代码隔离。
   *
   * 设计思路：未来考虑底层玩法代码复用，以JAR包、Git仓库、配置等方式实现玩法代码共享
   * @return
   */
  ClassLoader getClassLoader();

  /**
   * 获取事务管理器
   * @return
   */
  PlatformTransactionManager getTxManager();

  enum Status {
    DRAFT,   // 草稿，活动尚未发布，尚在配置阶段

    ONLINE,  // 上线，活动已发布，允许用户参与

    OFFLINE, // 下线，活动已结束，不允许用户再访问

    ENDED,   // 结束，活动已结束，允许用户访问查询结果，但不允许继续参与

    ;
  }
}
