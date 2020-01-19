package org.glamey.training.jvm.cache;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * JVM新增启动参数 -XX:-RestrictContended
 * 推荐使用
 * @author yang.zhou 2020.01.19.15
 */
public class RealSharingV3 {

    /**
     * 598
     * 546
     * 524
     * 493
     * 471
     * 535
     * 511
     * 468
     * 463
     * 461
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    @sun.misc.Contended
    static class CustomLong {
        volatile long value;
    }

    static class Object {
        static CustomLong a = new CustomLong();
        static CustomLong b = new CustomLong();
    }

    private static final int COUNT = 100000000;

    public static void test() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                Object.a.value++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                Object.b.value++;
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
