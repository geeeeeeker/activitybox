
标准活动、定制活动（尽量走动态配置，特殊的做开发定制）


1. 客户信息接入标准化
2. 奖品信息接入标准化
3. 基础能力接入标准化：微信相关（公众号、小程序）、支付（微信支付、支付宝支付、银行卡支付）、手机号地区限制等
4. 玩法标准化


分布式锁 => DB和Redis各自实现



1. 基础玩法：计次（CounterPlayway）、计分（ScoringPlayway）、邀请（InvitationPlayway）、助力（HelpPlayway）



支持基于注解（@BoxPlayway @BoxAction）和接口/抽象类方式定义玩法




基于抽象类/接口约定的玩法

```java

public class SampleInvitationStdPlayway extends AbstractInvitationStdPlayway {
  public MyInvitationStdPlayway() {
  }

  @Override
  public String getId() {
    return "sample_invitation";
  }

  @Override
  public String getName() {
    return "样例邀请玩法";
  }

  @Override
  public void initConf(InvitationStdPlaywayConf conf) {
  }

  @Override
  public String makeCode(UserRequestContext ctx, InvitationStdPlaywayApi api) {
    return null;
  }

  @Override
  public void acceptInvitation(UserRequestContext ctx, InvitationStdPlaywayApi api, String code) {
  }
}

```

```java

@ActPlayway(id = "sample_invitation", name = "样例邀请玩法")
public class SampleInvitationStdPlayway {
  public MyInvitationStdPlayway() {
  }

  @ActPlaywayConf
  public void initConf(InvitationStdPlaywayConf conf) {
  }

  @ActAction(id = "make_code", name = "生成邀请码")
  public String makeCode(UserRequestContext ctx, InvitationStdPlaywayApi api) {
    return null;
  }

  @ActAcion(id = "accept_invitation", name = "接受邀请")
  public void acceptInvitation(UserRequestContext ctx, InvitationStdPlaywayApi api, String code) {
  }
}

```









