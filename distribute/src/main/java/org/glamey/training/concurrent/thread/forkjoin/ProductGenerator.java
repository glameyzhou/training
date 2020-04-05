package org.glamey.training.concurrent.thread.forkjoin;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhouyang.zhou, 2017.04.28.22.
 */
public class ProductGenerator {
    public static List<Product> generate() {
        List<Product> list = Lists.newArrayListWithExpectedSize(100);
        for (int i = 0; i < 100; i++) {
            Product p = new Product();
            p.setName("product_name_" + i);
            p.setPrice(100D);
            list.add(p);
        }
        return list;
    }
}
