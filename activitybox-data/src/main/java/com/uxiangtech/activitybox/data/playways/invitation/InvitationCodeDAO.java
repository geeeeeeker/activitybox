package com.uxiangtech.activitybox.data.playways.invitation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface InvitationCodeDAO extends BaseMapper<InvitationCodePO>  {

  /**
   * 查询活动邀请码
   * @param code 邀请码
   * @param activityId 活动ID
   * @return
   */
  InvitationCodePO findByInvitationCodeAndActivityId(@Param("code") String code, @Param("activityId") Long activityId);

  /**
   * 查询我的邀请码
   * @param userId
   * @param activityId
   * @return
   */
  InvitationCodePO findByUserIdAndActivityId(@Param("userId") Long userId, @Param("activityId") Long activityId);

}
