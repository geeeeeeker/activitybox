package com.uxiangtech.activitybox.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePO<T> {

  /**
   * 删除标记
   */
  protected Boolean isDeleted;

  /**
   * 创建时间
   */
  protected LocalDateTime gmtCreate;

  /**
   * 更新时间
   */
  protected LocalDateTime gmtModified;

  public Boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }

  public LocalDateTime getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(LocalDateTime gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public LocalDateTime getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(LocalDateTime gmtModified) {
    this.gmtModified = gmtModified;
  }

  protected T initialized() {
    final LocalDateTime now = LocalDateTime.now();
    this.setDeleted(false);
    this.setGmtCreate(now);
    this.setGmtModified(now);
    return (T) this;
  }
}
