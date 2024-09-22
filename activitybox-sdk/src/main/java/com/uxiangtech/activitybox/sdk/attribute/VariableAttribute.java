package com.uxiangtech.activitybox.sdk.attribute;

import lombok.Data;

/**
 * 变量配置信息
 */
@Data
public class VariableAttribute {
  private String key;
  private Object value;
  private String type;
}
