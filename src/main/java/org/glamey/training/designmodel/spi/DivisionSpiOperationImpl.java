package org.glamey.training.designmodel.spi;

/**
 * @author zhouyang.zhou  2017/2/2.21.
 */
public class DivisionSpiOperationImpl implements SpiOperation {
  @Override public int operate(int a, int b) {
    if (a == 0 || b == 0) {
      return 0;
    }
    return a / b;
  }
}
