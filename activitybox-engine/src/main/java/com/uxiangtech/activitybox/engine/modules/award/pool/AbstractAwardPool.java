package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.award.Award;
import com.uxiangtech.activitybox.engine.modules.award.option.AwardOption;
import com.uxiangtech.activitybox.engine.modules.award.option.AwardOptionImpl;
import com.uxiangtech.activitybox.sdk.attribute.AwardOptionAttribute;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;

import java.util.List;
import java.util.Map;

public abstract class AbstractAwardPool implements AwardPool {

  private final String id;
  private final String name;
  private final Activity activity;
  private Map<String, AwardOption> awardOptionMap;

  public AbstractAwardPool(final AwardPoolAttribute attribute, final Activity activity) {
    this.id = attribute.getId();
    this.name = attribute.getName();
    this.activity = activity;
    this.buildOptions(attribute, activity);
  }

  private void buildOptions(final AwardPoolAttribute attribute, final Activity activity) {
    final List<AwardOptionAttribute> awardOptionAttributes = attribute.getOptions();
    for (AwardOptionAttribute awardOptionAttribute : awardOptionAttributes) {
      // 奖项对应的奖品ID
      final String awardId = awardOptionAttribute.getAwardId();
      final Award award = activity.getAwardMap().get(awardId);
      // 构建奖项
      final AwardOption awardOption =
        new AwardOptionImpl(awardOptionAttribute, award, this);
      this.awardOptionMap.put(awardOption.getId(), awardOption);
    }
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Activity getActivity() {
    return this.activity;
  }

  @Override
  public Map<String, AwardOption> getAwardOptionMap() {
    return this.awardOptionMap;
  }
}
