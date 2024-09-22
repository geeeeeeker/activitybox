package com.uxiangtech.activitybox.data.tenant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface TenantDAO extends BaseMapper<TenantPO> {
  /**
   * 根据租户ID查询租户信息
   * @param id 租户ID
   * @return 租户信息
   */
  TenantPO findById(@Param("id") Long id);
}
