package com.uxiangtech.activitybox.engine.modules.sdkimpl.playway.invitation;

import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.playway.invitation.InvitationStdPlaywayApi;

public interface InvitationPlayway extends Playway<InvitationPlayway> {

  InvitationService getInvitationService();

  InvitationStdPlaywayApi getInvitationStdPlaywayApi();

}
