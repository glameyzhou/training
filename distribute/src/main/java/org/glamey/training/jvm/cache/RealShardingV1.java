package org.glamey.training.jvm.cache;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * 内存共享
 * 在两个 long 类型的变量之间再加 7 个 long 类型
 *
 * 但是多余的7个long变量，可能被JVM优化掉
 * @author yang.zhou 2020.01.19.15
 */
public class RealShardingV1 {


    /**
     * 552
     * 530
     * 451
     * 421
     * 413
     * 421
     * 425
     * 412
     * 413
     * 414
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    static class Object {
        static volatile long a;
        static long p1, p2, p3, p4, p5, p6, p7;
        static volatile long b;
    }

    private static final int COUNT = 100000000;

    public static void test() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                Object.a++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                Object.b++;
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
