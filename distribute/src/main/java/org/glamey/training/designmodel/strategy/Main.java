package org.glamey.training.designmodel.strategy;

/**
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class Main {

    public static void main(String[] args) {
        new StrategyContext(new AddAlgorithmStrategy()).algorithm();
        new StrategyContext(new AbsAlgorithmStrategy()).algorithm();
    }
}