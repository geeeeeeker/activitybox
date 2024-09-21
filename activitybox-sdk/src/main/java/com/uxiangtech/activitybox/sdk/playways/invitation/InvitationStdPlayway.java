package com.uxiangtech.activitybox.sdk.playways.invitation;

import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;

/**
 * 邀请标准玩法接口
 */
public interface InvitationStdPlayway extends StdPlayway {

  void initConf(InvitationStdPlaywayConf conf);

  /**
   * 生成邀请码
   * @param ctx
   * @return
   */
  String makeCode(final ActionCallContext ctx, final InvitationStdPlaywayApi api);

  /**
   * 接受邀请
   * @param ctx
   */
  void acceptInvitation(final ActionCallContext ctx, final InvitationStdPlaywayApi api, final String code);

}
