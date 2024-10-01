package com.uxiangtech.activitybox.common.pipeline;

/**
 * 责任链节点
 * @param <PC>
 * @param <P>
 */
public interface PipelineNode<PC extends PipelineContext<PC>, P extends Pipeline, PE extends PipeNodeException> {
  void doPipe(PC context, P pipeline) throws PE;
}
