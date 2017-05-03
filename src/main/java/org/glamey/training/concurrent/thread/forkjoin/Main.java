package org.glamey.training.concurrent.thread.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author zhouyang.zhou, 2017.05.03.22.
 */
public class Main {

  public static void main(String[] args) {
    List<Product> products = ProductGenerator.generate();

    ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    ForkJoinTask task = new ProductPriceModifyProcessor(products, 0, products.size());
    //pool.execute(task);
    pool.invoke(task);
    System.out.println("......");
  }
}
