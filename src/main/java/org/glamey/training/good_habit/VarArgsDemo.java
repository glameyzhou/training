package org.glamey.training.good_habit;

/**
 * overload 重载，
 *
 * 因为int是一个原生数据类型，而数组本身是一个对象，编译器想要"偷懒",于是它会从最简单的开始"猜想"，只要符合编译条件的即可通过，于是就出现了此问题。 问题阐述清楚了，为了让我们的程序能被"人类"看懂，还是慎重考虑变长参数的方法重载吧，否则让人伤脑筋不说，说不定哪天就陷入这类小陷阱里了。
 *
 * @author zhouyang.zhou. 2017.09.22.16.
 */
public class VarArgsDemo {

  public float calPrice(int originPrice, int discount) {
    float f = originPrice * discount / 100f;
    System.out.printf("simple cal price : %s\n", f);
    return f;
  }

  public float calPrice(int originPrice, int... discounts) {
    float f = originPrice;
    for (int discount : discounts) {
      f = f * discount / 100f;
    }
    System.out.printf("complex cal price : %s\n", f);
    return f;
  }


  public void method_1(String string, Integer... integer) {

  }

  public void method_2(String string, String... strings) {

  }


  public static void main(String[] args) {
    VarArgsDemo demo = new VarArgsDemo();

    demo.calPrice(100, 70);
    demo.calPrice(100, 80, 70);

    demo.method_1("testDemo");
    demo.method_2("testDemo");

    demo.method_2("testDemo", null);

    demo.method_2("testDemo", new String[]{});





  }
}

