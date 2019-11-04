package org.glamey.training.designmodel.strategy;

/**
 * @author yang.zhou 2019.11.04.16
 */
public class AbsAlgorithmStrategy implements Strategy {
    @Override
    public void algorithm(StrategyContext context) {
        System.out.println("This is the abs() algorithm");
    }
}
