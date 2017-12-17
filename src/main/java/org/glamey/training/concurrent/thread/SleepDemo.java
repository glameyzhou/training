package org.glamey.training.concurrent.thread;

/**
 * T和read中的静态方法，休眠指定时间，交出CPU来执行其他任务，但是不会释放当前对象锁。 其他线程无法抢占当前线程的锁。
 *
 * <p></p> 下面的例子就说明了，第二个线程务必会等第一个线程释放锁才可以执行。
 *
 * @author zhouyang.zhou.
 * @date 2017.12.17.21.
 */
public class SleepDemo {

  private static int ID = 100;
  private static final Object LOCK = new Object();

  public static void main(String[] args) {
    Thread t1 = new Thread(new Demo(), "thread_1");
    Thread t2 = new Thread(new Demo(), "thread_2");
    t1.start();
    t2.start();
  }

  static class Demo implements Runnable {

    @Override public void run() {
      synchronized (LOCK) {
        ++ID;
        System.out.println(" i --> start " + ID);
        System.out.printf("%s --> 开始休眠 %ds\n", Thread.currentThread().getName(), 3);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.printf("%s --> 结束休眠 %ds\n", Thread.currentThread().getName(), 3);
        ++ID;
        System.out.println(" i --> end " + ID);
      }
    }
  }
}
