package com.uxiangtech.activitybox.engine.modules;

import javax.servlet.http.HttpServletRequest;

public interface ActivityboxEngineService {

  /**
   * 调用活动动作
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @return
   */
  Object callAction(Long activityId, String playwayId, String actionId, HttpServletRequest request);

  /**
   * 获取前端页面
   *
   * @param activityId 活动ID
   * @param pageId     页面ID
   * @return
   */
  String getPage(Long activityId, String pageId);
}
