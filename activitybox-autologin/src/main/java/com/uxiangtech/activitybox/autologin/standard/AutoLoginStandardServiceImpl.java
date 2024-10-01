package com.uxiangtech.activitybox.autologin.standard;

import com.uxiangtech.activitybox.common.security.BlowfishUtils;
import com.uxiangtech.activitybox.common.CookieManager;
import com.uxiangtech.activitybox.data.tenant.TenantDAO;
import com.uxiangtech.activitybox.data.tenant.TenantPO;
import com.uxiangtech.activitybox.data.user.UserDAO;
import com.uxiangtech.activitybox.data.user.UserPO;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 自动登陆标准服务实现
 */
@Service
public class AutoLoginStandardServiceImpl implements AutoLoginStandardService {

  @Resource
  private TenantDAO tenantDAO;
  @Resource
  private UserDAO userDAO;

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
  @Override
  public ResponseCookie autologin(Long tenantId, String outUserId, String outUsername, String timestamp, String extensions, String redirectUrl, String sign) {

    // 校验参数及其签名
    final TenantPO tenantPO = this.tenantDAO.findById(tenantId);
    if (null == tenantPO) {
      throw new RuntimeException("租户不存在");
    }

    UserPO userPO =
      this.userDAO.findByOutUserIdAndTenantId(outUserId, tenantId);
    if (null == userPO) {
      userPO =
        UserPO.builder()
          .tenantId(tenantId)
          .outUserId(outUserId)
          .outUsername(outUsername)
          .build()
          .markAsInitialized();
      this.userDAO.insert(userPO);
    }



    // TODO 记录用户登陆日志，以及活动投放环境等

    final ServletRequestAttributes servletRequestAttributes =
      (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    final HttpServletRequest request = servletRequestAttributes.getRequest();
    final String uaHeader = request.getHeader("User-Agent");
    // 判断是否来自微信小程序？


    final String cookieContent =
      "      {\n" +
        "        \"tenant_id\": \"" + tenantId + "\",\n" +
        "        \"out_user_id\": \"" + outUserId + "\",\n" +
        "        \"out_user_name\": \"" + outUsername + "\",\n" +
        "        \"user_id\": \"" + userPO.getId() + "\"\n" +
        "        \"extensions\": \"" + extensions + "\"\n" +
        "      }";

    String encryptedCookie = null;
    try {
      encryptedCookie =
        BlowfishUtils.encryptToBase64(cookieContent, BlowfishUtils.SECRET_KEY_ENGINE);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    final ResponseCookie cookie =
      CookieManager.getInstance()
        .addExtCookie(CookieManager.COOKIE_NAME_ACTIVITYBOX_ENGINE, encryptedCookie);
    return cookie;
  }
}
