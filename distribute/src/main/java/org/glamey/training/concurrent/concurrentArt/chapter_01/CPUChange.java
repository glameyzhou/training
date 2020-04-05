package org.glamey.training.concurrent.concurrentArt.chapter_01;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * 测试CPU切换后的损耗
 *
 * @author zhouyang.zhou. 2017.08.22.15.
 */
public class CPUChange {

  public static final long count = 90000L;

  public static void main(String[] args) throws InterruptedException {
    concurrency();
    serial();
  }

  private static void concurrency() throws InterruptedException {
    Stopwatch stopwatch = Stopwatch.createStarted();
    Thread thread = new Thread(new Runnable() {
      @Override public void run() {
        int a = 0;
        for (int i = 0; i < count; i++) {
          a += 5;
        }
      }
    });
    thread.start();

    int b = 0;
    for (int i = 0; i < count; i++) {
      b--;
    }
    thread.join();
    long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
    System.out.printf("elapsed=%d, b=%d\n", elapsed, b);
  }

  private static void serial() {
    Stopwatch stopwatch = Stopwatch.createStarted();
    int a = 0;
    for (int i = 0; i < count; i++) {
      a += 5;
    }
    int b = 0;
    for (int i = 0; i < count; i++) {
      b--;
    }
    long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
    System.out.printf("elapsed=%d, a=%d,b=%d\n", elapsed, a, b);
  }
}
