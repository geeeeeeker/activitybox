package com.uxiangtech.activitybox.sdk.api;

import com.uxiangtech.activitybox.sdk.context.ActionCallContextCapable;

import java.util.List;
import java.util.Map;

/**
 * 游戏道具API
 */
public interface PropApi extends ActionCallContextCapable {

  // 发放道具

  /**
   * 向上下文用户发放单个游戏道具
   *
   * @param propId 道具ID
   * @return
   */
  boolean grantOneProp(final String propId);

  /**
   * 向上下文用户发放多个游戏道具
   *
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  boolean grantMultiProps(final String propId, final Long quantity);

  /**
   * 给指定用户发放单个游戏道具
   *
   * @param userId 指定用户ID
   * @param propId 道具ID
   * @return
   */
  boolean grantOneProp(final Long userId, final String propId);

  /**
   * 给指定用户发放多个游戏道具
   *
   * @param userId   指定用户ID
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  boolean grantMultiProps(final Long userId, final String propId, final Long quantity);

  // 使用道具

  /**
   * 使用上下文用户单个游戏道具
   *
   * @param propId 道具ID
   * @return
   */
  boolean useOneProp(final String propId);

  /**
   * 使用上下文用户多个游戏道具
   *
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  boolean useMultiProps(final String propId, final Long quantity);

  /**
   * 使用指定用户单个游戏道具
   *
   * @param userId 指定用户ID
   * @param propId 道具ID
   * @return
   */
  boolean useOneProp(final Long userId, final String propId);

  /**
   * 使用上下文用户发放多个游戏道具
   *
   * @param userId   指定用户ID
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  boolean useMultiProps(final Long userId, final String propId, final Long quantity);


  // 统计道具

  /**
   * 统计上下文用户的单个游戏道具数量
   *
   * @param propId 道具ID
   * @return 游戏道具数量
   */
  Long countPropQuantity(final String propId);

  /**
   * 统计上下文用户的多个游戏道具数量
   *
   * @param propIds 道具ID列表
   * @return 游戏道具数量
   */
  Map<String, Long> countMultiPropsQuantity(final List<String> propIds);

  /**
   * 统计上下文用户的全部游戏道具数量
   *
   * @return 游戏道具数量
   */
  Map<String, Long> countAllPropsQuantity();

  /**
   * 统计指定用户的单个游戏道具数量
   *
   * @param userId 指定用户ID
   * @param propId 道具ID
   * @return 游戏道具数量
   */
  Long countPropQuantity(final Long userId, final String propId);

  /**
   * 统计上下文用户的多个游戏道具数量
   *
   * @param userId  指定用户ID
   * @param propIds 道具ID列表
   * @return 游戏道具数量
   */
  Map<String, Long> countMultiPropsQuantity(final Long userId, final List<String> propIds);

  /**
   * 统计上下文用户的全部游戏道具数量
   *
   * @param userId 指定用户ID
   * @return 游戏道具数量
   */
  Map<String, Long> countAllPropsQuantity(final Long userId);


}
