package com.uxiangtech.activitybox.engine;

import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playways.invitation.AbstractInvitationStdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayConf;

public class HelloWorldStdPlayway extends AbstractInvitationStdPlayway {
  @Override
  public String getId() {
    return "world";
  }

  @Override
  public String getName() {
    return "测试玩法";
  }

  @Override
  public void initConf(InvitationStdPlaywayConf conf) {

  }

  @Override
  public String makeCode(ActionCallContext ctx, InvitationStdPlaywayApi api) {
    return "abc";
  }

  @Override
  public void acceptInvitation(ActionCallContext ctx, InvitationStdPlaywayApi api, String code) {
    System.out.println(code);
  }
}
