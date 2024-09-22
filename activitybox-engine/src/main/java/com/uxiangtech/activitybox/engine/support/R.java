package com.uxiangtech.activitybox.engine.support;

import com.alibaba.fastjson2.JSON;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class R {

  private static final String CODE_OK = "OK";
  private static final String CODE_UNAUTHORIZED = "UNAUTHORIZED";


  private final boolean isSuccess;
  private final String errcode;
  private final String errmsg;
  private final Object data;

  private R(final String errcode, final String errmsg, final Object data) {
    this.isSuccess = CODE_OK.equals(errcode);
    this.errcode = errcode;
    this.errmsg = errmsg;
    this.data = data;
  }

  public static R ok(final Object data) {
    return new R(CODE_OK, "请求成功", data);
  }

  public static R ok() {
    return new R(CODE_OK, "请求成功", null);
  }

  public static R unauthorized() {
    return new R(CODE_UNAUTHORIZED, "用户尚未登陆", null);
  }

  public static void write401HttpStatus(final HttpServletResponse response) {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json;charset=UTF-8");
    try (final PrintWriter writer = response.getWriter()) {
      writer.write(R.unauthorized().toJsonStr());
      writer.flush();
    } catch (final IOException ignored) {
    }
  }

  public String toJsonStr() {
    return JSON.toJSONString(this);
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public String getErrcode() {
    return errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public Object getData() {
    return data;
  }
}
