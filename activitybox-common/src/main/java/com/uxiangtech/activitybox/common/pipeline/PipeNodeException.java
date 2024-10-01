package com.uxiangtech.activitybox.common.pipeline;

public class PipeNodeException extends RuntimeException {
  public PipeNodeException() {
  }

  public PipeNodeException(String message) {
    super(message);
  }

  public PipeNodeException(String message, Throwable cause) {
    super(message, cause);
  }

  public PipeNodeException(Throwable cause) {
    super(cause);
  }
}
