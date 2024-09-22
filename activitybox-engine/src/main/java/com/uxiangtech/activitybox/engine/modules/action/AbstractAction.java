package com.uxiangtech.activitybox.engine.modules.action;

import com.uxiangtech.activitybox.sdk.action.Action;
import com.uxiangtech.activitybox.sdk.playway.Playway;
import com.uxiangtech.activitybox.sdk.context.ActionCallContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAction implements Action {

  /**
   * 动作ID
   */
  private final String id;

  /**
   * 动作名称
   */
  private final String name;

  /**
   * 归属玩法
   */
  private final Playway playway;

  // TODO 支持事务，定时任务


  public AbstractAction(String id, String name, Playway playway) {
    this.id = id;
    this.name = name;
    this.playway = playway;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Playway getPlayway() {
    return this.playway;
  }

  @Override
  public Object execute(ActionCallContext context) {

    Method executableMethod = null;
    try {
      executableMethod = this.getExecutableMethod();
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }

    // 处理事务
    TransactionStatus transactionStatus = null;
    Class<? extends Throwable>[] noRollbackForClasses = null;
    Class<? extends Throwable>[] rollbackForClasses = null;
    final PlatformTransactionManager txManager =
      this.getPlayway().getActivity().getTxManager();
    final Transactional transactionalAnno =
      executableMethod.getAnnotation(Transactional.class);
    if (null != transactionalAnno) {
      final DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
      txDef.setPropagationBehavior(transactionalAnno.propagation().value());
      txDef.setIsolationLevel(transactionalAnno.isolation().value());
      txDef.setTimeout(transactionalAnno.timeout());
      txDef.setReadOnly(transactionalAnno.readOnly());

      // 不需要回滚的异常
      noRollbackForClasses = transactionalAnno.noRollbackFor();
      // 需要回滚的异常
      rollbackForClasses = transactionalAnno.rollbackFor();

      transactionStatus =
        this.getPlayway().getActivity()
          .getTxManager().getTransaction(txDef);
    }

    try {
      // TODO 分布式锁、分布式限流、分布式事务、分布式调度等注解处理，以及嵌入监控、日志等

      this.doExecute(context);

    } catch (final Throwable e) {

      Throwable t = null;
      if (e instanceof InvocationTargetException) {
        t = ((InvocationTargetException) e).getTargetException();
      } else {
        t = e;
      }

      if (null != transactionStatus) {

        Boolean isRollback = null;

        // Spring事务框架未指定回滚异常类型，仅对RuntimeException和Error执行回滚

        if (t instanceof RuntimeException || t instanceof Error) {
          isRollback = true;

          // 特别指定的异常不需要回滚
          for (Class<? extends Throwable> noRollbackForClass : noRollbackForClasses) {
            if (noRollbackForClass.isAssignableFrom(t.getClass())) {
              isRollback = false;
            }
          }

        } else {
          // 其他异常，如果不指定异常类则不执行回滚
          isRollback = false;

          for (Class<? extends Throwable> rollbackForClass : rollbackForClasses) {
            if (rollbackForClass.isAssignableFrom(t.getClass())) {
              isRollback = true;
            }
          }
        }

        if (null != isRollback && isRollback) {
          txManager.rollback(transactionStatus);
        }
      }

    } finally {

      if (null != transactionStatus) {
        // 根据事务状态，执行提交或回滚操作
        txManager.commit(transactionStatus);
      }

    }

    // TODO ?
    return null;
  }



  protected abstract Object doExecute(ActionCallContext context) throws RuntimeException;

}
