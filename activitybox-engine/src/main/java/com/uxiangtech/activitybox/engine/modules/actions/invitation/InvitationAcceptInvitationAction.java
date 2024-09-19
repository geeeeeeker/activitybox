package com.uxiangtech.activitybox.engine.modules.actions.invitation;

import com.uxiangtech.activitybox.engine.modules.actions.AbstractAction;
import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.sdk.context.UserRequestContext;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;

public class InvitationAcceptInvitationAction extends AbstractAction {

  private InvitationStdPlaywayApi invitationStdPlaywayApi;

  public InvitationAcceptInvitationAction(String id, String name, Playway playway, InvitationStdPlaywayApi invitationStdPlaywayApi) {
    super(id, name, playway);
    this.invitationStdPlaywayApi = invitationStdPlaywayApi;
  }

  @Override
  protected Object doExecute(UserRequestContext context) {

    // 邀请码
    final String code = context.getRequestParam("code");

    final InvitationStdPlayway invitationStdPlayway = (InvitationStdPlayway) this.getPlayway().getStdPlaywayInstance();
    invitationStdPlayway.acceptInvitation(context, this.invitationStdPlaywayApi, code);

    return null;
  }
}
