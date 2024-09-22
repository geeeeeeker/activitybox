package com.uxiangtech.activitybox.data.tenant;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import com.uxiangtech.activitybox.data.user.UserPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_tenant")
public class TenantPO extends BasePO<TenantPO> {

  /**
   * 租户ID
   */
  @TableId
  private Long id;

  /**
   * 租户名称
   */
  private String name;

  // 扩展营业材料等认证信息

}
