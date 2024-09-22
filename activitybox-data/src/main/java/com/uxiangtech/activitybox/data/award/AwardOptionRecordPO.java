package com.uxiangtech.activitybox.data.award;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 奖项
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_award_option_record")
public class AwardOptionRecordPO extends BasePO<AwardRecordPO> {

  @TableId
  private Long id;

  /**
   * 活动ID
   */
  private Long activityId;

  /**
   * 奖品ID
   */
  private String awardId;

  /**
   * 奖池ID
   */
  private String awardPoolId;

  /**
   * 已使用的库存
   */
  private Long usedStock;

  /**
   * 总限制的库存
   */
  private Long totalStock;
}
