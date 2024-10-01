package com.uxiangtech.activitybox.data.award;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 奖项库存
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_award_option_stock")
public class AwardOptionStockPO extends BasePO<AwardOptionStockPO> {
  @TableId
  private Long id;

  /**
   * 活动ID
   */
  private Long activityId;

  /**
   * 关联奖池ID
   */
  private String awardPoolId;

  /**
   * 奖项ID
   */
  private String awardOptionId;

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
