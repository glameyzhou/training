package org.glamey.training.jvm.cache;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * 新建自定义的long
 *
 * 但是多余的7个long变量，可能被JVM优化掉
 * @author yang.zhou 2020.01.19.15
 */
public class RealSharingV2 {


    /**
     * 581
     * 551
     * 457
     * 445
     * 469
     * 460
     * 417
     * 430
     * 424
     * 407
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    static class CustomLong {
        volatile long value;
        long p1, p2, p3, p4, p5, p6, p7;
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
