package org.glamey.training.algorithm.leetcode;

/**
 * 阶乘
 *
 * @author zhouyang.zhou. 2017.07.18.20.
 */
public class FactorialDemo {

  public Integer factorial(Integer n) {
    if(n <= 1) {
      return n;
    }
    return n * factorial(n - 1);
  }

  public static void main(String[] args) {
    FactorialDemo demo = new FactorialDemo();
    for (int i = 0; i < 5; i++) {
      Integer result = demo.factorial(i);
      System.out.printf("%d-->%d%n", i, result);
    }
  }
}
