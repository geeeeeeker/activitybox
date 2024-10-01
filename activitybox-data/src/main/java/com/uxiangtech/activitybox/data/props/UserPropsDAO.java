package com.uxiangtech.activitybox.data.props;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPropsDAO extends BaseMapper<UserPropsPO> {

  int insertOrAddQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propsId") String propsId, @Param("quantity") Long quantity);

  int subQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propsId") String propsId, @Param("quantity") Long quantity);

  Long countPropQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propsId") String propsId);

  List<PropsIdQuantity> countPropsQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId, @Param("propsIds") List<String> propsIds);

  List<PropsIdQuantity> countAllPropsQuantity(@Param("activityId") Long activityId, @Param("userId") Long userId);
}
