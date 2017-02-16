package org.glamey.training.jvm;

import java.lang.reflect.InvocationTargetException;

/**
 * Memory barrier
 * happen before
 * as if serial
 * @author zhouyang.zhou. 2016.12.26.15.
 */
public class JvmExecutorDemo {

  public static void main(String[] args)
          throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
    A.execute();
    A a = new A();
    a.foo();
    IFoo b = new B();
    b.foo();

    Integer.parseInt("a");
  }

  static class A {
    public static void execute() {
      System.out.println("A.execute");
    }

    public int foo() {
      return 1 + 2;
    }
  }

  interface IFoo {
    public void foo();
  }

  static class B implements IFoo {

    @Override public void foo() {
      System.out.println("B.foo");
    }
  }
}
