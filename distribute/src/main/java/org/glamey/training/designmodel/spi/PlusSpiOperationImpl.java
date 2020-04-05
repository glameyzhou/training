package org.glamey.training.designmodel.spi;

/**
 * @author zhouyang.zhou  2017/2/2.21.
 */
public class PlusSpiOperationImpl implements SpiOperation {
    @Override
    public int operate(int a, int b) {
        return a + b;
    }
}
