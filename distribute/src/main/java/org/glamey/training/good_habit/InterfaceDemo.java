package org.glamey.training.good_habit;

/**
 * 接口中做实现，<B>千万不要</B>出现这样的写法。
 *
 * @author zhouyang.zhou. 2017.09.22.18.
 */
public class InterfaceDemo {

  public static void main(String[] args) {
    B.a.doSomething();
    ;
  }

  interface A {
    void doSomething();
  }

  interface B {
    static final A a = new A() {
      @Override public void doSomething() {
        System.out.println("接口中实现的方法");
      }
    };
  }
}
