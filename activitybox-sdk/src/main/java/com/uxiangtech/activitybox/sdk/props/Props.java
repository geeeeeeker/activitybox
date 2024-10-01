package com.uxiangtech.activitybox.sdk.props;

import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.Award;

/**
 * 道具，道具也可以作为奖品中的特殊虚拟奖品
 */
public interface Props {

  /**
   * 获取道具ID
   *
   * @return
   */
  String getId();

  /**
   * 获取道具名称
   *
   * @return
   */
  String getName();

  /**
   * 获取道具类型
   *
   * @return
   */
  String getType();

  /**
   * 道具归属的活动
   * @return
   */
  Activity getActivity();

  /**
   * 道具归属的奖品
   *
   * 注意：道具可能归属于奖品，也可能单独使用。一般来说作为奖品都是由永久类型道具承担功能。
   * @return
   */
  Award getAward();

  enum Type {

    DAILY,    // 每日清零道具

    WEEKLY,   // 每周清零道具

    MONTHLY,  // 每月清零道具

    YEARLY,   // 每年清零道具

    ALWAYS,   // 永久道具

  }
}
