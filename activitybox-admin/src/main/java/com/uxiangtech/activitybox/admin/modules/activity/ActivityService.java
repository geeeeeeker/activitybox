package com.uxiangtech.activitybox.admin.modules.activity;

import java.time.LocalDateTime;

public interface ActivityService {

  LocalDateTime getModifiedTime(final Long id);
}
