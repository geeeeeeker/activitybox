package com.uxiangtech.activitybox.data.props;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 我的道具
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_props")
public class UserPropsPO extends BasePO<UserPropsPO> {

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
  private String propsId;

  /**
   * 道具数量
   */
  private Long quantity;
}
