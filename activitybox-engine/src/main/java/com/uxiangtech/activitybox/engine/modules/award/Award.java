package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;

/**
 * 奖品
 */
public interface Award {

  /**
   * 获取奖品ID
   * @return
   */
  String getId();

  /**
   * 获取奖品名称
   * @return
   */
  String getName();

  /**
   * 获取奖项图标URL
   * @return
   */
  String getIcon();

  /**
   * 获取奖项对应商品URL
   * @return
   */
  String getLink();

  /**
   * 获取奖品归属活动
   * @return
   */
  Activity getActivity();

  /**
   * 获取奖品库存，奖品库存是该活动的所有
   * @return
   */
  Long getStock();

  enum Type {
    PROP,  // 游戏道具


  }

}
