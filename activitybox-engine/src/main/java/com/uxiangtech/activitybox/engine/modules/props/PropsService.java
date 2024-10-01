package com.uxiangtech.activitybox.engine.modules.props;

import java.util.List;
import java.util.Map;

public interface PropsService {

  /**
   * 发放游戏道具
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @param userId     用户ID
   * @param propsId    游戏道具ID
   * @param quantity   游戏道具数量
   * @return 是否发放成功，true 表示成功，false表示失败
   */
  boolean grantProps(Long activityId, String playwayId, String actionId, Long userId, String propsId, Long quantity);

  /**
   * 使用游戏道具
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @param userId     用户ID
   * @param propsId    游戏道具ID
   * @param quantity   游戏道具数量
   * @return 是否使用成功，true 表示成功，false表示失败
   */
  boolean useProps(Long activityId, String playwayId, String actionId, Long userId, String propsId, Long quantity);

  /**
   * 统计单个游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @param propsId    游戏道具ID
   * @return 游戏道具数量
   */
  Long countPropQuantity(Long activityId, Long userId, String propsId);

  /**
   * 统计多个游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @param propsIds   游戏道具ID列表
   * @return 游戏道具数量
   */
  Map<String, Long> countMultiPropsQuantity(Long activityId, Long userId, List<String> propsIds);

  /**
   * 统计全部游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @return 游戏道具数量
   */
  Map<String, Long> countAllPropsQuantity(Long activityId, Long userId);

}
