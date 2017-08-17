package org.glamey.training.algorithm.leetcode;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

/**
 * f(n) = f(n-1) + f(n-2) n >= 2
 *
 * @author zhouyang.zhou. 2017.07.27.19.
 */
public class FibonacciDemo {

  public static void main(String[] args) {
    test0(10);
    System.out.printf("%n");
    test1(10);
    System.out.printf("%n");
    test3(10);
  }

  public static void test0(int n) {
    Stopwatch started = Stopwatch.createStarted();
    int[] arrs = new int[n];
    arrs[0] = arrs[1] = 1;
    for (int i = 2; i < arrs.length; i++) {
      arrs[i] = arrs[i - 1] + arrs[i - 2];
    }
    long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
    System.out.printf("--------->通过数组方式 elapsed = %d", elapsed);
    for (int arr : arrs) {
      System.out.println(arr);
    }
  }

  public static void test1(int n) {
    Stopwatch started = Stopwatch.createStarted();
    int a = 1, b = 1, c;
    System.out.println(a);
    System.out.println(b);
    for (int i = 0; i < n - 2; i++) {
      c = a + b;
      a = b;
      b = c;
      System.out.println(c);
    }
    long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
    System.out.printf("--------->通过下标交换 elapsed = %d", elapsed);
  }

  public static void test3(int n) {
    Stopwatch started = Stopwatch.createStarted();
    for (int i = 0; i < n; i++) {
      System.out.println(test3_(i));
    }
    long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
    System.out.printf("--------->通过递归交换 elapsed = %d", elapsed);
  }

  private static int test3_(int n) {
    if(n <= 2) {
      return 1;
    }
    return test3_(n - 1) + test3_(n - 2);
  }
}
