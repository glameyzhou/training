package org.glamey.training.concurrent.thread.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author zhouyang.zhou, 2017.05.03.22.
 */
public class Main {
    private static final ForkJoinPool POOL = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) {
        List<Product> products = ProductGenerator.generate();

        ForkJoinTask task = new ProductPriceModifyProcessor(products, 0, products.size());
        //POOL.execute(task);
        POOL.invoke(task);
        System.out.println("......");
    }
}
