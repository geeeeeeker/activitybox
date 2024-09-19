package com.uxiangtech.activitybox.engine.support.compiler;

import lombok.Data;

@Data
public class ClassMeta {
  /**
   * 包名
   */
  private String packageName;
  /**
   * 类的简单名
   */
  private String simpleName;
  /**
   * 类的完全限定名
   */
  private String fullQualifiedName;

  /**
   * 类归属的代码
   */
  private String code;
}
