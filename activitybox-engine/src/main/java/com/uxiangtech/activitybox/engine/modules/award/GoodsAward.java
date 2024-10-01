package com.uxiangtech.activitybox.engine.modules.award;

import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.AbstractAward;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.AwardAttribute;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;

/**
 * 商品奖品，涉及到远程调用商品中心进行发奖
 * <p>
 * 1. 虚拟商品：积分、优惠券、代金券、现金红包等
 * 2. 实物商品：涉及电商履约流程
 */
public class GoodsAward extends AbstractAward {

  public GoodsAward(String id, String name, String icon, Long stock, String refId, Activity activity) {
    super(id, name, Type.GOODS.name(), icon, stock, refId, activity);
  }

  public GoodsAward(AwardAttribute attribute, Activity activity) {
    this(attribute.getId(), attribute.getName(), attribute.getIcon(), attribute.getStock(), attribute.getRefId(), activity);
  }

  @Override
  public Object execute(AwardDrawingContext context) {

    // TODO 调用商品中心或权益中心接口，如果对接了其他平台则调用该平台接口权益发放接口

    return null;
  }
}
