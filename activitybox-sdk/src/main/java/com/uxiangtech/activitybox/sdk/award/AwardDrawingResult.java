package com.uxiangtech.activitybox.sdk.award;

/**
 * 抽奖结果
 */
public interface AwardDrawingResult {

  /**
   * 获取奖品ID
   * @return
   */
  String getAwardId();

  /**
   * 获取奖池ID
   * @return
   */
  String getAwardPoolId();

  /**
   * 获取奖项ID
   * @return
   */
  String getAwardOptionId();

  /**
   * 获取奖项名称
   * @return
   */
  String getAwardOptionName();

  /**
   * 判断是否是谢谢参与安慰奖
   * @return
   */
  Boolean isThanks();

  /**
   * 获取所中奖项图标
   * @return
   */
  String getIcon();

  /**
   * 获取领奖页
   * @return
   */
  String getLink();


}
