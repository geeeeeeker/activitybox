package com.uxiangtech.activitybox.sdk.award;

import com.uxiangtech.activitybox.sdk.activity.Activity;

/**
 * 奖品，配置的奖项最终还是映射到奖品，由奖品实际减扣库存。奖品用于抽奖。
 */
public interface Award {

  /**
   * 获取奖品ID
   *
   * @return
   */
  String getId();

  /**
   * 获取奖品名称
   *
   * @return
   */
  String getName();

  /**
   * 获取奖品类型，如商品类型，道具类型等
   *
   * @return
   */
  String getType();

  /**
   * 获取奖项图标URL
   *
   * @return
   */
  String getIcon();

  /**
   * 获取奖品关联ID，如商品ID，道具ID
   *
   * @return
   */
  String getRefId();

  /**
   * 获取奖品归属活动
   *
   * @return
   */
  Activity getActivity();

  /**
   * 获取奖品库存，奖品库存是该活动所有的奖项库存
   *
   * @return
   */
  Long getStock();

  /**
   * 实际发放奖品
   *
   * @param context
   * @return 返回结果根据商品中心或合作方返回结果决定
   */
  AwardExecutedResult execute(AwardDrawingContext context);

  enum Type {

    DRAWING, // 抽奖

    PROPS,  // 道具

    GOODS,  // 商品
  }

}
