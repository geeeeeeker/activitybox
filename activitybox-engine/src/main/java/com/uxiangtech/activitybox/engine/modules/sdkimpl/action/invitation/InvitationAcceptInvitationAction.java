package com.uxiangtech.activitybox.engine.modules.sdkimpl.action.invitation;

import com.uxiangtech.activitybox.engine.modules.sdkimpl.action.AbstractAction;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playway.invitation.InvitationStdPlayway;
import com.uxiangtech.activitybox.sdk.playway.invitation.InvitationStdPlaywayApi;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

public class InvitationAcceptInvitationAction extends AbstractAction {

  private InvitationStdPlaywayApi invitationStdPlaywayApi;

  public InvitationAcceptInvitationAction(String id, String name, Playway playway, InvitationStdPlaywayApi invitationStdPlaywayApi) {
    super(id, name, playway);
    this.invitationStdPlaywayApi = invitationStdPlaywayApi;
  }

  @Override
  protected Object doExecute(ActionCallContext context) {

    // 邀请码
    final String code = context.getRequestParam("code");
    if (StringUtils.isBlank(code)) {
      throw new RuntimeException("邀请码不存在");
    }

    // 校验邀请码
    invitationStdPlaywayApi.getInviterByCode(code);

    final InvitationStdPlayway invitationStdPlayway = (InvitationStdPlayway) this.getPlayway().getStdPlaywayInstance();
    invitationStdPlayway.acceptInvitation(context, this.invitationStdPlaywayApi, code);

    return null;
  }

  @Override
  public Method getExecutableMethod() throws NoSuchMethodException {
    Object stdPlaywayInstance = this.getPlayway().getStdPlaywayInstance();
    return stdPlaywayInstance.getClass().getDeclaredMethod(
      "acceptInvitation", ActionCallContext.class, InvitationStdPlaywayApi.class, String.class);
  }
}
