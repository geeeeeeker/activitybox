package com.uxiangtech.activitybox.engine.modules.actions.invitation;

import com.uxiangtech.activitybox.engine.modules.actions.AbstractAction;
import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.sdk.context.UserRequestContext;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;

public class InvitationMakeCodeAction extends AbstractAction {

  private InvitationStdPlaywayApi invitationStdPlaywayApi;

  public InvitationMakeCodeAction(String id, String name, Playway playway, InvitationStdPlaywayApi invitationStdPlaywayApi) {
    super(id, name, playway);
    this.invitationStdPlaywayApi = invitationStdPlaywayApi;
  }

  @Override
  protected Object doExecute(UserRequestContext context) {
    final InvitationStdPlayway invitationStdPlayway = (InvitationStdPlayway) this.getPlayway().getStdPlaywayInstance();
    return invitationStdPlayway.makeCode(context, this.invitationStdPlaywayApi);
  }
}
