package com.uxiangtech.activitybox.engine.modules.sdkimpl.props;

import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.PropsAttribute;
import com.uxiangtech.activitybox.sdk.award.Award;
import com.uxiangtech.activitybox.sdk.props.Props;

public class PropsImpl implements Props {

  private final String id;

  private final String name;

  private final String type;

  private final Activity activity;

  public PropsImpl(String id, String name, String type, Activity activity) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.activity = activity;
  }

  public PropsImpl(PropsAttribute attribute, Activity activity) {
    this(attribute.getId(), attribute.getName(), attribute.getType(), activity);
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
  public String getType() {
    return this.type;
  }

  @Override
  public Activity getActivity() {
    return null;
  }

  @Override
  public Award getAward() {
    return null;
  }
}
