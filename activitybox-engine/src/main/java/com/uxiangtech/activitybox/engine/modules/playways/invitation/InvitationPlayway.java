package com.uxiangtech.activitybox.engine.modules.playways.invitation;

import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;

public interface InvitationPlayway extends Playway<InvitationPlayway> {

  InvitationService getInvitationService();

  InvitationStdPlaywayApi getInvitationStdPlaywayApi();

}
