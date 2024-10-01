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

  protected Boolean getDeleted() {
    return this.isDeleted;
  }

  protected void setDeleted(Boolean deleted) {
    this.isDeleted = deleted;
  }

  protected LocalDateTime getGmtCreate() {
    return this.gmtCreate;
  }

  protected void setGmtCreate(LocalDateTime gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  protected LocalDateTime getGmtModified() {
    return gmtModified;
  }

  protected void setGmtModified(LocalDateTime gmtModified) {
    this.gmtModified = gmtModified;
  }

  public T markAsInitialized() {
    final LocalDateTime now = LocalDateTime.now();
    this.setDeleted(false);
    this.setGmtCreate(now);
    this.setGmtModified(now);
    return (T) this;
  }
}
