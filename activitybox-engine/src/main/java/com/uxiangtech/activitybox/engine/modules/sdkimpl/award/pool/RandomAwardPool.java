package com.uxiangtech.activitybox.engine.modules.sdkimpl.award.pool;

import com.uxiangtech.activitybox.common.SpringBeanHolder;
import com.uxiangtech.activitybox.engine.modules.award.AwardService;
import com.uxiangtech.activitybox.engine.modules.sdkimpl.award.option.DegradedThanksAwardOption;
import com.uxiangtech.activitybox.sdk.activity.Activity;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingContext;
import com.uxiangtech.activitybox.sdk.award.AwardDrawingResult;
import com.uxiangtech.activitybox.sdk.attribute.AwardPoolAttribute;
import com.uxiangtech.activitybox.sdk.award.AwardOption;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 概率抽奖规则
 */
public class RandomAwardPool extends AbstractAwardPool {

  private final AwardService awardService;

  public RandomAwardPool(AwardPoolAttribute attribute, Activity activity) {
    super(attribute, activity);
    this.awardService = SpringBeanHolder.getBean(AwardService.class);
  }

  @Override
  public AwardDrawingResult draw(AwardDrawingContext context) {

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

      // 奖项总库存限制
      Long totalStock = hitAwardOption.getTotalStock();

      // 奖品总库存限制 => 配置奖项时，不能超过奖品的总库存

      // 查询奖项中奖记录




    } else {
      // 如果配置存在问题，实在没有命中则降级成谢谢参与
      hitAwardOption = new DegradedThanksAwardOption();
    }


    final AwardDrawingResult awardDrawingResult = new AwardDrawingResultImpl();


    return awardDrawingResult;
  }
}
