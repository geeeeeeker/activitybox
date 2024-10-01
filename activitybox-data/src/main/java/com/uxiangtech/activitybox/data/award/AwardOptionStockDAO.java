package com.uxiangtech.activitybox.data.award;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 奖项库存DAO
 */
public interface AwardOptionStockDAO extends BaseMapper<AwardOptionStockPO> {

  AwardOptionStockPO findByActivityIdAndAwardPoolIdAndAwardOptionId(@Param("activityId") Long activityId, @Param("awardPoolId") String awardPoolId, @Param("awardOptionId") String awardOptionId);

  int useStock(@Param("activityId") Long activityId, @Param("awardPoolId") String awardPoolId, @Param("awardOptionId") String awardOptionId, @Param("quantity") Long quantity);

}
