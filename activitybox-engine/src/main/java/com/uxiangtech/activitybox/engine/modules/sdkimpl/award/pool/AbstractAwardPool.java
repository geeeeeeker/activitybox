package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool;

import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option.AwardOptionImpl;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.Award;
import com.uxiangtech.activitybox.sdk.award.AwardOption;
import com.uxiangtech.activitybox.sdk.award.AwardPool;
import com.uxiangtech.activitybox.sdk.attribute.AwardOptionAttribute;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractAwardPool implements AwardPool {

  private final String id;
  private final String name;
  private final Activity activity;
  private Map<String, AwardOption> awardOptionMap;  // 冗余存储，便于取值
  private List<AwardOption> awardOptions;  // 用于页面展示及概率抽奖概率累加

  public AbstractAwardPool(final AwardPoolAttribute attribute, final Activity activity) {
    this.id = attribute.getId();
    this.name = attribute.getName();
    this.activity = activity;
    this.awardOptionMap = new HashMap<>();
    this.awardOptions = new ArrayList<>();
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
      this.awardOptions.add(awardOption);
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

  @Override
  public List<AwardOption> getAwardOptions() {
    return this.awardOptions;
  }
}
