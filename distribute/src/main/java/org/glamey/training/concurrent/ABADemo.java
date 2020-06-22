package org.glamey.training.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用AtomicInteger进行数据ABA操作，没有问题。 使用AtomicStampedReference进行ABA操作，更新失败
 *
 * @author zhouyang.zhou.
 * @date 2017.12.07.10.
 */
public class ABADemo {

    private static AtomicInteger integer = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws Exception {
        //AtomicInteger ABA
        Thread t1 = new Thread(() -> {
            integer.compareAndSet(100, 101);
            integer.compareAndSet(101, 100);
        });

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = integer.compareAndSet(100, 101);
            System.out.println("atomic --> " + result);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //AtomicStampedReference
        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
            reference.compareAndSet(101, 100, reference.getStamp(), reference.getStamp() + 1);
        });

        Thread t4 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = reference.getStamp();
            boolean result = reference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println("reference --> " + result);
        });

        t3.start();
        t4.start();
    }
}
