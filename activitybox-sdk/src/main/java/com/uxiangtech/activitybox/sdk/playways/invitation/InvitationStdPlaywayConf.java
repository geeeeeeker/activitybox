package com.uxiangtech.activitybox.sdk.playways.invitation;

import com.uxiangtech.activitybox.sdk.playways.AbstractStdPlaywayConf;
import com.uxiangtech.activitybox.sdk.playways.StdPlaywayConf;
import lombok.Data;

/**
 * 邀请标准玩法配置
 */
public class InvitationStdPlaywayConf extends AbstractStdPlaywayConf {

  /**
   * 是否支持邀请自己
   */
  private Boolean inviteMyselfAllowed = false;

  public InvitationStdPlaywayConf(final String id, final String name) {
    super(id, name);
  }
}
