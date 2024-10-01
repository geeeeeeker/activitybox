package com.uxiangtech.activitybox.common.pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于顺序表实现的责任链
 * @param <PC>
 * @param <PN>
 * @param <PE>
 */
public abstract class AbstractPipeline<PC extends PipelineContext<PC>, PN extends PipelineNode, PE extends PipeNodeException> implements Pipeline<PC, PN, PE> {

  private int curIndex;

  private final List<PN> nodes;

  public AbstractPipeline(final List<PN> nodes) {
    if (null == nodes) {
      this.nodes = new ArrayList<>();
    } else {
      this.nodes = nodes;
    }
    this.curIndex = 0;
  }

  @Override
  public void addChainNode(PN node) {
    this.nodes.add(node);
  }

  @Override
  public void doPipe(PC context) throws PE {

    // 仅在启动pipeline时调用
    if (this.curIndex == 0) {
      this.doBeforePipe(context);
    }

    // 仅在最后一个结点执行完成后调用
    if (this.curIndex >= this.nodes.size()) {
      this.doAfterPipe(context);
      // 结束掉责任链调用逻辑
      return;
    }

    final PN node = this.nodes.get(this.curIndex);

    this.curIndex++;

    node.doPipe(context, this);
  }

  protected void doBeforePipe(PC context) {
  }

  protected void doAfterPipe(PC context) {
  }


}
