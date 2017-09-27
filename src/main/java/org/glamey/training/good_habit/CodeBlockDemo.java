package org.glamey.training.good_habit;

/**
 * 代码块
 * 普通代码块：就是在方法后面使用"{}"括起来的代码片段，它不能单独运行，必须通过方法名调用执行；
 * 静态代码块：在类中使用static修饰，并用"{}"括起来的代码片段，用于静态变量初始化或对象创建前的环境初始化。
 * 同步代码块：使用synchronized关键字修饰，并使用"{}"括起来的代码片段，它表示同一时间只能有一个线程进入到该方法块中，是一种多线程保护机制。
 * 构造代码块：在类中没有任何前缀和后缀,并使用"{}"括起来的代码片段
 *
 *
 * 我们知道代码块不具有独立执行能力，那么编译器是如何处理构造代码块的呢？很简单，编译器会把构造代码块插入到每个构造函数的最前端.
 * @author zhouyang.zhou. 2017.09.26.10.
 */
public class CodeBlockDemo {

  /**
   * 统计对象实例化次数，直接在代码构造块中实现接口，因此每次构造器（有无参数的构造器）的调用，构造代码块必然会执行一次。
   */
  private static int objectInstanceCount;

  public CodeBlockDemo() {
    System.out.println("默认构造器");
  }

  public CodeBlockDemo(String name) {
    System.out.println("带参数的构造器" + name);
  }

  static {
    System.out.println("静态代码块");
  }

  {
    System.out.println("构造代码块");
    ++objectInstanceCount;
  }


  public int getObjectInstanceCount() {
    return objectInstanceCount;
  }

  public static void main(String[] args) {
    CodeBlockDemo demo = new SubClass();
    new CodeBlockDemo();
    new CodeBlockDemo("xx");
    System.out.println(CodeBlockDemo.objectInstanceCount);

    /**
     * 输出的结果是：
     *
     * 静态代码块
     * 静态代码块-子类
     * 构造代码块
     * 默认构造器
     * 构造代码块-子类
     * 默认构造器-子类
     */
  }


  static class SubClass extends CodeBlockDemo {
    static {
      System.out.println("静态代码块-子类");
    }

    {
      System.out.println("构造代码块-子类");
    }

    public SubClass() {
      System.out.println("默认构造器-子类");
    }
  }

}
