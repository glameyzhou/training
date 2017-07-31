package org.glamey.training.designmodel.singleton;

/**
 *
 * 单例模式:通过内部类来实现,原理依然是通过JVM的classLoader来保证线程的安全。
 *
 *
 * 推荐使用下面的模式，静态内部类，延迟加载的单例模式。
 * @author zhouyang.zhou  2016/12/24.22.
 */
public class SingletonV4 {

  private SingletonV4() {
  }

  private static class SingletonHolder {
    private static final SingletonV4 instance = new SingletonV4();
  }

  public static SingletonV4 getInstance() {
    return SingletonHolder.instance;
  }
}
