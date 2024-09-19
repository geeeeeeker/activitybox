package com.uxiangtech.activitybox.admin.modules.activity;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class ActivityServiceImpl implements ActivityService {

  @Resource
  private ActivityRepository activityRepository;

  @Override
  public LocalDateTime getModifiedTime(final Long id) {

    final ActivityPO activityPO =
      this.activityRepository.getActivity(id);

    return activityPO.getGmtModified();

  }
}
