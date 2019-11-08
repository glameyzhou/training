package org.glamey.training.concurrent.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 使用场景:让多个线程互相等待某一事件的发生，然后同时被唤醒
 *
 * @author zhouyang.zhou  2017/1/1.21.
 */
public class CyclicBarrierDemo {

    public static int getRandom() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) {
        int threadCount = 10;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                Thread thread = Thread.currentThread();
                int sleepSeconds = getRandom();
                System.out.println(String.format("%d-%s start, and will sleeping %s", thread.getId(), thread.getName(), sleepSeconds));
                try {
                    TimeUnit.SECONDS.sleep(sleepSeconds);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("%d-%s end", thread.getId(), thread.getName()));
            }).start();
        }
    }
}
