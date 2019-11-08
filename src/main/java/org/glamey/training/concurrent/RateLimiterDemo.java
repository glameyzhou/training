package org.glamey.training.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou. 2017.07.25.11.
 */
public class RateLimiterDemo {

    private static final RateLimiter LIMITER = RateLimiter.create(2, 2, TimeUnit.SECONDS); //设置每秒可以处理的总量

    public void test() {
        for (int i = 0; i < 10; i++) {
            double acquire = LIMITER.acquire();
            System.out.printf("强制等待的时间 %s%n", acquire);
            final int index = i;
            new Thread(() -> doIt(index)).start();
        }
    }

    private void doIt(int i) {
        int sleepTime = 2;
        try {
            System.out.printf("开始处理[%d]，thread=%s,大概持续%d秒%n", i, Thread.currentThread().getName(), sleepTime);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RateLimiterDemo demo = new RateLimiterDemo();
        demo.test();
    }
}
