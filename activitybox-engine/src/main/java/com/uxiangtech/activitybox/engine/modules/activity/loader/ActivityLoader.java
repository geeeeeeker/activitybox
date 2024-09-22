package com.uxiangtech.activitybox.engine.modules.activity.loader;

import java.util.Map;

public interface ActivityLoader {
  /**
   * 加载单个活动
   * @param id
   * @return
   */
  Activity load(final Long id);

  /**
   * 加载所有活动
   * @return
   */
  Map<Long, Activity> loadAll();

}
