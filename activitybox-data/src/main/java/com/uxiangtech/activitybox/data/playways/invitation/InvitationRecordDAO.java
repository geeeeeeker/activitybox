package com.uxiangtech.activitybox.data.playways.invitation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationRecordDAO extends BaseMapper<InvitationRecordPO> {

  /**
   * 查询受邀记录列表
   * @param inviteeId 受邀人
   * @param activityId 活动ID
   * @return
   */
  List<InvitationRecordPO> findByInviteeIdAndActivityId(@Param("inviteeId") Long inviteeId, @Param("activityId") Long activityId);

  /**
   * 查询邀请记录列表
   * @param inviterId 邀请人
   * @param activityId 活动ID
   * @return
   */
  List<InvitationRecordPO> findAllByInviterIdAndActivityId(@Param("inviterId") Long inviterId, @Param("activityId") Long activityId);
}
