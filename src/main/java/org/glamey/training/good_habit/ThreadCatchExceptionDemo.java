package org.glamey.training.good_habit;

/**
 * @author zhouyang.zhou. 2017.09.25.16.
 */
public class ThreadCatchExceptionDemo implements Runnable {

  public ThreadCatchExceptionDemo() {
    Thread thread = new Thread(this, "异常捕获线程");
    thread.setUncaughtExceptionHandler(new SocketExceptionHandler());
    thread.start();
  }

  @Override public void run() {

    for (int i = 0; i < 10; i++) {
      try {
        System.out.println("系统运行中...." + i);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    throw new RuntimeException("系统崩溃...");
  }

  private class SocketExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      System.out.println("系统崩溃，异常捕获，重启中...");
      new ThreadCatchExceptionDemo();
      System.out.println("重启完毕....");
    }
  }

  public static void main(String[] args) {
    Thread thread_a = new Thread(new ThreadCatchExceptionDemo());
    Thread thread_b = new Thread(new ThreadCatchExceptionDemo());

    thread_a.start();
    thread_b.start();
  }
}
