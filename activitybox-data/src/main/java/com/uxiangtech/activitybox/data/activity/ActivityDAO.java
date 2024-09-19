package com.uxiangtech.activitybox.data.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface ActivityDAO extends BaseMapper<ActivityPO> {

  ActivityPO getActivityById(@Param("id") Long id);

}
