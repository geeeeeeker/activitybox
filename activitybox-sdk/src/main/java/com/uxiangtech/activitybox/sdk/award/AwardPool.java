package com.uxiangtech.activitybox.sdk.award;

import com.uxiangtech.activitybox.sdk.activity.Activity;

import java.util.List;
import java.util.Map;

/**
 * 奖池，定义抽奖规则及相应奖项
 *
 * 注意：配置时，每个奖池强制要求必须包含一个谢谢参与奖
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
   * 获取活动
   * @return
   */
  Activity getActivity();

  /**
   * 获取奖项列表
   * @return
   */
  Map<String, AwardOption> getAwardOptionMap();

  /**
   * 获取奖项列表
   * @return
   */
  List<AwardOption> getAwardOptions();

  /**
   * 抽取奖项
   * @param context
   * @return
   */
  AwardDrawingResult draw(AwardDrawingContext context);

  /**
   * 抽奖规则类型
   */
  enum RuleType {
    DIRECT, RANDOM
  }

  /**
   *
   */
  class Limit {

  }



}
