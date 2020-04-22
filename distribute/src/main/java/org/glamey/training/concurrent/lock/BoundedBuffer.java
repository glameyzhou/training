package org.glamey.training.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞的有界队列，其实内部实现就是 {@link java.util.concurrent.ArrayBlockingQueue}
 * {@link java.util.concurrent.locks.Condition}
 */
public class BoundedBuffer {

    final Lock lock;
    final Condition notFull;
    final Condition notEmpty;
    final int capacity;
    final Object[] items;
    int putIndex, takeIndex, count;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }


    public void put(Object e) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putIndex] = e;
            if (++putIndex == count) {
                putIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object ret = items[takeIndex];
            if (++takeIndex == count) {
                takeIndex = 0;
            }
            --count;
            notFull.signalAll();
            return ret;
        } finally {
            lock.unlock();
        }
    }
}
