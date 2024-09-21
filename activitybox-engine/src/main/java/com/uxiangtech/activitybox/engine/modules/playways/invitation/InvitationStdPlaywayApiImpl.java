package com.uxiangtech.activitybox.engine.modules.playways.invitation;

import com.uxiangtech.activitybox.engine.support.ActionCallContextHolder;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标准邀请玩法API实现
 */
@Component
public class InvitationStdPlaywayApiImpl implements InvitationStdPlaywayApi {

  @Resource
  private InvitationService invitationService;

  @Override
  public String getMyCode() {
    final ActionCallContext context = ActionCallContextHolder.getInstance().getContext();
    final Long activityId = context.getActivityId();
    final Long userId = context.getUserId();
    return this.invitationService.makeCode(activityId, userId);
  }

  @Override
  public String getMyCode(int codeLength) {
    final ActionCallContext context = ActionCallContextHolder.getInstance().getContext();
    final Long activityId = context.getActivityId();
    final Long userId = context.getUserId();
    return this.invitationService.makeCode(activityId, userId, codeLength);
  }

  @Override
  public Long getInviterByCode(final String code) {
    final ActionCallContext context = ActionCallContextHolder.getInstance().getContext();
    final Long activityId = context.getActivityId();
    return this.invitationService.getInviterByCode(activityId, code);
  }

  @Override
  public List<Long> getInviteesByCode(final String code) {
    final ActionCallContext context = ActionCallContextHolder.getInstance().getContext();
    final Long activityId = context.getActivityId();
    return this.invitationService.getInviteeByCode(activityId, code);
  }
}
