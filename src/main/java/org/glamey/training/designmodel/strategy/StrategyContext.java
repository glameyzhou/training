package org.glamey.training.designmodel.strategy;

/**
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class StrategyContext {

  private Strategy strategy;

  public StrategyContext(Strategy strategy) {
    this.strategy = strategy;
  }

  public void process() {
    this.strategy.process(this);
  }
}
