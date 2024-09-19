package com.uxiangtech.activitybox.engine.modules.playways;

import com.uxiangtech.activitybox.engine.modules.actions.Action;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 玩法接口
 */
public interface Playway<P extends Playway<?>> {

  /**
   * 获取玩法ID
   * @return
   */
  String getId();

  /**
   * 获取玩法名称
   * @return
   */
  String getName();

  /**
   * 获取玩法归属的活动
   * @return
   */
  Activity getActivity();

  /**
   * 获取标准玩法实例
   * @return
   */
  Object getStdPlaywayInstance();

  /**
   * 获取玩法中的方法集合
   * @return
   */
  Map<String, Action> getActionMap();

  /**
   * 获取玩法中的指定动作
   * @param actionId
   * @return
   */
  Action getAction(final String actionId);

  /**
   * 获取玩法中的指定动作
   * @param actionId
   * @param supplier
   * @return
   */
  Action getActionOrThrow(final String actionId, final Supplier<RuntimeException> supplier);

  /**
   * 获取标准玩法代码的类加载器，玩法共享活动的类加载器
   * @return
   */
  ClassLoader getClassLoader();

  /**
   * 获取玩法源代码
   * @return
   */
  String getCode();

  /**
   * 获取标准玩法代码的编程语言
   * @return
   */
  Language getLanguage();


  /**
   * 标准玩法支持的编程语言类型
   */
  enum Language {
    JAVA, GROOVY, SCALA, KOTLIN;



  }
}
