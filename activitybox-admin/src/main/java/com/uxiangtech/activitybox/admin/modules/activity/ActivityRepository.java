package com.uxiangtech.activitybox.admin.modules.activity;

public interface ActivityRepository {

  /**
   * 查询
   * @param id
   * @return
   */
  ActivityPO getActivity(final Long id);
}
