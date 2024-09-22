package com.uxiangtech.activitybox.sdk.attribute;

import com.uxiangtech.activitybox.sdk.playways.PlaywayType;
import lombok.Data;

@Data
public class PlaywayAttribute {

  private String id;
  private String name;


  /**
   * 基础玩法类型：邀请、助力、计分、计数
   */
  private String type;  // TODO 目前玩法类型由代码定义


  // 如果是预定义的玩法，以下属性均不需要，只需要配置基础信息即可

  /**
   * 源代码
   */
  private String code;
  /**
   * 代码类型：支持Java、Groovy、Kotlin、Scala等JVM平台语言
   */
  private String lang;
  /**
   * 玩法代码版本
   */
  private Integer version;

  // TODO 玩法配置，不同的玩法支持不同配置参数

  /**
   * 邀请玩法配置
   */
  private Invitation invitation ;

  public Invitation getInvitation() {
    if (PlaywayType.INVITATION.name().equals(type)) {
      return this.invitation;
    } else {
      return new Invitation();
    }
  }

  /**
   * 邀请玩法配置
   */
  class Invitation {
    private Boolean isInviteMyselfAllowed = false;
  }
}
