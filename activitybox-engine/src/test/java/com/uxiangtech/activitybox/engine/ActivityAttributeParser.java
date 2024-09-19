package com.uxiangtech.activitybox.engine;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.uxiangtech.activitybox.data.activity.ActivityDAO;
import com.uxiangtech.activitybox.data.activity.ActivityPO;
import com.uxiangtech.activitybox.engine.modules.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.ActivityAttribute;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;

public class ActivityAttributeParser extends AbstractTestCase {

  @Resource
  private ActivityDAO activityDAO;

  @Test
  public void insertActivity() {

    final String attributeStr = "{\n" +
      "  \"playways\": [\n" +
      "    {\n" +
      "      \"code\": \"package com.uxiangtech.activitybox.engine;\\n\\nimport com.uxiangtech.activitybox.sdk.context.UserRequestContext;\\nimport com.uxiangtech.activitybox.sdk.playways.invitation.AbstractInvitationStdPlayway;\\nimport com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;\\nimport com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayConf;\\n\\npublic class HelloWorldStdPlayway extends AbstractInvitationStdPlayway {\\n  @Override\\n  public String getId() {\\n    return \\\"world\\\";\\n  }\\n\\n  @Override\\n  public String getName() {\\n    return \\\"测试玩法\\\";\\n  }\\n\\n  @Override\\n  public void initConf(InvitationStdPlaywayConf conf) {\\n\\n  }\\n\\n  @Override\\n  public String makeCode(UserRequestContext ctx, InvitationStdPlaywayApi api) {\\n    return \\\"abc\\\";\\n  }\\n\\n  @Override\\n  public void acceptInvitation(UserRequestContext ctx, InvitationStdPlaywayApi api, String code) {\\n    System.out.println(code);\\n  }\\n}\\n\",\n" +
      "      \"lang\": \"JAVA\"\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    LocalDateTime now = LocalDateTime.now();
    ActivityPO activityPO = new ActivityPO();
    activityPO.setId(5L);
    activityPO.setName("样例活动");
    activityPO.setStatus(Activity.Status.ONLINE.name());
    activityPO.setAttribute(attributeStr);
    activityPO.setGmtCreate(now);
    activityPO.setGmtModified(now);

    activityDAO.insert(activityPO);




//    final JSONObject attribute = JSON.parseObject(attributeStr);

  }
}





