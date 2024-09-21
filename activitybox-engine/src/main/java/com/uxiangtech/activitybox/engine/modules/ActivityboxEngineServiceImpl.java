package com.uxiangtech.activitybox.engine.modules;

import com.uxiangtech.activitybox.engine.modules.actions.Action;
import com.uxiangtech.activitybox.engine.modules.activity.ActionCallContextImpl;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.engine.modules.activity.registry.ActivityRegistry;
import com.uxiangtech.activitybox.engine.modules.page.Page;
import com.uxiangtech.activitybox.engine.support.ActionCallContextHolder;
import com.uxiangtech.activitybox.sdk.action.ActionNotFoundException;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playways.PlaywayNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ActivityboxEngineServiceImpl implements ActivityboxEngineService {

  /**
   * 调用动作
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @param request
   * @return
   */
  @Override
  public Object callAction(Long activityId, String playwayId, String actionId, HttpServletRequest request) {

    final Action action =
      ActivityRegistry.getInstance()
        .load(activityId)
        .getPlaywayOrThrow(playwayId,
          () -> new PlaywayNotFoundException())
        .getActionOrThrow(actionId,
          () -> new ActionNotFoundException());

    // 活动开始时间和结束时间检查

    //构建用户请求上下文
    final ActionCallContext actionCallContext =
      new ActionCallContextImpl(activityId, playwayId, actionId, request);
    ActionCallContextHolder.getInstance().setContext(actionCallContext);

    return action.execute(actionCallContext);

  }

  /**
   * 获取页面代码
   * @param activityId 活动ID
   * @param pageId     页面ID
   * @return
   */
  @Override
  public String getPage(Long activityId, String pageId) {
    final Activity activity =
      ActivityRegistry.getInstance()
        .load(activityId);
    final Page page =
      activity.getPageOrDegradeToNullPage(pageId);
    return page.getCode();
  }
}
