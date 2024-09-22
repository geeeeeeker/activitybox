package com.uxiangtech.activitybox.common;

import org.springframework.http.ResponseCookie;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CookieManager {

  public static final String COOKIE_NAME_ACTIVITYBOX_ENGINE = "ux_actbox_engine";

  public static final String COOKIE_NAME_ACTIVITYBOX_CONSOLE = "ux_actbox_console";

  private static volatile CookieManager INSTANCE = null;

  private CookieManager() {
  }

  public static CookieManager getInstance() {
    if (null == INSTANCE) {
      synchronized (CookieManager.class) {
        if (null == INSTANCE) {
          INSTANCE = new CookieManager();
        }
      }
    }
    return INSTANCE;
  }

  public Cookie addCookie(final String name, final String value) {
    final Cookie cookie = new Cookie(name, value);
    cookie.setDomain(".uxiangtech.com");
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setMaxAge(7200);
    return cookie;
  }

  public ResponseCookie addExtCookie(final String name, final String value) {
    return ResponseCookie.from(name, value)
      .httpOnly(true) // 禁止js读取
      .secure(true) // 在https下传输
      .domain(".uxiangtech.com")// 域名
      .path("/")
      .maxAge(7200)
      .sameSite("None")  // 大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外
      .build();
  }

  public ResponseCookie addExtCookie(final String domain, final String name, final String value) {
    return
      ResponseCookie.from(name, value)
        .httpOnly(true)
        .secure(true)
        .domain(domain)
        .path("/")
        .maxAge(7200)
        .sameSite("None")
        .build();
  }


  public Cookie getCookie(HttpServletRequest request, final String name) {
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(name)) {
        return cookie;
      }
    }
    return null;
  }

  public String getCookieValue(HttpServletRequest request, final String name) {
    Cookie cookie = this.getCookie(request, name);
    if (null == cookie) {
      return null;
    } else {
      return cookie.getValue();
    }
  }




  public Cookie expireCookie(final Cookie cookie) {
    cookie.setMaxAge(0);
    return cookie;
  }
}
