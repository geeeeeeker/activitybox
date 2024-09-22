package com.uxiangtech.activitybox.engine.modules;

import com.uxiangtech.activitybox.engine.modules.sdkimpl.context.ActionCallContextImpl;
import com.uxiangtech.activitybox.engine.modules.activity.registry.ActivityRegistry;
import com.uxiangtech.activitybox.sdk.action.Action;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.context.ActionCallContextHolder;
import com.uxiangtech.activitybox.sdk.context.UserContext;
import com.uxiangtech.activitybox.sdk.context.UserContextHolder;
import com.uxiangtech.activitybox.sdk.page.Page;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.action.ActionNotFoundException;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playway.PlaywayNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ActivityboxEngineServiceImpl implements ActivityboxEngineService {

  /**
   * 调用动作
   *
   * @param activityId 活动ID
   * @param playwayId  玩法ID
   * @param actionId   动作ID
   * @param request    Servlet请求对象
   * @return 动作调用结果
   */
  @Override
  public Object callAction(Long activityId, String playwayId, String actionId, HttpServletRequest request) {

    final Activity activity =
      ActivityRegistry.getInstance().load(activityId);
    final Playway<?> playway =
      activity.getPlaywayOrThrow(playwayId,
        () -> new PlaywayNotFoundException());
    final Action action =
      playway.getActionOrThrow(actionId,
        () -> new ActionNotFoundException());

    // 活动开始时间和结束时间检查

    //构建动作调用上下文
    final ActionCallContext context =
      new ActionCallContextImpl(activity, playway,
        action, UserContextHolder.getInstance().getContext(), request);
    final ActionCallContextHolder holder = ActionCallContextHolder.getInstance();

    holder.setContext(context);
    try {
      return action.execute(context);
    } finally {
      holder.removeContext();
    }

  }

  /**
   * 获取页面代码
   *
   * @param activityId 活动ID
   * @param pageId     页面ID
   * @return 页面代码
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
