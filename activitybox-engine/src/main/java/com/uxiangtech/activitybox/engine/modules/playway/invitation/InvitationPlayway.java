package com.uxiangtech.activitybox.engine.modules.playway.invitation;

import com.uxiangtech.activitybox.engine.modules.playway.Playway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;

public interface InvitationPlayway extends Playway<InvitationPlayway> {

  InvitationService getInvitationService();

  InvitationStdPlaywayApi getInvitationStdPlaywayApi();

}
