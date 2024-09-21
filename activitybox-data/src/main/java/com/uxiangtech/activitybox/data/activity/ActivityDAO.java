package com.uxiangtech.activitybox.data.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityDAO extends BaseMapper<ActivityPO> {

  /**
   * 根据活动ID查询活动信息
   * @param id
   * @return
   */
  ActivityPO findActivityById(@Param("id") Long id);

  /**
   * 查询所有上线活动
   * @return
   */
  List<ActivityPO> findAllOnlineActivities();

  /**
   * 查询所有修改过的活动，可能已有上线，下线，删除等操作
   * @return
   */
  List<ActivityPO> findAllModifiedActivities(@Param("lastLoadTime")LocalDateTime lastLoadTime);
}
