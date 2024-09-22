package com.uxiangtech.activitybox.data.playways.invitation;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_invitation_code")
public class InvitationRecordPO {

  @TableId
  private Long id;

  /**
   * 活动ID
   */
  private Long activityId;

  /**
   * 邀请人用户ID
   */
  private Long inviterId;

  /**
   * 受邀人用户ID
   */
  private Long inviteeId;

  /**
   * 删除标记
   */
  private Boolean isDeleted;

  /**
   * 创建时间
   */
  private LocalDateTime gmtCreate;

  /**
   * 更新时间
   */
  private LocalDateTime gmtModified;
}
