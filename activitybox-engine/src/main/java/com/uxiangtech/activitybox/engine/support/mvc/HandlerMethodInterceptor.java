package com.uxiangtech.activitybox.engine.support.mvc;

import com.alibaba.fastjson2.JSONObject;
import com.uxiangtech.activitybox.common.BlowfishUtils;
import com.uxiangtech.activitybox.common.CookieManager;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.context.UserContextImpl;
import com.uxiangtech.activitybox.engine.support.R;
import com.uxiangtech.activitybox.sdk.context.UserContext;
import com.uxiangtech.activitybox.sdk.context.UserContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HandlerMethodInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return false;
    }
    final HandlerMethod handlerMethod = (HandlerMethod) handler;

    final NoLogin noLoginAnno =
      handlerMethod.getMethodAnnotation(NoLogin.class);
    if (null != noLoginAnno) {
      return true;
    }

    final Cookie cookie=
      CookieManager.getInstance().getCookie(
        request, CookieManager.COOKIE_NAME_ACTIVITYBOX_ENGINE);
    if (null == cookie) {
      R.write401HttpStatus(response);
      return false;
    }

    // 解析Coolie
    final String value = cookie.getValue();
    final String plainText =
      BlowfishUtils.decryptToString(value, BlowfishUtils.SECRET_KEY_ENGINE);
    JSONObject cookieObj =
      JSONObject.parseObject(plainText);

    Long tenantId = cookieObj.getLong("tenant_id");
    String outUserId = cookieObj.getString("out_userid");
    String outUsername = cookieObj.getString("out_username");
    Long userId = cookieObj.getLong("tenant_id");

    // 构建用户上下文
    UserContext userContext = new UserContextImpl(tenantId, userId, outUserId, outUsername);
    UserContextHolder.getInstance().setContext(userContext);

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                              @Nullable Exception ex) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return;
    }
    UserContextHolder.getInstance().removeContext();
  }

}
