package com.uxiangtech.activitybox.engine.modules.award.pool;

import com.uxiangtech.activitybox.engine.modules.award.limiter.AwardDrawingLimitedException;
import com.uxiangtech.activitybox.engine.modules.award.limiter.AwardDrawingLimiter;
import com.uxiangtech.activitybox.engine.modules.award.limiter.AwardDrawingLimiterContext;
import com.uxiangtech.activitybox.engine.modules.award.limiter.AwardDrawingLimiterPipeline;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option.DegradedThanksAwardOption;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool.AbstractAwardPool;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.award.AwardOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 概率抽奖规则
 */
public class RandomAwardPool extends AbstractAwardPool {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(RandomAwardPool.class);

  private final AwardDrawingLimiterPipeline awardDrawingLimiterPipeline;

  public RandomAwardPool(AwardPoolAttribute attribute, Activity activity) {
    super(attribute, activity);
    this.awardDrawingLimiterPipeline = this.buildAwardDrawingLimiterPipeline();
  }

  /**
   * 构建抽奖限制链
   * @return
   */
  private AwardDrawingLimiterPipeline buildAwardDrawingLimiterPipeline() {
    final ServiceLoader<AwardDrawingLimiter> serviceLoader = ServiceLoader.load(AwardDrawingLimiter.class);
    final List<AwardDrawingLimiter> awardDrawingLimiters = new ArrayList<>();
    for (Iterator<AwardDrawingLimiter> iterator = serviceLoader.iterator(); iterator.hasNext();) {
      awardDrawingLimiters.add(iterator.next());
    }
    return new AwardDrawingLimiterPipeline(awardDrawingLimiters);
  }

  @Override
  public AwardOption chooseAwardOption(AwardDrawingContext context) {

    // 生成随机概率值
    final ThreadLocalRandom random = ThreadLocalRandom.current();
    final BigDecimal ranProb = new BigDecimal(random.nextDouble());

    // 命中的奖项
    AwardOption hitAwardOption = null;
    // 命中奖项标记
    boolean isHit = false;

    BigDecimal sumProb = BigDecimal.ZERO;
    for (AwardOption awardOption : getAwardOptions()) {
      // 奖项概率
      BigDecimal optionProb = awardOption.getProbability();

      sumProb = sumProb.add(optionProb);

      // 概率区间左闭右开，比如 1-0.3, 2-0.5, 3-0.2, 三个奖项及其概率区间分别为[0, 0.3), [0.3, 0.8), [0.8, 1.0)，如果概率出现0.3000000
      if (ranProb.compareTo(sumProb) < 0) {
        hitAwardOption = awardOption;
        isHit = true;
      }
    }

    // 命中则检查库存以及奖池限制
    if (isHit) {
      // 奖品总库存限制 => 配置奖项时，不能超过奖品的总库存
      final AwardDrawingLimiterContext awardDrawingLimiterContext =
        new AwardDrawingLimiterContext(hitAwardOption);
      try {
        this.awardDrawingLimiterPipeline.doPipe(awardDrawingLimiterContext);
      } catch (final AwardDrawingLimitedException e) {
        LOGGER.warn("【抽奖引擎】活动ID={},用户ID={} >>> 随机奖池抽奖，命中风控规则={},",
          context.getActivityId(), context.getUserId(), e.getMessage());
        hitAwardOption = new DegradedThanksAwardOption();
      }
    } else {
      // 如果配置存在问题，实在没有命中则降级成谢谢参与
      hitAwardOption = new DegradedThanksAwardOption();
    }

    return hitAwardOption;
  }
}
