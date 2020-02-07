package org.glamey.training.designmodel.spi;

/**
 * @author zhouyang.zhou  2017/2/2.20.
 */
@FunctionalInterface
public interface SpiOperation {
    int operate(int a, int b);

    default String getDesc() {
        return this.getClass().getCanonicalName();
    }
}
