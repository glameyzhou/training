package org.glamey.training.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义拒绝策略
 *
 * @author zhouyang.zhou  2016/12/24.22.
 */
public class RejectedDemo {

  public static final int count = 20;
  public static final CountDownLatch LATCH = new CountDownLatch(count);

  public static void main(String[] args) throws InterruptedException {
    ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(2, 5, 1, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
              public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("未执行的任务 -->" + r.toString());
              }
            });

    for (int i = 0; i < 20; i++) {
      threadPoolExecutor.execute(new Runnable() {
        public void run() {
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName());
        }
      });
      LATCH.countDown();
    }
    LATCH.await();
    if (!threadPoolExecutor.isShutdown()) {
      threadPoolExecutor.shutdown();
    }
  }
}
