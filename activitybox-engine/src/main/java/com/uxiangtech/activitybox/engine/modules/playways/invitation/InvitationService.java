package com.uxiangtech.activitybox.engine.modules.playways.invitation;

import java.util.List;

public interface InvitationService {

  String makeCode(final Long activityId, final Long userId);

  String makeCode(final Long activityId, final Long userId, final int codeLength);

  Long getInviterByCode(final Long activityId, final String code);

  List<Long> getInviteeByCode(final Long activityId, final String code);

}
