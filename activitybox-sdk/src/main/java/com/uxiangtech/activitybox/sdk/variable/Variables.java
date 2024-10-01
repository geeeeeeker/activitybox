package com.uxiangtech.activitybox.sdk.variable;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.uxiangtech.activitybox.sdk.activity.Activity;

import java.math.BigDecimal;

/**
 * 变量容器，用于存储配置项，类似于Apollo或Nacos等功能配置中心功能
 */
public interface Variables {

  enum Type {
    BOOLEAN, INT, DECIMAL, STRING, JSON_OBJECT, JSON_ARRAY
  }

  Activity getActivity();

  void putValue(String key, Object value);


  Variables fluentPutValue(String key, Object value);

  /**
   * 获取变量值
   * @param key 变量KEY
   * @return 变量值
   */
  Object getValue(final String key);

  default Boolean getDataAsBoolean(final String key) throws ClassCastException {
    return (Boolean)this.getValue(key);
  }

  default Integer getDataAsInt(final String key) throws ClassCastException {
    return (Integer)this.getValue(key);
  }

  default BigDecimal getDataAsDecimal(final String key) throws ClassCastException {
    return (BigDecimal)this.getValue(key);
  }

  default String getDataAsString(final String key) throws ClassCastException {
    return (String)this.getValue(key);
  }

  default JSONObject getDataAsJsonObject(final String key) throws ClassCastException {
    return JSONObject.parseObject(getDataAsString(key));
  }

  default JSONArray getDataAsJsonArray(final String key) throws ClassCastException {
    return JSONArray.parseArray(getDataAsString(key));
  }

}
