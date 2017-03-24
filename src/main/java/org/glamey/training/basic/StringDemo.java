package org.glamey.training.basic;

/**
 * @author zhouyang.zhou, 2017.03.21.15.
 */
public class StringDemo {

  public static void main(String[] args) {
    String s1 = new StringBuffer("java").append("world").toString();
    String s1Intern = s1.intern();
    System.out.println(String.format("equal %s", s1Intern.equals(s1)));
    System.out.println(String.format("== %s", s1Intern == s1));
  }
}
