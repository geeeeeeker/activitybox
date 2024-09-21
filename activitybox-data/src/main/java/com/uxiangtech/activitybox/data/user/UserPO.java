package com.uxiangtech.activitybox.data.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class UserPO {

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
