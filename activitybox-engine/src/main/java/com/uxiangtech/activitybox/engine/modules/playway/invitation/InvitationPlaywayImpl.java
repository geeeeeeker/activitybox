package com.uxiangtech.activitybox.engine.modules.playway.invitation;

import com.uxiangtech.activitybox.engine.modules.action.Action;
import com.uxiangtech.activitybox.engine.modules.action.invitation.InvitationAcceptInvitationAction;
import com.uxiangtech.activitybox.engine.modules.action.invitation.InvitationMakeCodeAction;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.playway.AbstractPlayway;
import com.uxiangtech.activitybox.engine.support.SpringBeanHolder;
import com.uxiangtech.activitybox.sdk.attribute.PlaywayAttribute;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;
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

  private Class<?> stdPlaywayClass;

  private Map<String, Action> actionMap;


  public InvitationPlaywayImpl(final String id, final String name, PlaywayAttribute attribute, InvitationStdPlayway invitationStdPlayway, Activity activity, ClassLoader classLoader) {
    super(id, name, attribute, activity, classLoader);

    // 初始化配置信息
    this.invitationStdPlayway = invitationStdPlayway;
    this.stdPlaywayClass = invitationStdPlayway.getClass();
    this.invitationService = SpringBeanHolder.getBean(InvitationService.class);
    this.invitationStdPlaywayApi = SpringBeanHolder.getBean(InvitationStdPlaywayApi.class);

    // 构建玩法包含的动作
    this.actionMap = this.buildActions();
  }

  private Map<String, Action> buildActions() {

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
  public Object getStdPlaywayInstance() {
    return this.invitationStdPlayway;
  }

  @Override
  public Class<? extends StdPlayway> getStdPlaywayClass() {
    return this.invitationStdPlayway.getClass();
  }

  @Override
  public void setStdPlaywayClass(Class<? extends StdPlayway> stdPlaywayClass) {
    this.stdPlaywayClass = stdPlaywayClass;
  }

  @Override
  public void setInitConfMethodName(String initConfMethodName) {

  }

  @Override
  public String getInitConfMethodName() {
    return null;
  }

  @Override
  public Map<String, Action> getActionMap() {
    return this.actionMap;
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
