package com.uxiangtech.activitybox.sdk.playway.invitation;

import java.util.List;

/**
 * 邀请标准玩法工具集
 */
public interface InvitationStdPlaywayApi {

  /**
   * 查询我的邀请码，如果没有则自动生成
   *
   * @return 邀请码
   */
  String getMyCode();

  /**
   * 查询我的邀请码，如果没有则自动生成
   * @param codeLength 邀请码长度
   * @return 邀请码
   */
  String getMyCode(int codeLength);

  /**
   * 查询邀请码对应的邀请人ID
   * @return
   */
  Long getInviterByCode(final String code);

  /**
   * 查询邀请码对应的被邀请人ID列表
   * @return
   */
  List<Long> getInviteesByCode(final String code);


}
