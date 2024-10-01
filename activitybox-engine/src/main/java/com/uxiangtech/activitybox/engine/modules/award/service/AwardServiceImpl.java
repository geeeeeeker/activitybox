package com.uxiangtech.activitybox.engine.modules.award.service;

import com.uxiangtech.activitybox.data.playways.drawing.DrawingRecordDAO;
import com.uxiangtech.activitybox.data.playways.drawing.DrawingRecordPO;
import com.uxiangtech.activitybox.data.award.AwardOptionStockDAO;
import com.uxiangtech.activitybox.data.award.AwardOptionStockPO;
import com.uxiangtech.activitybox.data.playways.drawing.UserDrawingRecordDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AwardServiceImpl implements AwardService {

  @Resource
  private AwardOptionStockDAO awardOptionStockDAO;

  @Resource
  private DrawingRecordDAO drawingRecordDAO;


  @Resource
  private UserDrawingRecordDAO userDrawingRecordDAO;

  @Override
  public boolean grantAwardOption(Long activityId, String playwayId, String actionId, Long userId, String awardPoolId, Long awardId, String awardOptionId, Long totalStock, Long quantity) {

    // 如果本次消耗库存大于总库存，直接返回库存不足
    if (quantity > totalStock) {
      return false;
    }

    AwardOptionStockPO awardOptionStockPO =
      this.awardOptionStockDAO.findByActivityIdAndAwardPoolIdAndAwardOptionId(activityId, awardPoolId, awardOptionId);
    // 初始化库存记录
    if (null == awardOptionStockPO) {
      awardOptionStockPO =
        AwardOptionStockPO.builder()
          .activityId(activityId)
          .awardPoolId(awardPoolId)
          .awardId(awardId)
          .awardOptionId(awardOptionId)
          .usedStock(quantity)
          .totalStock(totalStock)
          .build()
          .markAsInitialized();
      this.awardOptionStockDAO.insert(awardOptionStockPO);
    }

    // 减扣库存
    final int affectedRows =
      this.awardOptionStockDAO.useStock(
        activityId, awardPoolId, awardOptionId, quantity);

    boolean isStockUsedSuccess = affectedRows > 0;

    // 增加减扣记录
    if (isStockUsedSuccess) {
      final DrawingRecordPO drawingRecordPO =
        DrawingRecordPO.builder()
          .activityId(activityId)
          .playwayId(playwayId)
          .actionId(actionId)
          .awardId(awardId)
          .awardPoolId(awardPoolId)
          .awardOptionId(awardOptionId)
          .build()
          .markAsInitialized();
      this.drawingRecordDAO.insert(drawingRecordPO);
    }

    return isStockUsedSuccess;
  }

  @Override
  public Long countUnusedAwardOptionStock(Long activityId, String awardPoolId, String awardOptionId, Long totalStock) {
    AwardOptionStockPO awardOptionStockPO =
      this.awardOptionStockDAO.findByActivityIdAndAwardPoolIdAndAwardOptionId(activityId, awardPoolId, awardOptionId);
    if (null == awardOptionStockPO) {
      return totalStock;
    } else {
      return totalStock - awardOptionStockPO.getUsedStock();
    }
  }

}
