package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.award.Award;

import java.util.Map;

/**
 * 奖池，定义抽奖规则及相应奖项
 */
public interface AwardPool {

  /**
   * 获取抽奖规则ID
   * @return
   */
  String getId();

  /**
   * 获取抽奖规则名称
   * @return
   */
  String getName();

  /**
   * 获取抽奖规则描述
   * @return
   */
  String getDesc();

  /**
   * 获取抽奖规则类型
   * @return
   */
  RuleType getType();

  /**
   * 获取规则奖项
   * @return
   */
  Map<String, Award> getAwardMap();

  /**
   * 抽奖规则类型
   */
  enum RuleType {
    DIRECT, RANDOM
  }

}
