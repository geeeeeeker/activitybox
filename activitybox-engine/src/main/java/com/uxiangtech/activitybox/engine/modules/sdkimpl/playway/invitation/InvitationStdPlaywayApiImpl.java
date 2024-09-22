package com.uxiangtech.activitybox.engine.modules.sdkimpl.playway.invitation;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.modules.playway.invitation.InvitationService;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.api.ApiImpl;
import com.uxiangtech.activitybox.sdk.playway.invitation.InvitationStdPlaywayApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 标准邀请玩法API实现
 */
@Component
public class InvitationStdPlaywayApiImpl extends ApiImpl implements InvitationStdPlaywayApi {

  private final InvitationService invitationService;

  public InvitationStdPlaywayApiImpl() {
    this.invitationService = SpringBeanHolder.getBean(InvitationService.class);
  }

  @Override
  public String getMyCode() {
    return
      this.invitationService.makeCode(
        getContext().getActivityId(), getContext().getUserId());
  }

  @Override
  public String getMyCode(int codeLength) {
    return
      this.invitationService.makeCode(
        getContext().getActivityId(), getContext().getUserId(), codeLength);
  }

  @Override
  public Long getInviterByCode(final String code) {
    return
      this.invitationService.getInviterByCode(
        getContext().getActivityId(), code);
  }

  @Override
  public List<Long> getInviteesByCode(final String code) {
    return
      this.invitationService.getInviteeByCode(
        getContext().getActivityId(), code);
  }
}
