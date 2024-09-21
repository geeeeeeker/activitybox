package com.uxiangtech.activitybox.engine.modules.playways.invitation;

import com.uxiangtech.activitybox.data.invitation.InvitationCodeDAO;
import com.uxiangtech.activitybox.data.invitation.InvitationCodePO;
import com.uxiangtech.activitybox.data.invitation.InvitationRecordDAO;
import com.uxiangtech.activitybox.data.invitation.InvitationRecordPO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationServiceImpl implements InvitationService {

  @Resource
  private InvitationCodeDAO invitationCodeDAO;

  @Resource
  private InvitationRecordDAO invitationRecordDAO;

  /**
   * 生成邀请码
   *
   * TODO 考虑根据
   *
   * @param activityId
   * @param userId
   * @return
   */
  @Override
  public String makeCode(final Long activityId, final Long userId) {

    final InvitationCodePO invitationCodePO =
      this.invitationCodeDAO.findByUserIdAndActivityId(userId, activityId);
    if (null != invitationCodePO) {
      return invitationCodePO.getCode();
    }

    return this.doMakeCode(activityId, userId, 6, 0);
  }

  @Override
  public String makeCode(final Long activityId, final Long userId, final int codeLength) {

    final InvitationCodePO invitationCodePO =
      this.invitationCodeDAO.findByUserIdAndActivityId(userId, activityId);
    if (null != invitationCodePO) {
      return invitationCodePO.getCode();
    }

    return this.doMakeCode(activityId, userId, codeLength, 0);
  }

  private String doMakeCode(final Long activityId, final Long userId, int codeLength, int cnt) {

    if (cnt >= 3) {
      throw new RuntimeException("生成邀请码失败");
    }
    cnt += 1;

    final String code = RandomStringUtils.randomAlphanumeric(codeLength);

    InvitationCodePO invitationCodePO =
      this.invitationCodeDAO.findByInvitationCodeAndActivityId(code, activityId);

    if (null != invitationCodePO) {
      return this.doMakeCode(activityId, userId, codeLength, cnt);
    } else {
      invitationCodePO =
        InvitationCodePO.builder()
          .activityId(activityId)
          .userId(userId)
          .code(code)
          .build();
      this.invitationCodeDAO.insert(invitationCodePO);
    }

    return code;
  }


  @Override
  public Long getInviterByCode(final Long activityId, final String code) {
    final InvitationCodePO invitationCodePO =
      this.invitationCodeDAO.findByInvitationCodeAndActivityId(code, activityId);
    return invitationCodePO.getUserId();
  }

  @Override
  public List<Long> getInviteeByCode(final Long activityId, final String code) {
    final InvitationCodePO invitationCodePO =
      this.invitationCodeDAO.findByInvitationCodeAndActivityId(code, activityId);
    final Long inviterId = invitationCodePO.getUserId();

    final List<InvitationRecordPO> invitationRecordPOs =
      this.invitationRecordDAO.findAllByInviterIdAndActivityId(inviterId, activityId);

    return
      invitationRecordPOs.stream()
        .map(InvitationRecordPO::getInviteeId)
        .collect(Collectors.toList());
  }
}
