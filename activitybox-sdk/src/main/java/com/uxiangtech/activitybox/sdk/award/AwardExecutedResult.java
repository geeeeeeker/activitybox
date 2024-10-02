package com.uxiangtech.activitybox.sdk.award;

import lombok.Getter;

/**
 * 奖品发放结果
 */
@Getter
public class AwardExecutedResult {

  private final String link;

  private final boolean isSuccess;

  public AwardExecutedResult(String link, boolean isSuccess) {
    this.link = link;
    this.isSuccess = isSuccess;
  }
}
