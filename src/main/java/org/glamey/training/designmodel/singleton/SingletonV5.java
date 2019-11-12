package org.glamey.training.designmodel.singleton;

/**
 * 单例模式:通过枚举方式实现 effective java推荐使用的方式
 *
 * @author zhouyang.zhou  2016/12/24.22.
 */
public enum SingletonV5 {
  INSTANCE;

  SingletonV5() {
  }

  /**
   * 自定义需要创建的对象
   *
   * @return
   */
  public Object getInstance() {
    return new Object();
  }
}
