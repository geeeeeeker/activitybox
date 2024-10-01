package com.uxiangtech.activitybox.sdk.award;

import java.math.BigDecimal;

/**
 * 奖项，一个抽奖规则可包含多个奖项
 *
 * 奖项是奖品和奖池连接点，是抽象的概念，奖池定义了抽奖规则、发给谁、发多少、如何发，奖品定义了发什么。
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
   * 获取奖项图片，默认与奖品相同
   * @return
   */
  String getIcon();

  /**
   * 获取奖项概率
   * @return
   */
  BigDecimal getProbability();

  /**
   * 获取奖项库存限制
   * @return
   */
  Long getTotalStock();

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

  // TODO 针对游戏道具和抽奖次数，可以一次性发放多次；其他的奖品如果是复合型的通过商品中心或商家自行控制物流配送

  /**
   * 获取单次发放奖品数量，针对的一般是无实际库存限制的产品
   * @return
   */
  Long getGrantQuantity();
}
