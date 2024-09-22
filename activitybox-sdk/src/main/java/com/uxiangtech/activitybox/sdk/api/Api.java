package com.uxiangtech.activitybox.sdk.api;

import com.uxiangtech.activitybox.sdk.context.ActionCallContextCapable;

/**
 * 动作调用API，作为最高层API接口，封装所有玩法都可能包含的API
 */
public interface Api extends ActionCallContextCapable {

  /**
   * 抽奖API，包含奖池随机抽奖和直接抽奖两种抽奖方式
   *
   * 注意：虽然奖池可以配置抽中游戏道具，但是与直接发放游戏道具奖励有区别，抽奖存在抽中概率和库存等。
   * @return
   */
  AwardDrawingApi awardDrawingApi();

  /**
   * 游戏道具奖励API
   * @return
   */
  PropApi awardPropApi();



}
