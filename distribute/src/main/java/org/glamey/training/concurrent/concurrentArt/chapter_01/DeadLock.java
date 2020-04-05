package org.glamey.training.concurrent.concurrentArt.chapter_01;

import java.util.concurrent.TimeUnit;

/**
 *
 * 线程死锁，一般代码中不会这样写。通过以下方式避免死锁的发生：
 * 1、避免在同一个线程中获取多个锁。
 * 2、避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源。
 * 3、尝试使用定时锁，给锁添加自动释放时长，例如lock.tryLock(timeout)
 * 4、针对DB，加锁和解锁一定要在一个DB链接中，避免锁释放失败。
 * @author zhouyang.zhou. 2017.08.22.16.
 */
public class DeadLock {

  public static final String A = "a";
  public static final String B = "b";

  public static void main(String[] args) {
    new DeadLock().deadLock();
  }

  private void deadLock() {

    Thread threadA = new Thread(new Runnable() {
      @Override public void run() {
        synchronized (A) {
          try {
            TimeUnit.SECONDS.sleep(2);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          synchronized (B) {
            System.out.println("Thread A");
          }
        }
      }
    });

    Thread threadB = new Thread(new Runnable() {
      @Override public void run() {
        synchronized (B) {
          try {
            TimeUnit.SECONDS.sleep(2);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          synchronized (A) {
            System.out.println("Thread B");
          }
        }
      }
    });

    threadA.start();
    threadB.start();
  }
}

/**
 *
 "Thread-1" #11 prio=5 os_prio=31 tid=0x00007f9517817800 nid=0x5103 waiting for monitor entry [0x00007000098b6000]
 java.lang.Thread.State: BLOCKED (on object monitor)
 at org.glamey.training.concurrent.concurrentArt.chapter_01.DeadLock$2.run(DeadLock.java:45)
 - waiting to lock <0x0000000795800760> (a java.lang.String)
 - locked <0x0000000795800790> (a java.lang.String)
 at java.lang.Thread.run(Thread.java:745)

 "Thread-0" #10 prio=5 os_prio=31 tid=0x00007f951803d000 nid=0x4f03 waiting for monitor entry [0x00007000097b3000]
 java.lang.Thread.State: BLOCKED (on object monitor)
 at org.glamey.training.concurrent.concurrentArt.chapter_01.DeadLock$1.run(DeadLock.java:29)
 - waiting to lock <0x0000000795800790> (a java.lang.String)
 - locked <0x0000000795800760> (a java.lang.String)
 at java.lang.Thread.run(Thread.java:745)



 JVM回比较聪明，自动分析线程信息，将锁信息直接列举出来
 Found one Java-level deadlock:
 =============================
 "Thread-1":
 waiting to lock monitor 0x00007f9516830568 (object 0x0000000795800760, a java.lang.String),
 which is held by "Thread-0"
 "Thread-0":
 waiting to lock monitor 0x00007f9516832f58 (object 0x0000000795800790, a java.lang.String),
 which is held by "Thread-1"

 Java stack information for the threads listed above:
 ===================================================
 "Thread-1":
 at org.glamey.training.concurrent.concurrentArt.chapter_01.DeadLock$2.run(DeadLock.java:45)
 - waiting to lock <0x0000000795800760> (a java.lang.String)
 - locked <0x0000000795800790> (a java.lang.String)
 at java.lang.Thread.run(Thread.java:745)
 "Thread-0":
 at org.glamey.training.concurrent.concurrentArt.chapter_01.DeadLock$1.run(DeadLock.java:29)
 - waiting to lock <0x0000000795800790> (a java.lang.String)
 - locked <0x0000000795800760> (a java.lang.String)
 at java.lang.Thread.run(Thread.java:745)

 Found 1 deadlock.
 */
