package org.glamey.training.loader;

/**
 * @author zhouyang.zhou  2017/2/2.22.
 */
public class ClassInitDemoTest {
  public static void main(String[] args) {
    ClassInitDemo demo = ClassInitDemo.getDemo();
    System.out.println(demo.a);
    System.out.println(demo.b);
  }
}
