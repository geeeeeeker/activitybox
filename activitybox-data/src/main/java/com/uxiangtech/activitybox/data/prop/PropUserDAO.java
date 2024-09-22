package com.uxiangtech.activitybox.data.prop;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PropUserDAO extends BaseMapper<PropUserPO> {

  int insertOrAddQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propId") String propId, @Param("quantity") Long quantity);

  int subQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propId") String propId, @Param("quantity") Long quantity);

  Long countPropQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propId") String propId);

  List<PropIdQuantity> countPropsQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propIds") List<String> propIds);

  List<PropIdQuantity> countAllPropsQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId);
}
