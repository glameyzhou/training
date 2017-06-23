package org.glamey.training.algorithm.demo.produce_consume_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo2Machine {

    private static final int threshold = 10;
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce(String name) throws InterruptedException {
        lock.lock();
        try {
            while (count == threshold) {
                System.out.printf("the queue[%s] is full[%d], please waiting...\r\n", name, count);
                condition.await();
            }
            count++;
            System.out.printf("produce[%s] ok, count=[%d]\r\n", name, count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume(String name) throws InterruptedException {

        lock.lock();
        try {
            while (count == 0) {
                System.out.printf("the queue[%s] is empty[%d], please waiting...\r\n", name, count);
                condition.wait();
            }

            count--;
            System.out.printf("consume[%s] ok, count=[%d]\r\n", name, count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
