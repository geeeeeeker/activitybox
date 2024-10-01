package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.modules.playway.drawing.DrawingService;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.AbstractAward;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.AwardAttribute;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;

/**
 * 抽奖机会奖品
 */
public class DrawingAward extends AbstractAward {

  private final DrawingService drawingService;

  public DrawingAward(String id, String name, String type, String icon, Long stock, String refId, Activity activity, DrawingService drawingService) {
    super(id, name, type, icon, stock, refId, activity);
    this.drawingService = drawingService;
  }

  public DrawingAward(String id, String name, String icon, Activity activity) {
    super(id, name, Type.PROPS.name(), icon, null, null, activity);
    this.drawingService = SpringBeanHolder.getBean(DrawingService.class);
  }

  /**
   * 抽奖机会奖品构造器
   *
   * @param attribute 奖品配置属性
   * @param activity  活动实例
   */
  public DrawingAward(AwardAttribute attribute, Activity activity) {
    this(attribute.getId(), attribute.getName(), attribute.getIcon(), activity);
  }

  @Override
  public Object execute(AwardDrawingContext context) {
    return null;
  }
}
