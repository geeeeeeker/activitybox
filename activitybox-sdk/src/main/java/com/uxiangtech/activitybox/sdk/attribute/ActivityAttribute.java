package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

import java.util.List;

/**
 * 活动属性，使用一个字段存储，读取活动时进行解析，并检查配置合法性
 *
 * 1. 奖品配置
 * 2. 页面配置
 * 3. 奖池及奖项配置
 * 4. 玩法配置
 *
 * 由于活动属性要用于管理后台配置及活动引擎解析，因此位于公共的SDK模块
 */
@Data
public class ActivityAttribute {

  /**
   * 页面列表
   */
  private List<PageAttribute> pages;

  /**
   * 奖品列表
   */
  private List<AwardAttribute> awards;

  /**
   * 奖池列表
   */
  private List<AwardPoolAttribute> pools;

  /**
   * 玩法列表
   */
  private List<PlaywayAttribute> playways;

}
