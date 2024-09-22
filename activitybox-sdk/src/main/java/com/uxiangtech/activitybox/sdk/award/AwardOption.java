package com.uxiangtech.activitybox.sdk.award;

import java.math.BigDecimal;

/**
 * 奖项，一个抽奖规则可包含多个奖项
 */
public interface AwardOption {

  /**
   * 获取奖项ID
   * @return
   */
  String getId();

  /**
   * 获取奖项名称
   * @return
   */
  String getName();

  /**
   * 获取奖项概率
   * @return
   */
  BigDecimal getProbability();

  /**
   * 获取奖项库存限制
   * @return
   */
  Long getStock();

  /**
   * 获取奖项归属抽奖规则
   * @return
   */
  AwardPool getAwardPool();

  /**
   * 获取奖项归属的奖品
   * @return
   */
  Award getAward();


}
