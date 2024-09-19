package com.uxiangtech.activitybox.engine.modules.actions;

import com.uxiangtech.activitybox.engine.modules.activity.UserRequestContextImpl;
import com.uxiangtech.activitybox.engine.modules.playways.Playway;
import com.uxiangtech.activitybox.sdk.context.UserRequestContext;

import java.lang.reflect.Method;

public abstract class AbstractAction implements Action {

  /**
   * 动作ID
   */
  private final String id;

  /**
   * 动作名称
   */
  private final String name;

  /**
   * 归属玩法
   */
  private final Playway playway;


  // TODO 支持事务，定时任务


  public AbstractAction(String id, String name, Playway playway) {
    this.id = id;
    this.name = name;
    this.playway = playway;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Playway getPlayway() {
    return this.playway;
  }

  @Override
  public Object execute(UserRequestContext context) {

    // 分布式锁、分布式限流、分布式事务、分布式调度等注解处理，以及嵌入监控、日志等

    return this.doExecute(context);

  }

  protected abstract Object doExecute(UserRequestContext context);

}
