package org.glamey.training.jvm.oom;

/**
 * StackOverflowError,线程请求的栈深度大于虚拟机所允许的深度。
 *
 * -Xss=128k -Xms=100m -Xmx=100m -XX:NewSize=10m -XX:PermSize=10m，<p>
 *   现在默认都是1M，我们通过递归的方式，不断的向虚拟机栈中添加上下文信息。
 *
 * stack length = 9939
 *
 * @author zhouyang.zhou. 2017.09.14.09.
 */
public class StackOverFlowErrorDemo {

  private int length = 0;

  public void stackLeak() {
    System.out.println(length);
    length++;
    stackLeak();
  }

  public static void main(String[] args) {
    StackOverFlowErrorDemo demo = new StackOverFlowErrorDemo();
    demo.stackLeak();
  }
}
