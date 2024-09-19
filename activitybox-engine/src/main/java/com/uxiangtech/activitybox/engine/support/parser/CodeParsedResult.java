package com.uxiangtech.activitybox.engine.support.parser;

import com.uxiangtech.activitybox.engine.support.compiler.ClassMeta;

import java.util.List;

/**
 * 代码解析结果
 */
public class CodeParsedResult {
  /**
   * 源代码
   */
  private final String code;
  /**
   * 解析结果：包含类元信息
   */
  private final List<ClassMeta> classMetas;

  public CodeParsedResult(String code, List<ClassMeta> classMetas) {
    this.code = code;
    this.classMetas = classMetas;
  }

  public String getCode() {
    return code;
  }

  public List<ClassMeta> getClassMetas() {
    return classMetas;
  }
}
