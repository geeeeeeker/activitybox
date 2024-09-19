package com.uxiangtech.activitybox.engine.modules.playways.invitation;

import com.uxiangtech.activitybox.engine.modules.actions.Action;
import com.uxiangtech.activitybox.engine.modules.actions.invitation.InvitationAcceptInvitationAction;
import com.uxiangtech.activitybox.engine.modules.actions.invitation.InvitationMakeCodeAction;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.playways.AbstractPlayway;
import com.uxiangtech.activitybox.engine.support.SpringBeanHolder;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;

import java.util.HashMap;
import java.util.Map;

/**
 * 标准邀请玩法代码包装
 */
public final class InvitationPlaywayImpl extends AbstractPlayway<InvitationPlayway> implements InvitationPlayway {

  private final InvitationService invitationService;

  private final InvitationStdPlaywayApi invitationStdPlaywayApi;

  private final InvitationStdPlayway invitationStdPlayway;


  public InvitationPlaywayImpl(final String playwayId, final String playwayName, InvitationStdPlayway invitationStdPlayway, Activity activity, ClassLoader classLoader) {
    super(playwayId, playwayName, activity, classLoader);

    // 初始化配置信息
    this.invitationStdPlayway = invitationStdPlayway;
    this.invitationService = SpringBeanHolder.getBean(InvitationService.class);
    this.invitationStdPlaywayApi = SpringBeanHolder.getBean(InvitationStdPlaywayApi.class);
  }

  @Override
  public Object getStdPlaywayInstance() {
    return this.invitationStdPlayway;
  }

  @Override
  public Map<String, Action> getActionMap() {

    final Action makeCodeAction =
      new InvitationMakeCodeAction(
        "gencode", "生成邀请码", this, this.invitationStdPlaywayApi);

    final Action acceptInvitationAction =
      new InvitationAcceptInvitationAction(
        "accept", "接受邀请", this, this.invitationStdPlaywayApi);

    final Map<String, Action> actionMap = new HashMap<>();
    actionMap.put(makeCodeAction.getId(), makeCodeAction);
    actionMap.put(acceptInvitationAction.getId(), acceptInvitationAction);
    return actionMap;
  }


  @Override
  public InvitationService getInvitationService() {
    return this.invitationService;
  }

  @Override
  public InvitationStdPlaywayApi getInvitationStdPlaywayApi() {
    return this.invitationStdPlaywayApi;
  }
}
