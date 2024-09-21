package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.option.AwardOption;

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

}
