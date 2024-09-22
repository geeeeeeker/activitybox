package com.uxiangtech.activitybox.sdk.playway.invitation;

/**
 * 邀请标准玩法抽象实现
 */
public abstract class AbstractInvitationStdPlayway implements InvitationStdPlayway {

  @Override
  public String getId() {
    return "invitation";
  }

  @Override
  public String getName() {
    return "邀请玩法";
  }
}
