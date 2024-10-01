package com.uxiangtech.activitybox.engine.modules.props;

import com.uxiangtech.activitybox.data.props.PropsIdQuantity;
import com.uxiangtech.activitybox.data.props.PropsRecordDAO;
import com.uxiangtech.activitybox.data.props.PropsRecordPO;
import com.uxiangtech.activitybox.data.props.UserPropsDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PropsServiceImpl implements PropsService {

  private final UserPropsDAO userPropsDAO;
  private final PropsRecordDAO propsRecordDAO;
  private final TransactionTemplate transactionTemplate;

  public PropsServiceImpl(UserPropsDAO userPropsDAO, PropsRecordDAO propsRecordDAO, TransactionTemplate transactionTemplate) {
    this.userPropsDAO = userPropsDAO;
    this.propsRecordDAO = propsRecordDAO;
    this.transactionTemplate = transactionTemplate;
  }

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
  @Override
  public boolean grantProps(Long activityId, String playwayId, String actionId, Long userId, String propsId, Long quantity) {

    if (quantity <= 0) {
      return false;
    }

    this.userPropsDAO.insertOrAddQuantity(activityId, userId, propsId, quantity);

    final PropsRecordPO propsRecordPO =
      PropsRecordPO.builder()
        .activityId(activityId)
        .playwayId(playwayId)
        .actionId(actionId)
        .userId(userId)
        .propsId(propsId)
        .quantity(quantity)
        .deltaType(PropsRecordPO.DeltaType.ADD.name())
        .build()
        .markAsInitialized();
    this.propsRecordDAO.insert(propsRecordPO);

    return true;
  }

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
  @Override
  public boolean useProps(Long activityId, String playwayId, String actionId, Long userId, String propsId, Long quantity) {

    if (quantity <= 0) {
      return false;
    }

    final int affectedRows =
      this.userPropsDAO.subQuantity(
        activityId, userId, propsId, quantity);
    // 游戏道具不足，无法减扣
    if (affectedRows <= 0) {
      return false;
    }

    final PropsRecordPO propsRecordPO =
      PropsRecordPO.builder()
        .activityId(activityId)
        .playwayId(playwayId)
        .actionId(actionId)
        .userId(userId)
        .propsId(propsId)
        .quantity(quantity)
        .deltaType(PropsRecordPO.DeltaType.SUB.name())
        .build()
        .markAsInitialized();
    this.propsRecordDAO.insert(propsRecordPO);

    return true;
  }

  /**
   * 统计游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @param propsId    游戏道具ID
   * @return 游戏道具数量
   */
  @Override
  public Long countPropQuantity(Long activityId, Long userId, String propsId) {
    return this.userPropsDAO.countPropQuantity(activityId, userId, propsId);
  }

  /**
   * 批量统计游戏道具数量
   *
   * @param activityId 活动ID
   * @param userId     用户ID
   * @param propsIds   游戏道具ID列表
   * @return 游戏道具数量
   */
  @Override
  public Map<String, Long> countMultiPropsQuantity(Long activityId, Long userId, List<String> propsIds) {
    return
      this.userPropsDAO.countPropsQuantity(activityId, userId, propsIds)
        .stream().collect(Collectors.toMap(PropsIdQuantity::getPropsId, PropsIdQuantity::getQuantity));
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
    return
      this.userPropsDAO.countAllPropsQuantity(activityId, userId)
        .stream().collect(Collectors.toMap(PropsIdQuantity::getPropsId, PropsIdQuantity::getQuantity));
  }
}
