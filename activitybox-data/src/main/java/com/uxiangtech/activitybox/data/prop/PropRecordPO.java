package com.uxiangtech.activitybox.data.prop;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 道具记录
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_prop_record")
public class PropRecordPO extends BasePO<PropRecordPO> {

  @TableId
  private Long id;

  /**
   * 活动ID
   */
  private Long activityId;

  /**
   * 玩法ID
   */
  private String playwayId;

  /**
   * 动作ID
   */
  private String actionId;

  /**
   * 用户ID
   */
  private Long userId;

  /**
   * 道具ID
   */
  private String propId;

  /**
   * 道具数量
   */
  private Long quantity;

  /**
   * 变更类型
   */
  private String deltaType;

  public enum DeltaType {
    ADD, SUB
  }

}
