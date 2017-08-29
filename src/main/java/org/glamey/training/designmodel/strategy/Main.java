package org.glamey.training.designmodel.strategy;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;

/**
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class Main {

  public static void main(String[] args) {

    final ImmutableMap<String, Strategy> strategyMapping = ImmutableMap.<String, Strategy>builder()
        .put("apple", new AppleStrategy())
        .put("banana", new BananaStrategy())
        .build();

    ImmutableSet<Map.Entry<String, Strategy>> entries = strategyMapping.entrySet();
    for (Map.Entry<String, Strategy> entry : entries) {
      StrategyContext context = new StrategyContext(entry.getValue());
      context.process();
    }


    /*StrategyContext context = new StrategyContext(new Strategy() {
      @Override public void process(StrategyContext context) {
        System.out.println("use the demo strategy to do...");
      }
    });
    context.process();*/
  }
}
