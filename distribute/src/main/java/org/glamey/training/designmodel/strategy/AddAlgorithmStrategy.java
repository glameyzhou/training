package org.glamey.training.designmodel.strategy;

/**
 * @author zhouyang.zhou. 2017.08.29.15.
 */
public class AddAlgorithmStrategy implements Strategy {
    @Override
    public void algorithm(StrategyContext context) {
        System.out.println("This is the add() algorithm");
    }
}
