package org.glamey.training.concurrent.thread;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * yield 指令也会暂停当前线程，只不过会让当前线程重新处于RUNNABLE状态，交出CPU供相同优先级的线程使用。
 * [no yield] elapsed --> 2198 微秒, count --> 10000001
 * [yield] elapsed --> 379314 微秒, count --> 10000001
 *
 * @author zhouyang.zhou.
 * @date 2017.12.17.21.
 */
public class YieldDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new Demo());
        thread.start();

        Thread yieldDemo = new Thread(new DemoYield());
        yieldDemo.start();
    }

    static class Demo implements Runnable {
        @Override
        public void run() {
            Stopwatch started = Stopwatch.createStarted();
            int count = 1;
            for (int i = 0; i < 1000000; i++) {
                count += 10;
            }
            long elapsed = started.elapsed(TimeUnit.MICROSECONDS);
            System.out.printf("[no yield] elapsed --> %d 微秒, count --> %d\n", elapsed, count);
        }
    }

    static class DemoYield implements Runnable {
        @Override
        public void run() {
            Stopwatch started = Stopwatch.createStarted();
            int count = 1;
            for (int i = 0; i < 1000000; i++) {
                count += 10;

                //System.out.println(Thread.currentThread().getState()); //RUNNING

                Thread.yield();

                //System.out.println(Thread.currentThread().getState());//RUNNABLE
            }
            long elapsed = started.elapsed(TimeUnit.MICROSECONDS);
            System.out.printf("[yield] elapsed --> %d 微秒, count --> %d\n", elapsed, count);
        }
    }
}
