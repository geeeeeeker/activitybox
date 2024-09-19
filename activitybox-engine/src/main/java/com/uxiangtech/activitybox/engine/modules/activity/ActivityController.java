package com.uxiangtech.activitybox.engine.modules.activity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/activity")
public class ActivityController {

  @Resource
  private ActivityService activityService;

  /**
   * 业务接口调用
   * @param activityId
   * @param playwayId
   * @param actionId
   * @return
   */
  @PostMapping("/{activity-id}/playway/{playway-id}/action/{action-id}.do")
  public Object doAction(@PathVariable("activity-id") Long activityId,
                         @PathVariable("playway-id") String playwayId,
                         @PathVariable("action-id") String actionId) {
    return activityService.doAction(activityId, playwayId, actionId);
  }


  /**
   * 查询活动皮肤
   * @param activityId
   * @param skinId
   * @return
   */
  @GetMapping("/{activity-id}/skin/{skin-id}.html")
  public String getSkin(@PathVariable("activity-id") Long activityId, @PathVariable("skin-id") String skinId) {
    return activityService.getSkin(activityId, skinId);
  }


}
