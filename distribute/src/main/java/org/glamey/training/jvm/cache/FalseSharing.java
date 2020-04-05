package org.glamey.training.jvm.cache;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * 伪共享
 * <p>
 * <p>
 * 缓存行大概有64字节，可以缓存8个long。下面测试伪共享.
 *
 * CPU计算的a的时候，加载了b,在计算完毕a后，其他包含A的缓存行均失效，因为其他缓存中A的值已经不是最新的了。
 * 而读取B的时候，发现A已经失效，需要从主内存中重新加载。
 *
 * 故当前操作大概4S,比较耗时。
 *
 * @author yang.zhou 2020.01.19.14
 */
public class FalseSharing {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 耗时 ms
         * 3940
         * 3720
         * 3633
         * 3554
         * 3578
         * 3630
         * 3645
         * 3401
         * 3250
         * 3259
         */
        for (int i = 0; i < 10; i++) {
            FalseSharing.test();
        }
    }

    static class Object {
        static volatile long a;
        static volatile long b;
    }

    private static final int COUNT = 100000000;

    public static void test() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                Object.a ++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                Object.b ++;
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
