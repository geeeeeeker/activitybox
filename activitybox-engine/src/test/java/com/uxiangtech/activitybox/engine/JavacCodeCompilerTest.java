package com.uxiangtech.activitybox.engine;

import com.uxiangtech.activitybox.engine.modules.activity.ActionCallContextImpl;
import com.uxiangtech.activitybox.engine.modules.playway.invitation.InvitationStdPlaywayApiImpl;
import com.uxiangtech.activitybox.engine.support.classloader.JavaBasedStdPlaywayObjectFactory;
import com.uxiangtech.activitybox.engine.support.classloader.StdPlaywayObjectFactory;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;
import com.uxiangtech.activitybox.sdk.playways.invitation.InvitationStdPlaywayApi;
import org.junit.Test;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
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

    final StdPlaywayObjectFactory stdPlaywayObjectFactory = new JavaBasedStdPlaywayObjectFactory(Thread.currentThread().getContextClassLoader());

    Map<String, StdPlayway> stringObjectMap = stdPlaywayObjectFactory.newInstances(code);

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

    final StdPlaywayObjectFactory stdPlaywayObjectFactory = new JavaBasedStdPlaywayObjectFactory(Thread.currentThread().getContextClassLoader());

    Map<String, StdPlayway> stringObjectMap = stdPlaywayObjectFactory.newInstances(code);

    for (Map.Entry<String, StdPlayway> entry : stringObjectMap.entrySet()) {
      System.out.println(entry.getKey());

      Object playway = entry.getValue();

      try {

        Method makeCodeMethod =
          playway.getClass().getDeclaredMethod("makeCode", ActionCallContext.class, InvitationStdPlaywayApi.class);

        HttpServletRequest request = new HttpServletRequest() {
          @Override
          public String getAuthType() {
            return null;
          }

          @Override
          public Cookie[] getCookies() {
            return new Cookie[0];
          }

          @Override
          public long getDateHeader(String name) {
            return 0;
          }

          @Override
          public String getHeader(String name) {
            return null;
          }

          @Override
          public Enumeration<String> getHeaders(String name) {
            return null;
          }

          @Override
          public Enumeration<String> getHeaderNames() {
            return null;
          }

          @Override
          public int getIntHeader(String name) {
            return 0;
          }

          @Override
          public String getMethod() {
            return null;
          }

          @Override
          public String getPathInfo() {
            return null;
          }

          @Override
          public String getPathTranslated() {
            return null;
          }

          @Override
          public String getContextPath() {
            return null;
          }

          @Override
          public String getQueryString() {
            return null;
          }

          @Override
          public String getRemoteUser() {
            return null;
          }

          @Override
          public boolean isUserInRole(String role) {
            return false;
          }

          @Override
          public Principal getUserPrincipal() {
            return null;
          }

          @Override
          public String getRequestedSessionId() {
            return null;
          }

          @Override
          public String getRequestURI() {
            return null;
          }

          @Override
          public StringBuffer getRequestURL() {
            return null;
          }

          @Override
          public String getServletPath() {
            return null;
          }

          @Override
          public HttpSession getSession(boolean create) {
            return null;
          }

          @Override
          public HttpSession getSession() {
            return null;
          }

          @Override
          public String changeSessionId() {
            return null;
          }

          @Override
          public boolean isRequestedSessionIdValid() {
            return false;
          }

          @Override
          public boolean isRequestedSessionIdFromCookie() {
            return false;
          }

          @Override
          public boolean isRequestedSessionIdFromURL() {
            return false;
          }

          @Override
          public boolean isRequestedSessionIdFromUrl() {
            return false;
          }

          @Override
          public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
            return false;
          }

          @Override
          public void login(String username, String password) throws ServletException {

          }

          @Override
          public void logout() throws ServletException {

          }

          @Override
          public Collection<Part> getParts() throws IOException, ServletException {
            return null;
          }

          @Override
          public Part getPart(String name) throws IOException, ServletException {
            return null;
          }

          @Override
          public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass) throws IOException, ServletException {
            return null;
          }

          @Override
          public Object getAttribute(String name) {
            return null;
          }

          @Override
          public Enumeration<String> getAttributeNames() {
            return null;
          }

          @Override
          public String getCharacterEncoding() {
            return null;
          }

          @Override
          public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

          }

          @Override
          public int getContentLength() {
            return 0;
          }

          @Override
          public long getContentLengthLong() {
            return 0;
          }

          @Override
          public String getContentType() {
            return null;
          }

          @Override
          public ServletInputStream getInputStream() throws IOException {
            return null;
          }

          @Override
          public String getParameter(String name) {
            return null;
          }

          @Override
          public Enumeration<String> getParameterNames() {
            return null;
          }

          @Override
          public String[] getParameterValues(String name) {
            return new String[0];
          }

          @Override
          public Map<String, String[]> getParameterMap() {
            return null;
          }

          @Override
          public String getProtocol() {
            return null;
          }

          @Override
          public String getScheme() {
            return null;
          }

          @Override
          public String getServerName() {
            return null;
          }

          @Override
          public int getServerPort() {
            return 0;
          }

          @Override
          public BufferedReader getReader() throws IOException {
            return null;
          }

          @Override
          public String getRemoteAddr() {
            return null;
          }

          @Override
          public String getRemoteHost() {
            return null;
          }

          @Override
          public void setAttribute(String name, Object o) {

          }

          @Override
          public void removeAttribute(String name) {

          }

          @Override
          public Locale getLocale() {
            return null;
          }

          @Override
          public Enumeration<Locale> getLocales() {
            return null;
          }

          @Override
          public boolean isSecure() {
            return false;
          }

          @Override
          public RequestDispatcher getRequestDispatcher(String path) {
            return null;
          }

          @Override
          public String getRealPath(String path) {
            return null;
          }

          @Override
          public int getRemotePort() {
            return 0;
          }

          @Override
          public String getLocalName() {
            return null;
          }

          @Override
          public String getLocalAddr() {
            return null;
          }

          @Override
          public int getLocalPort() {
            return 0;
          }

          @Override
          public ServletContext getServletContext() {
            return null;
          }

          @Override
          public AsyncContext startAsync() throws IllegalStateException {
            return null;
          }

          @Override
          public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
          }

          @Override
          public boolean isAsyncStarted() {
            return false;
          }

          @Override
          public boolean isAsyncSupported() {
            return false;
          }

          @Override
          public AsyncContext getAsyncContext() {
            return null;
          }

          @Override
          public DispatcherType getDispatcherType() {
            return null;
          }
        };

        ActionCallContext ctx = new ActionCallContextImpl(1L, "", "", request);
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
