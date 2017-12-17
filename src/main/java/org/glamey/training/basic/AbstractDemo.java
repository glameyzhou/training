package org.glamey.training.basic;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.12.17.
 */
public class AbstractDemo {

  public static class A {
    protected String s = "";

    public A() {
      s += "s ";
      System.out.println("Construct default A");
    }

    public A(String s) {
      this.s += s;
    }
  }

  public static class B extends A {
    public B() {
      System.out.println("construct default B");
    }

    public B(String s) {
      new A("superA");
      System.out.println("construct B string");
    }
  }

  public static class C extends B {
    public C() {
      System.out.println("construct default C");
    }

    public C(String s) {
      new B("superB");
      System.out.println("construct C string");
    }
  }

  public static void main(String[] args) {
    C c = new C("c");
    String s = c.s;
    System.out.println(s);
  }
}
