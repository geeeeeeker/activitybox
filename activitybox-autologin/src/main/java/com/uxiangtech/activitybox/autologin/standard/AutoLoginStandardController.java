package com.uxiangtech.activitybox.autologin.standard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AutoLoginStandardController {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(AutoLoginStandardController.class);

  @Resource
  private AutoLoginStandardService autoLoginStandardService;

  /**
   * 活动标准免登接口
   *
   * @param tenantId    租户ID
   * @param outUserId   外部合作方用户ID
   * @param timestamp   时间戳
   * @param redirectUrl 重定向活动地址
   * @param sign        参数签名
   */
  @GetMapping("/autologin")
  public void autologin(@RequestParam(value = "tenant_id", required = false) Long tenantId,
                        @RequestParam(value = "out_userid", required = false) String outUserId,
                        @RequestParam(value = "out_username", required = false) String outUsername,
                        @RequestParam(value = "timestamp", required = false) String timestamp,
                        @RequestParam(value = "redirect_url", required = false) String redirectUrl,
                        @RequestParam(value = "sign", required = false) String sign,
                        HttpServletResponse response) throws IOException {
    final ResponseCookie cookie =
      this.autoLoginStandardService.autologin(
        tenantId, outUserId, outUsername, timestamp, redirectUrl, sign);
    response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    response.sendRedirect(redirectUrl);
  }
}
