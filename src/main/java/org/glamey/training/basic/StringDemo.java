package org.glamey.training.basic;

/**
 * String Constants Pool 字符串常量池
 *
 * @author zhouyang.zhou, 2017.03.21.15.
 */
public class StringDemo {

  public static void main(String[] args) {
    String s = "abcd";
    int length = s.length(); // string char size

    int sLen = s.codePointCount(0, s.length());
    System.out.println("char size = " + length + "   string len " + sLen);
  }
}
