package com.uxiangtech.activitybox.data.award;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 奖品库存DAO
 */
public interface AwardStockDAO extends BaseMapper<AwardStockPO> {

  AwardStockPO findByActivityIdAndAwardId(@Param("activityId") Long activityId, @Param("awardId") String awardId);

  int useStock(@Param("activityId") Long activityId, @Param("awardId") String awardId, @Param("quantity") Long quantity);

}
