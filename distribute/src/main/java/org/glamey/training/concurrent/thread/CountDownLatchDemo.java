package org.glamey.training.concurrent.thread;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 主要用来:让某一线程等待多个线程的状态，然后该线程被唤醒
 *
 * @author zhouyang.zhou  2017/1/1.21.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int threadCount = 10;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                Thread thread = Thread.currentThread();
                System.out.println(String.format("%d-%s start...", thread.getId(), thread.getName()));
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("%d-%s end...", thread.getId(), thread.getName()));
                latch.countDown();
            }).start();
        }
        latch.await();
        stopwatch.stop();
        System.out.println(String.format(" all Thread task is finish, cost time = %d ms", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }
}
