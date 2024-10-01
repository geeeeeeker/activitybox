package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.modules.props.PropsService;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.AbstractAward;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.AwardAttribute;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;

/**
 * 道具奖品
 * <p>
 * 道具奖品是虚拟奖品，用于游戏道具等计数场景，没有奖品库存限制。
 */
public class PropsAward extends AbstractAward {

  private final PropsService propsService;

  /**
   * 道具奖品构造器
   *
   * @param id       奖品ID
   * @param name     奖品名称
   * @param icon     奖品图标
   * @param refId    道具ID
   * @param activity 活动实例
   */
  public PropsAward(String id, String name, String icon, String refId, Activity activity) {
    super(id, name, Type.PROPS.name(), icon, null, refId, activity);
    this.propsService = SpringBeanHolder.getBean(PropsService.class);
  }

  /**
   * 道具奖品构造器
   *
   * @param attribute 奖品配置属性
   * @param activity  活动实例
   */
  public PropsAward(AwardAttribute attribute, Activity activity) {
    this(attribute.getId(), attribute.getName(), attribute.getIcon(), attribute.getRefId(), activity);
  }

  @Override
  public Object execute(AwardDrawingContext context) {

    final String propsId = this.getRefId();

    this.propsService.grantProps(context.getActivityId(),
      context.getPlaywayId(), context.getActionId(), context.getUserId(), propsId, 1L);

    return null;
  }
}
