package com.uxiangtech.activitybox.data.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserDAO extends BaseMapper<UserPO> {

  UserPO findByOutUserIdAndTenantId(@Param("outUserId") String outUserId, @Param("tenantId") Long tenantId);
}
