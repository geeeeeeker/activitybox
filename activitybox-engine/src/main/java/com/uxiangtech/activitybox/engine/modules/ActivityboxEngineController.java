package com.uxiangtech.activitybox.engine.modules;

import com.uxiangtech.activitybox.engine.support.R;
import com.uxiangtech.activitybox.engine.support.mvc.NoLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ActivityboxEngineController {

  @Resource
  private ActivityboxEngineService activityboxEngineService;

  /**
   * 调用活动接口
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @return
   */
  @PostMapping("/activity/{activity-id}/playway/{playway-id}/action/{action-id}.do")
  public R callAction(@PathVariable("activity-id") Long activityId,
                      @PathVariable("playway-id") String playwayId,
                      @PathVariable("action-id") String actionId,
                      HttpServletRequest request) {
    final Object invokeResult =
      this.activityboxEngineService
        .callAction(activityId, playwayId, actionId, request);
    return R.ok(invokeResult);
  }


  /**
   * 查询活动页面
   *
   * @param activityId 活动ID
   * @param pageId     页面ID
   * @return
   */
  @NoLogin
  @GetMapping("/activity/{activity-id}/page/{page-id}.html")
  public String getSkin(@PathVariable("activity-id") Long activityId, @PathVariable("page-id") String pageId) {
    return this.activityboxEngineService.getPage(activityId, pageId);
  }


}
