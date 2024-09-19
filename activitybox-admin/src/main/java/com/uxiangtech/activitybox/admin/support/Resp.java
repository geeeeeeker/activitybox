package com.uxiangtech.activitybox.admin.support;

import com.alibaba.fastjson2.JSON;

import java.io.Serializable;

public class Resp<T> implements Serializable {
  private Integer errcode;
  private String errmsg;
  private T data;

  private static <T> Resp<T> restResult(int code, String msg, T data)
  {
    Resp<T> resp = new Resp<>();
    resp.setErrcode(code);
    resp.setErrmsg(msg);
    resp.setData(data);
    return resp;
  }

  public static <T> Resp<T> ok() {
    return restResult(0, "操作成功", null);
  }

  public static <T> Resp<T> ok(final T data) {
    return restResult(0, "操作成功", data);
  }

  public static <T> Resp<T> parameterRequired(final String parameter) {
    return restResult(400, "请求参数缺失:"+parameter, null);
  }

  public static <T> Resp<T> badRequest() {
    return restResult(400, "参数错误", null);
  }

  public static <T> Resp<T> badRequest(final String errmsg) {
    return restResult(400, errmsg, null);
  }

  public static <T> Resp<T> badTermType() {
    return restResult(400, "非法访问，登陆终端不匹配", null);
  }

  public static <T> Resp<T> unauthorized() {
    return restResult(401, "访问授权已过期", null);
  }

  public static <T> Resp<T> forbidden() {
    return restResult(403, "无操作权限", null);
  }

  public static <T> Resp<T> error() {
    return restResult(500, "系统错误", null);
  }

  public static <T> Resp<T> error(final String errmsg) {
    return restResult(500, errmsg, null);
  }


  public String toJsonStr() {
    return JSON.toJSONString(this);
  }

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  public Object getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
