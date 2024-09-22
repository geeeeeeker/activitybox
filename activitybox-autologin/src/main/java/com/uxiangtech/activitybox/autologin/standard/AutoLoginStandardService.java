package com.uxiangtech.activitybox.autologin.standard;

import org.springframework.http.ResponseCookie;

public interface AutoLoginStandardService {
  ResponseCookie autologin(Long tenantId, String outUserId, String outUsername, String timestamp, String redirectUrl, String sign);
}
