package com.uxiangtech.activitybox.admin.modules.activity;

import com.uxiangtech.activitybox.admin.support.Resp;
import com.uxiangtech.activitybox.sdk.Activity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("/activity")
public class ActivityController {

  @Resource
  private ActivityService activityService;


  @RequestMapping("/{activity-id}/actions/{action-id}")
  public Resp execute(@PathVariable("activity-id") Long activityId, @PathVariable("action-id") String actionId) {

    final Activity activity =
      ActivityMgr.getInstance()
        .getActivity(activityId);

    if (null == activity) {
      return Resp.error("当前活动不存在");
    }




    return null;
  }
}
