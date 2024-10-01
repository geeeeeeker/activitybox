package com.uxiangtech.activitybox.autologin.standard;

import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletRequest;

/**
 * 自动登陆标准服务
 */
public interface AutoLoginStandardService {

  /**
   * 活动标准免登接口
   *
   * @param tenantId    租户ID
   * @param outUserId   外部合作方用户ID
   * @param outUsername 外部合作方用户名
   * @param timestamp   时间戳
   * @param redirectUrl 重定向活动地址
   * @param extensions  扩展参数，接入方可传递该参数作为活动人群圈定、抽奖限制等。传递格式 key1=value1,key2=value2,...使用URLEncode编码一次
   * @param sign        参数签名
   * @return cookie     SPRING扩展的标准Cookie
   */
  ResponseCookie autologin(Long tenantId, String outUserId, String outUsername, String timestamp, String extensions, String redirectUrl, String sign);
}
