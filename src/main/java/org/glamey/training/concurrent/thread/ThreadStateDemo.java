package org.glamey.training.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 *
 * 线程状态 start,runnable,running,block,waiting,time-waiting,terminal
 * 不能从terminal再次转为start，因为线程已经消亡，需要重新再次new thread
 * 例如：T1=新建开启线程 T2=线程执行任务，T3=线程关闭消亡。
 * 其他T2可以通过程序来优化，T1 T3是没法优化，因此我们可以借助线程池来优化T1T3占用的时长（线程池中的线程有两个状态：可运行，等待）。
 * @author zhouyang.zhou. 2017.09.25.17.
 */
public class ThreadStateDemo {
  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new Runnable() {
      @Override public void run() {
        System.out.println("...running...");
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Thread thread = new Thread(runnable);

    thread.start();

    while (thread.getState() != Thread.State.TERMINATED) {
      TimeUnit.MILLISECONDS.sleep(500);
      System.out.println(".");
    }
    System.out.println("线程已经结束，再次开始...");
    thread.start();
  }
}
