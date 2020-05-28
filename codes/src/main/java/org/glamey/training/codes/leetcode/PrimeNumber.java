package org.glamey.training.codes.leetcode;

/**
 * @author zhouyang.zhou. 2017.08.22.11.
 */
public class PrimeNumber {

  public static void main(String[] args) {

    System.out.println("----------------[1]------------------->");
    prime_1(100);

    System.out.println("----------------[2]------------------->");
    prime_2(100);

    System.out.println("----------------[3]------------------->");
    prime_3(100);
  }

  /**
   * 第一种方式：遍历每一个数字，看是否可以被整除
   *
   * @param number
   */
  public static void prime_1(int number) {
    boolean isPrime = true;
    for (int i = 2; i <= number; i++) {
      for (int j = 2; j < i; j++) {
        if(i % j == 0) {
          isPrime = false;
          break;
        }
      }
      if(isPrime) {
        System.out.println(i);
      }
      isPrime = true;
    }
  }

  /**
   * 第二种方式：可以把偶数全部排除掉，这样少遍历一半的数字
   *
   * @param number
   */
  public static void prime_2(int number) {
    boolean isPrime = true;
    for (int i = 2; i <= number; i++) {
      if(i % 2 == 0 && i != 2) {
        continue;
      }

      for (int j = 3; j < i; j += 2) {
        if(i % j == 0) {
          isPrime = false;
          break;
        }
      }

      if(isPrime) {
        System.out.println(i);
      }
      isPrime = true;
    }
  }

  /**
   * 第三种方式：通过对每个数字进行开根处理(排除掉偶数)
   *
   * @param number
   */
  public static void prime_3(int number) {
    boolean isPrime = true;
    for (int i = 2; i <= number; i++) {
      if(i % 2 == 0 && i != 2) {
        continue;
      }

      int tmp = (int) Math.sqrt(i);
      for (int j = 2; j <= tmp; j += 2) {
        if(i % j == 0) {
          isPrime = false;
          break;
        }
      }
      if(isPrime) {
        System.out.println(i);
      }
      isPrime = true;
    }
  }
}
