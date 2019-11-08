package org.glamey.training.concurrent.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.17.19.
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        //joinTimeout();
        //joinNoTimeout();
        //multiThreadJoin();
        multiThreadWaitMainThread();
    }

    public static void joinTimeout() throws InterruptedException {
        System.out.println("我是主线程，现在开始");
        Thread demo = new Thread(new Demo(), "joinTimeout");
        demo.start();
        demo.join(100);
        System.out.println("我是主线程，已经结束");
    }

    public static void joinNoTimeout() throws InterruptedException {
        System.out.println("我是主线程，现在开始");
        Thread demo = new Thread(new Demo(), "joinNoTimeout");
        demo.start();
        demo.join();
        System.out.println("我是主线程，已经结束");
    }

    public static void multiThreadJoin() throws InterruptedException {
        Thread d1 = new Thread(new Demo(), "thread_1");
        Thread d2 = new Thread(new Demo(), "thread_2");

        d1.start();
        System.out.println("d1 start...");
        d1.join();

        d2.start();
        System.out.println("d2 start...");
        d2.join();

        System.out.println("主线程结束");
    }

    public static void multiThreadWaitMainThread() throws InterruptedException {
        Thread d1 = new Thread(new Demo(), "thread_1");
        Thread d2 = new Thread(new Demo(), "thread_2");

        d1.start();
        System.out.println("d1 start...");

        d2.start();
        System.out.println("d2 start...");

        // the main thread waiting
        System.out.println("主线程等待");
        d1.join();
        d2.join();
        System.out.println("主线程等待结束");
    }

    static class Demo implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.printf("%s --> start...\n", thread.getName());
            for (int i = 0; i < 5; i++) {
                int sleepTime = new Random().nextInt(3);
                for (int j = 0; j < sleepTime; j++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%s --> .\n", thread.getName());
                }
            }
            System.out.printf("%s --> finish...\n", thread.getName());
        }
    }
}
