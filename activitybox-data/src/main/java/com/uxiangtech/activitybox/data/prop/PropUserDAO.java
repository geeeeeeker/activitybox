package com.uxiangtech.activitybox.data.prop;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface PropUserDAO extends BaseMapper<PropUserPO> {

  boolean insertOrAddQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propId") String propId, @Param("quantity") Long quantity);

  boolean subQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propId") String propId, @Param("quantity") Long quantity);

}
