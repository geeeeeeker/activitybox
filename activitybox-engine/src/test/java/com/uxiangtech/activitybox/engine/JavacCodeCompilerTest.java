package com.uxiangtech.activitybox.engine;

import com.uxiangtech.activitybox.engine.modules.activity.UserRequestContextImpl;
import com.uxiangtech.activitybox.engine.modules.playways.invitation.InvitationStdPlaywayApiImpl;
import com.uxiangtech.activitybox.engine.support.classloader.PlaywayInstanceFactory;
import com.uxiangtech.activitybox.engine.support.classloader.PlaywayJavaInstanceFactory;
import com.uxiangtech.activitybox.sdk.context.UserRequestContext;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class JavacCodeCompilerTest {

  @Test
  public void test() {

    final String code =
      "package com.uxiangtech.activitybox.engine;\n" +
        "\n" +
        "import com.uxiangtech.activitybox.engine.modules.playways.ActPlayway;\n" +
        "\n" +
        "@ActPlayway(id = \"demo\", type = \"join\")\n" +
        "public class HelloWordPlayway {\n" +
        "  public void join() {\n" +
        "    System.out.println(\"xxx\");\n" +
        "  }\n" +
        "}";

    final PlaywayInstanceFactory playwayInstanceFactory = new PlaywayJavaInstanceFactory(Thread.currentThread().getContextClassLoader());

    Map<String, StdPlayway> stringObjectMap = playwayInstanceFactory.newInstances(code);

    for (Map.Entry<String, StdPlayway> entry : stringObjectMap.entrySet()) {
      System.out.println(entry.getKey());

      Object playway = entry.getValue();

      try {
        Method joinMethod = playway.getClass().getDeclaredMethod("join");
        joinMethod.invoke(playway);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

  }

  @Test
  public void test2() {

    final String code = "package com.uxiangtech.activitybox.engine;\n" +
      "\n" +
      "import com.uxiangtech.activitybox.sdk.context.UserRequestContext;\n" +
      "import com.uxiangtech.activitybox.sdk.playways.invitation.AbstractInvitationStdPlayway;\n" +
      "import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;\n" +
      "import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayConf;\n" +
      "\n" +
      "public class HelloWorldStdPlayway extends AbstractInvitationStdPlayway {\n" +
      "  @Override\n" +
      "  public String getId() {\n" +
      "    return \"hello\";\n" +
      "  }\n" +
      "\n" +
      "  @Override\n" +
      "  public String getName() {\n" +
      "    return \"测试玩法\";\n" +
      "  }\n" +
      "\n" +
      "  @Override\n" +
      "  public void initConf(InvitationStdPlaywayConf conf) {\n" +
      "\n" +
      "  }\n" +
      "\n" +
      "  @Override\n" +
      "  public String makeCode(UserRequestContext ctx, InvitationStdPlaywayApi api) {\n" +
      "    return \"hello\";\n" +
      "  }\n" +
      "\n" +
      "  @Override\n" +
      "  public void acceptInvitation(UserRequestContext ctx, InvitationStdPlaywayApi api, String code) {\n" +
      "\n" +
      "  }\n" +
      "}\n";

    final PlaywayInstanceFactory playwayInstanceFactory = new PlaywayJavaInstanceFactory(Thread.currentThread().getContextClassLoader());

    Map<String, StdPlayway> stringObjectMap = playwayInstanceFactory.newInstances(code);

    for (Map.Entry<String, StdPlayway> entry : stringObjectMap.entrySet()) {
      System.out.println(entry.getKey());

      Object playway = entry.getValue();

      try {

        Method makeCodeMethod =
          playway.getClass().getDeclaredMethod("makeCode", UserRequestContext.class, InvitationStdPlaywayApi.class);

        UserRequestContext ctx = new UserRequestContextImpl();
        InvitationStdPlaywayApi api = new InvitationStdPlaywayApiImpl();
        makeCodeMethod.invoke(playway, ctx, api);


      } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }

  }




}
