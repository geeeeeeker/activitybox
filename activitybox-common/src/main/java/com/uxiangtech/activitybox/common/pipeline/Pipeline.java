package com.uxiangtech.activitybox.common.pipeline;

public interface Pipeline<PC extends PipelineContext, PN extends PipelineNode, PE extends PipeNodeException> {
  void addChainNode(final PN node);

  void doPipe(PC context) throws PE;
}
