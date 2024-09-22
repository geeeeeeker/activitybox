package com.uxiangtech.activitybox.data.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class UserPO extends BasePO<UserPO> {

  @TableId
  private Long id;

  /**
   * 租户ID
   */
  private Long tenantId;

  /**
   * 外部用户ID
   */
  private String outUserId;

  /**
   * 外部用户名
   */
  private String outUsername;

  /**
   * 删除标记
   */
  private Boolean isDeleted;

  /**
   * 创建时间
   */
  private String gmtCreate;

  /**
   * 修改时间
   */
  private String gmtModified;
}
