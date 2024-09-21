package com.uxiangtech.activitybox.engine.modules.actions.invitation;

import com.uxiangtech.activitybox.engine.modules.actions.AbstractAction;
import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;

import java.lang.reflect.Method;

public class InvitationMakeCodeAction extends AbstractAction {

  private InvitationStdPlaywayApi invitationStdPlaywayApi;

  public InvitationMakeCodeAction(String id, String name, Playway playway, InvitationStdPlaywayApi invitationStdPlaywayApi) {
    super(id, name, playway);
    this.invitationStdPlaywayApi = invitationStdPlaywayApi;
  }

  @Override
  protected Object doExecute(ActionCallContext context) {
    final InvitationStdPlayway invitationStdPlayway = (InvitationStdPlayway) this.getPlayway().getStdPlaywayInstance();
    return invitationStdPlayway.makeCode(context, this.invitationStdPlaywayApi);
  }

  @Override
  public Method getExecutableMethod() throws NoSuchMethodException {
    Object stdPlaywayInstance = this.getPlayway().getStdPlaywayInstance();

    return stdPlaywayInstance.getClass().getDeclaredMethod(
      "makeCode", ActionCallContext.class, InvitationStdPlaywayApi.class);
  }
}