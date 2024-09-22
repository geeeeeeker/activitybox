package com.uxiangtech.activitybox.engine.modules.sdkimpl.api;

import com.uxiangtech.activitybox.engine.modules.prop.PropService;
import com.uxiangtech.activitybox.sdk.api.PropApi;
import com.uxiangtech.activitybox.sdk.attribute.AwardAttribute;
import com.uxiangtech.activitybox.sdk.award.Award;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 游戏道具API实现
 */
@Component
public class PropApiImpl implements PropApi {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(PropApiImpl.class);

  private final PropService propService;

  public PropApiImpl(final PropService propService) {
    this.propService = propService;
  }

  /**
   * 预校验道具ID
   */
  private boolean preValidPropId(final String propId) {
    if (StringUtils.isBlank(propId)) {
      LOGGER.error("道具预校验失败，道具ID为空", propId);
      return false;
    }
    final AwardAttribute awardAttribute =
      this.getAwardAttributeMap().get(propId);
    if (null == awardAttribute) {
      LOGGER.error("道具预校验失败，道具[{}]不存在", propId);
      return false;
    }
    return true;
  }

  private boolean preValidPropIds(List<String> propIds) {
    if (CollectionUtils.isEmpty(propIds)) {
      LOGGER.error("道具预校验失败，道具ID列表为空", propIds);
      return false;
    }
    for (String propId : propIds) {
      if (!this.preValidPropId(propId)) {
        return false;
      }
    }
    return true;
  }

  private Map<String, AwardAttribute> getAwardAttributeMap() {
    return
      this.getContext().getActivity().getAttribute().getAwards()
        .stream().filter(attribute ->
          Award.Type.PROP.name().equals(attribute.getType()))
        .collect(Collectors.toMap(AwardAttribute::getId, Function.identity()));
  }

  /**
   * 向上下文用户发放单个游戏道具
   *
   * @param propId 道具ID
   * @return
   */
  @Override
  public boolean grantOneProp(String propId) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    return this.propService.grantProps(getContext().getActivityId(),
      getContext().getPlaywayId(), getContext().getActionId(), getContext().getUserId(), propId, 1L);
  }

  /**
   * 向上下文用户发放多个游戏道具
   *
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  @Override
  public boolean grantMultiProps(String propId, Long quantity) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    if (quantity <= 0) {
      quantity = 1L;
    }

    return
      this.propService.grantProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), getContext().getUserId(), propId, quantity);
  }

  /**
   * 向指定用户发放单个游戏道具
   *
   * @param userId 指定用户ID
   * @param propId 道具ID
   * @return
   */
  @Override
  public boolean grantOneProp(Long userId, String propId) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    return
      this.propService.grantProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), userId, propId, 1L);
  }

  /**
   * 向指定用户发放多个游戏道具
   *
   * @param userId   指定用户ID
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  @Override
  public boolean grantMultiProps(Long userId, String propId, Long quantity) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    if (quantity <= 0) {
      quantity = 1L;
    }
    return
      this.propService.grantProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), userId, propId, quantity);
  }

  /**
   * 使用上下文用户单个游戏道具
   *
   * @param propId 道具ID
   * @return
   */
  @Override
  public boolean useOneProp(String propId) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    return
      this.propService.useProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), getContext().getUserId(), propId, 1L);
  }

  /**
   * 使用上下文用户多个游戏道具
   *
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  @Override
  public boolean useMultiProps(String propId, Long quantity) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    if (quantity <= 0) {
      quantity = 1L;
    }
    return
      this.propService.useProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), getContext().getUserId(), propId, quantity);
  }

  /**
   * 使用指定用户单个游戏道具
   *
   * @param userId 指定用户ID
   * @param propId 道具ID
   * @return
   */
  @Override
  public boolean useOneProp(Long userId, String propId) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    return
      this.propService.useProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), userId, propId, 1L);
  }

  /**
   * 使用指定用户多个游戏道具
   *
   * @param userId   指定用户ID
   * @param propId   道具ID
   * @param quantity 道具数量
   * @return
   */
  @Override
  public boolean useMultiProps(Long userId, String propId, Long quantity) {
    if (!this.preValidPropId(propId)) {
      return false;
    }
    if (quantity <= 0) {
      quantity = 1L;
    }
    return
      this.propService.useProps(getContext().getActivityId(),
        getContext().getPlaywayId(), getContext().getActionId(), userId, propId, quantity);
  }

  /**
   * 统计上下文用户的单个游戏道具数量
   *
   * @param propId 道具ID
   * @return
   */
  @Override
  public Long countPropQuantity(String propId) {
    if (!this.preValidPropId(propId)) {
      return 0L;
    }
    return
      this.propService.countPropQuantity(
        getContext().getActivityId(), getContext().getUserId(), propId);
  }

  /**
   * 统计上下文用户的多个游戏道具数量
   *
   * @param propIds 道具ID列表
   * @return
   */
  @Override
  public Map<String, Long> countMultiPropsQuantity(List<String> propIds) {
    if (!this.preValidPropIds(propIds)) {
      return new HashMap<>();
    }
    return
      this.propService.countMultiPropsQuantity(
        getContext().getActivityId(), getContext().getUserId(), propIds);
  }

  /**
   * 统计上下文用户的全部游戏道具数量
   *
   * @return
   */
  @Override
  public Map<String, Long> countAllPropsQuantity() {
    return
      this.propService.countAllPropsQuantity(
        getContext().getActivityId(), getContext().getUserId());
  }

  /**
   * 统计指定用户的单个游戏道具数量
   *
   * @param userId 指定用户ID
   * @param propId 道具ID
   * @return
   */
  @Override
  public Long countPropQuantity(Long userId, String propId) {
    if (!this.preValidPropId(propId)) {
      return 0L;
    }
    return
      this.propService.countPropQuantity(
        getContext().getActivityId(), userId, propId);
  }

  /**
   * 统计指定用户的多个游戏道具数量
   *
   * @param userId  指定用户ID
   * @param propIds 道具ID列表
   * @return
   */
  @Override
  public Map<String, Long> countMultiPropsQuantity(Long userId, List<String> propIds) {
    if (!this.preValidPropIds(propIds)) {
      return new HashMap<>();
    }
    return
      this.propService.countMultiPropsQuantity(
        getContext().getActivityId(), userId, propIds);
  }

  /**
   * 统计指定用户的全部游戏道具数量
   *
   * @param userId 指定用户ID
   * @return
   */
  @Override
  public Map<String, Long> countAllPropsQuantity(Long userId) {
    return
      this.propService.countAllPropsQuantity(
        getContext().getActivityId(), userId);
  }
}
