package com.uxiangtech.activitybox.data.playways.drawing;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用户抽奖次数
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_drawing_count")
public class UserDrawingCountPO extends BasePO<UserDrawingCountPO> {

  @TableId
  private Long id;

  private Long activityId;

  private String userId;

  /**
   * 抽奖次数
   */
  private Long count;
}
