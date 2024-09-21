package com.uxiangtech.activitybox.data.my;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.Data;

/**
 * 我的道具
 */
@Data
@TableName("t_my_prop")
public class MyPropPO extends BasePO<MyPropPO> {

  @TableId
  private Long id;

  /**
   * 活动ID
   */
  private Long activityId;

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
}
