package org.glamey.training.concurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouyang.zhou. 2017.09.25.17.
 */
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {

    /*for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) {
        Thread thread = new Thread(new UnSafeThread());
        thread.start();
      }
      TimeUnit.SECONDS.sleep(2);
      System.out.println(UnSafeThread.count);
      UnSafeThread.count = 0;
    }*/

    /*for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) {
        Thread thread = new Thread(new UnSafeVolatile());
        thread.start();
      }
      TimeUnit.SECONDS.sleep(3);
      System.out.println(UnSafeVolatile.count);
      UnSafeVolatile.count = 0;
    }*/


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                Thread thread = new Thread(new UnSafeAtomic());
                thread.start();
            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println(UnSafeAtomic.count.get());
            UnSafeAtomic.count.set(0);
        }

    }

    /**
     * 由于count是共享变量，所以在多线程环境之后， 会出现当前线程操作后的值没有来得及写入内存，其他线程使用的还是老的值，因此总体计算下来的count会小于10000
     * <p>
     * 循环打印结果如下：
     * 10000
     * 10000
     * 9967
     * 10000
     * 10000
     * 10000
     * 10000
     * 10000
     * 10000
     * 10000
     */
    static class UnSafeThread implements Runnable {
        private static int count;

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                count++;
            }
        }
    }


    static class UnSafeVolatile implements Runnable {
        private static volatile int count;

        @Override
        public void run() {
            count++;
        }
    }


    static class UnSafeAtomic implements Runnable {
        private static AtomicInteger count = new AtomicInteger();

        @Override
        public void run() {
            count.incrementAndGet();
        }
    }
}
