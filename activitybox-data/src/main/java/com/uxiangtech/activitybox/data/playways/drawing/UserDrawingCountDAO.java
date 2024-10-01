package com.uxiangtech.activitybox.data.playways.drawing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserDrawingCountDAO extends BaseMapper<UserDrawingCountPO>  {

  UserDrawingCountPO findByActivityIdAndUserId(@Param("activityId") Long activityId, @Param("userId") Long userId);

  int createOrIncrCount(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("delta") Long delta);

  int decrCount(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("delta") Long delta);

}
