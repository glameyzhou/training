package org.glamey.training.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou.2016.10.08 14.
 */
public class SupplierMain {
    public static void main(String[] args) throws InterruptedException {
        Supplier<Integer> memoizeSupplier = Suppliers.memoize(new Supplier<Integer>() {
            public Integer get() {
                System.out.println("memoizeSupplier ...");
                return 100;
            }
        });
        for (int i = 0; i < 10; i++) {
            Integer integer = memoizeSupplier.get();
            System.out.println(integer);
        }

        Supplier<Integer> expireSupplier = Suppliers.memoizeWithExpiration(() -> {
            System.out.println("expireSupplier ...");
            return 100;
        }, 5, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            System.out.println(expireSupplier.get());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
