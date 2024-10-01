package com.uxiangtech.activitybox.engine.modules.playway.drawing;

import com.uxiangtech.activitybox.data.playways.drawing.UserDrawingCountDAO;
import com.uxiangtech.activitybox.data.playways.drawing.UserDrawingCountPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DrawingServiceImpl implements DrawingService {

  @Resource
  private UserDrawingCountDAO userDrawingCountDAO;

  /**
   * 获取可抽奖次数
   * @param activityId
   * @param userId
   * @return
   */
  @Override
  public Long getDrawableCount(Long activityId, Long userId) {
    final UserDrawingCountPO userDrawingCountPO =
      this.userDrawingCountDAO.findByActivityIdAndUserId(activityId, userId);
    if (null == userDrawingCountPO) {
      return 0L;
    } else {
      return userDrawingCountPO.getCount();
    }
  }

  @Override
  public boolean incrDrawableCount(Long activityId, Long userId, Long deltaIncr) {
    final int affectedRows =
      this.userDrawingCountDAO.createOrIncrCount(
        activityId, userId, deltaIncr);
    return affectedRows > 0;
  }

  @Override
  public boolean decrDrawableCount(Long activityId, Long userId, Long deltaIncr) {
    final int affectedRows =
      this.userDrawingCountDAO.decrCount(
        activityId, userId, deltaIncr);
    return affectedRows > 0;
  }
}
