package com.uxiangtech.activitybox.engine.modules.sdkimpl.prop;

import com.uxiangtech.activitybox.data.prop.PropRecordDAO;
import com.uxiangtech.activitybox.data.prop.PropRecordPO;
import com.uxiangtech.activitybox.data.prop.PropUserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PropServiceImpl implements PropService {

  private final PropUserDAO propUserDAO;
  private final PropRecordDAO propRecordDAO;
  private final TransactionTemplate transactionTemplate;

  public PropServiceImpl(PropUserDAO propUserDAO, PropRecordDAO propRecordDAO, TransactionTemplate transactionTemplate) {
    this.propUserDAO = propUserDAO;
    this.propRecordDAO = propRecordDAO;
    this.transactionTemplate = transactionTemplate;
  }

  /**
   * 发放游戏道具
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @param userId     用户ID
   * @param propId     游戏道具ID
   * @param quantity   游戏道具数量
   * @return 是否发放成功，true 表示成功，false表示失败
   */
  @Override
  public boolean grantProps(Long activityId, String playwayId, String actionId, Long userId, String propId, Long quantity) {

    if (quantity <= 0) {

    }

    final PropRecordPO propRecordPO =
      PropRecordPO.builder()
        .activityId(activityId)
        .playwayId(playwayId)
        .actionId(actionId)
        .userId(userId)
        .propId(propId)
        .quantity(quantity)
        .deltaType(PropRecordPO.DeltaType.ADD.name())
        .build()
        .markAsInitialized();

    this.transactionTemplate.executeWithoutResult(action -> {
      propUserDAO.insertOrAddQuantity(activityId, userId, propId, quantity);
      this.propRecordDAO.insert(propRecordPO);
    });

    return true;
  }

  /**
   * 使用游戏道具
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @param userId     用户ID
   * @param propId     游戏道具ID
   * @param quantity   游戏道具数量
   * @return 是否使用成功，true 表示成功，false表示失败
   */
  @Override
  public boolean useProps(Long activityId, String playwayId, String actionId, Long userId, String propId, Long quantity) {



    final PropRecordPO propRecordPO =
      PropRecordPO.builder()
        .activityId(activityId)
        .playwayId(playwayId)
        .actionId(actionId)
        .userId(userId)
        .propId(propId)
        .quantity(quantity)
        .deltaType(PropRecordPO.DeltaType.SUB.name())
        .build()
        .markAsInitialized();

    return false;
  }

  /**
   * 统计游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @param propId     游戏道具ID
   * @return 游戏道具数量
   */
  @Override
  public Long countPropQuantity(Long activityId, Long userId, String propId) {
    return null;
  }

  /**
   * 批量统计游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @param propIds    游戏道具ID列表
   * @return 游戏道具数量
   */
  @Override
  public Map<String, Long> countMultiPropsQuantity(Long activityId, Long userId, List<String> propIds) {
    return null;
  }

  /**
   * 统计全部游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @return 游戏道具数量
   */
  @Override
  public Map<String, Long> countAllPropsQuantity(Long activityId, Long userId) {
    return null;
  }
}
