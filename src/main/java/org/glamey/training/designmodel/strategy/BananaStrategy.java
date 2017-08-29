package org.glamey.training.designmodel.strategy;

/**
 * @author zhouyang.zhou. 2017.08.29.15.
 */
public class BananaStrategy implements Strategy {
  @Override public void process(StrategyContext context) {
    System.out.println("This is the banana strategy...");
  }
}
