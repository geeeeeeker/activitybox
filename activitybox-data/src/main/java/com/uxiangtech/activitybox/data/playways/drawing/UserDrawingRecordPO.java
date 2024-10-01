package com.uxiangtech.activitybox.data.playways.drawing;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uxiangtech.activitybox.data.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_drawing_record")
public class UserDrawingRecordPO extends BasePO<UserDrawingRecordPO> {

  @TableId
  private Long id;

  private Long activityId;

  private String playwayId;

  private String actionId;

  private String awardPoolId;

  private String awardId;

  private String awardOptionId;

  private String awardOptionName;

  private String userId;

}
