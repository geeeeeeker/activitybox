package com.uxiangtech.activitybox.sdk.context;

/**
 * 主要用于简化动作上下文获取，减少冗余代码
 */
public interface ActionCallContextCapable {
  default ActionCallContext getContext() {
    return ActionCallContextHolder.getInstance().getContext();
  }
}
