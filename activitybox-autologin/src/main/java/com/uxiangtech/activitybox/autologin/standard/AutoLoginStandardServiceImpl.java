package com.uxiangtech.activitybox.autologin.standard;

import com.uxiangtech.activitybox.common.BlowfishUtils;
import com.uxiangtech.activitybox.common.CookieManager;
import com.uxiangtech.activitybox.data.tenant.TenantDAO;
import com.uxiangtech.activitybox.data.tenant.TenantPO;
import com.uxiangtech.activitybox.data.user.UserDAO;
import com.uxiangtech.activitybox.data.user.UserPO;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Service
public class AutoLoginStandardServiceImpl implements AutoLoginStandardService {

  @Resource
  private TenantDAO tenantDAO;
  @Resource
  private UserDAO userDAO;

  @Override
  public ResponseCookie autologin(Long tenantId, String outUserId, String outUsername, String timestamp, String redirectUrl, String sign) {

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
          .initialized();
      this.userDAO.insert(userPO);
    }

    final String cookieContent =
      "      {\n" +
        "        \"tenant_id\": \"" + tenantId + "\",\n" +
        "        \"out_user_id\": \"" + outUserId + "\",\n" +
        "        \"out_user_name\": \"" + outUsername + "\",\n" +
        "        \"user_id\": \"" + userPO.getId() + "\"\n" +
        "      }";

    String encryptedCookie = null;
    try {
      encryptedCookie =
        BlowfishUtils.encryptToBase64(
          cookieContent.getBytes(StandardCharsets.UTF_8), "");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    final ResponseCookie cookie =
      CookieManager.getInstance()
        .getResponseCookie(CookieManager.COOKIE_NAME_ACTIVITYBOX_ENGINE, encryptedCookie);
    return cookie;
  }
}
