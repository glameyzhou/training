package org.glamey.training.concurrent.thread.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * 产品价格批量修改处理器
 *
 * @author zhouyang.zhou, 2017.04.28.22.
 */
public class ProductPriceModifyProcessor extends RecursiveAction {

  private static final int THRESHOLD = 20;
  private List<Product> products;
  private int low;
  private int high;

  public ProductPriceModifyProcessor(List<Product> products, int low, int high) {
    this.products = products;
    this.low = low;
    this.high = high;
  }

  @Override protected void compute() {

    if(high - low < THRESHOLD) {
      doInner(products, low, high);
      return;
    }

    int middle = (high + low) >>> 1;
    ProductPriceModifyProcessor processor1 = new ProductPriceModifyProcessor(products, low, middle);
    ProductPriceModifyProcessor processor2 = new ProductPriceModifyProcessor(products, middle, high);
    invokeAll(processor1, processor2);
    processor1.join();
    processor2.join();
  }

  private void doInner(List<Product> products, int low, int high) {
    for (int i = low; i < high; i++) {
      Product product = products.get(i);
      product.setPrice(0.1 * product.getPrice());
    }
    Thread thread = Thread.currentThread();
    System.out.println(String.format("thread[id=%d,name=%s],range=[%d-%d)", thread.getId(), thread.getName(), low, high));
  }
}
