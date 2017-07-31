package org.glamey.training.designmodel.singleton;

/**
 * 单例模式:基于双重check的模式保证线程的安全
 *
 * @author zhouyang.zhou  2016/12/24.22.
 */
public class SingletonV2 {
  private SingletonV2() {
  }

  private volatile static SingletonV2 instance;

  public static SingletonV2 getInstance() {
    if(instance == null) {
      synchronized (SingletonV2.class) {
        if(instance == null) {
          instance = new SingletonV2();
        }
      }
    }
    return instance;
  }
}
