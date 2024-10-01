package com.uxiangtech.activitybox.data.award;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 奖品库存
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_award_stock")
public class AwardStockPO extends BasePO<AwardStockPO> {
  @TableId
  private Long id;

  /**
   * 活动ID
   */
  private Long activityId;

  /**
   * 关联奖品ID
   */
  private Long awardId;

  /**
   * 已消耗库存
   */
  private Long usedStock;

  /**
   * 配置总库存
   */
  private Long totalStock;
}
