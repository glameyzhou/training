package org.glamey.training.designmodel.strategy;

/**
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class Main {

  public static void main(String[] args) {

    StrategyContext context = new StrategyContext(new Strategy() {
      @Override public void process(StrategyContext context) {
        System.out.println("use the demo strategy to do...");
      }
    });
    context.process();
  }
}
