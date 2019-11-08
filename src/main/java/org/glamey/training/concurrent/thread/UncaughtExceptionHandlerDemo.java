package org.glamey.training.concurrent.thread;

import com.google.common.base.Throwables;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 针对线程中，出现的未捕获异常处理。
 * <p>
 * <p>
 * 测试：设置10个线程，并发执行某一个任务（其实就是间隔2秒打印字符串），假设某一个线程出现异常，那么将有统一的异常handler来处理。
 *
 * @author zhouyang.zhou. 2017.09.25.16.
 */
public class UncaughtExceptionHandlerDemo {

    final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(5,
            10,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            new NamedThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    final static int COUNT = 10;
    final static CountDownLatch LATCH = new CountDownLatch(COUNT);

    /**
     * 设置未捕获异常处理类
     */
    private static class NamedThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            //thread.setUncaughtExceptionHandler(new DemoUncaughtExceptionHandler());
            return thread;
        }
    }

    /**
     * 针对未捕获异常的处理
     */
    private static class DemoUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("%s,s 异常捕获处理中\n", t.getName(), Throwables.getStackTraceAsString(e));
            LATCH.countDown();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            Runnable command = new Runnable() {
                @Override
                public void run() {
                    new UncaughtExceptionHandlerDemo().process();
                }
            };
            EXECUTOR.execute(command);
        }

        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (EXECUTOR != null && !EXECUTOR.isShutdown()) {
            EXECUTOR.shutdown();
        }
    }

    public void process() {
        Thread thread = Thread.currentThread();
        Random random = new Random();
        int index = random.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%d_%s-->开始\n", thread.getId(), thread.getName());
        if (index % 2 == 0) {
            throw new RuntimeException(String.format("%d_%s-->异常", thread.getId(), thread.getName()));
        }
        System.out.printf("%d_%s-->完成\n", thread.getId(), thread.getName());
        LATCH.countDown();
    }
}
