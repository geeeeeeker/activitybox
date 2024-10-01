package com.uxiangtech.activitybox.engine.modules.playway.drawing;

public interface DrawingService {

  /**
   * 获取抽奖次数
   * @return
   */
  Long getDrawableCount(Long activityId, Long userId);

  /**
   * 增加抽奖次数
   * @param activityId
   * @param userId
   * @param deltaIncr
   * @return
   */
  boolean incrDrawableCount(Long activityId, Long userId, Long deltaIncr);

  /**
   * 消耗抽奖次数
   * @param activityId
   * @param userId
   * @param deltaIncr
   * @return
   */
  boolean decrDrawableCount(Long activityId, Long userId, Long deltaIncr);
}
