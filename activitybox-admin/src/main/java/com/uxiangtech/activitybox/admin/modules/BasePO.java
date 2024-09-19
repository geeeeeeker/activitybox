package com.uxiangtech.activitybox.admin.modules;

import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BasePO {

  @TableId
  private Long id;

  /**
   * 软删标记
   */
  @TableField("is_deleted")
  protected Boolean isDeleted;
  /**
   * 创建时间
   */
  @TableField("gmt_create")
  protected LocalDateTime gmtCreate;
  /**
   * 更新时间
   */
  @TableField("gmt_modified")
  protected LocalDateTime gmtModified;

  public <T extends BasePO> T markAsInitialized() {
    final LocalDateTime now = LocalDateTime.now();
    this.setIsDeleted(false);
    this.setGmtCreate(now);
    this.setGmtModified(now);
    return (T) this;
  }

  public <T extends BasePO> T markAsDeleted() {
    this.setIsDeleted(true);
    this.setGmtModified(LocalDateTime.now());
    return (T) this;
  }
}
