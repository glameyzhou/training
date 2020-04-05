package org.glamey.training.jvm.loader;

/**
 *
 * http://www.cnblogs.com/javaee6/p/3714716.html
 * @author zhouyang.zhou  2017/2/2.22.
 */
public class ClassInitDemo {
  private static ClassInitDemo demo = new ClassInitDemo();

  public static int a;
  public static int b = 0;

  private ClassInitDemo() {
    a ++;
    b ++;
  }

  public static ClassInitDemo getDemo() {
    return demo;
  }

}
