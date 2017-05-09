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
  private int startId;
  private int endId;

  public ProductPriceModifyProcessor(List<Product> products, int startId, int endId) {
    this.products = products;
    this.startId = startId;
    this.endId = endId;
  }

  @Override protected void compute() {

    if(endId - startId < THRESHOLD) {
      doInner(products, startId, endId);
      return;
    }

    int middle = (endId + startId) >>> 1;
    ProductPriceModifyProcessor processor1 = new ProductPriceModifyProcessor(products, startId, middle);
    ProductPriceModifyProcessor processor2 = new ProductPriceModifyProcessor(products, middle, endId);
    invokeAll(processor1, processor2);
    processor1.join();
    processor2.join();
  }

  private void doInner(List<Product> products, int startId, int endId) {
    for (int i = startId; i < endId; i++) {
      Product product = products.get(i);
      product.setPrice(0.1 * product.getPrice());
    }
    Thread thread = Thread.currentThread();
    System.out.println(String.format("thread[id=%d,name=%s],range=[%d-%d)", thread.getId(), thread.getName(), startId, endId));
  }
}
