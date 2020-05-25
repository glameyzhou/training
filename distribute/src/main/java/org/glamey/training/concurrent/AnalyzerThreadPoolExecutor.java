package org.glamey.training.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * https://juejin.im/post/5ec9b64de51d45786973b8fa
 * 线程池，是一种池化思想，类似数据库连接池，JDK常量池等。
 * 用来管理线程，复用线程，新建、销毁线程。
 * <p>
 * 线程池的状态：
 * RUNNING
 *      (1) 状态说明：线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。
 *      (2) 状态切换：线程池的初始化状态是RUNNING。换句话说，线程池被一旦被创建，就处于RUNNING状态，并且线程池中的任务数为0
 *
 * SHUTDOWN
 *      (1) 状态说明：线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。
 *      (2) 状态切换：调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN
 *
 * STOP
 *      (1) 状态说明：线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。
 *      (2) 状态切换：调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP
 *
 * TIDYING 整理清理
 *      (1) 状态说明：当所有的任务已终止，ctl记录的"任务数量"为0，线程池会变为TIDYING状态。
 *              当线程池变为TIDYING状态时，会执行钩子函数terminated()。
 *              terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。
 *      (2) 状态切换：当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。
 *          当线程池在STOP状态下，线程池中执行的任务为空时，就会由STOP -> TIDYING
 *
 * TERMINATED
 *      (1) 状态说明：线程池彻底终止，就变成TERMINATED状态。
 *      (2) 状态切换：线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED
 *
 * RUNNING -> shutdown() -> SHUTDOWN --> 队列为空，线程池中工作线程为0 -->TIDYING --> terminated() --> TERMINATED
 * RUNNING -> shutdownNow() --> STOP ->  线程池中的工作线程为0 -> TIDYING -> terminated() --> TERMINATED
 *
 *
 *
 */
public class AnalyzerThreadPoolExecutor {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), new ThreadFactoryBuilder().build(), new ThreadPoolExecutor.CallerRunsPolicy());


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    executor.shutdownNow();
                }
            }
        }));

        int index = 0;
        while (true) {
            System.out.println(String.format("corePoolSize=%d, maxPoolSize=%d, keepAliveTime=%s, allowCoreThreadTimeout=%s",
                    executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getKeepAliveTime(TimeUnit.SECONDS),
                    executor.allowsCoreThreadTimeOut()));

            TimeUnit.SECONDS.sleep(1);
            index++;
            if (index % 10 == 0) {
                changeTheThreadPoolExecutorProperties_after(executor);
            } else {
                changeTheThreadPoolExecutorProperties_before(executor);
            }

        }

    }

    private static void changeTheThreadPoolExecutorProperties_before(ThreadPoolExecutor executor) {
        executor.setCorePoolSize(2);
        executor.setMaximumPoolSize(2);
        executor.setKeepAliveTime(2, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(true);
        executor.setThreadFactory(new ThreadFactoryBuilder().setNameFormat("before").build());

    }

    private static void changeTheThreadPoolExecutorProperties_after(ThreadPoolExecutor executor) {
        executor.setCorePoolSize(1);
        executor.setMaximumPoolSize(1);
        executor.setKeepAliveTime(1, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(false);
        executor.setThreadFactory(new ThreadFactoryBuilder().setNameFormat("before").build());
    }
}
