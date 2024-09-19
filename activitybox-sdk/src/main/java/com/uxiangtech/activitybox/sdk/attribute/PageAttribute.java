package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

/**
 * 前端页面配置信息
 */
@Data
public class PageAttribute {
  private String id;
  private String name;
  private String desc;
  /**
   * 前端HTML源码
   */
  private String code;
}
