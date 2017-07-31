package org.glamey.training.designmodel.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单例模式:基于同步锁的模式保证线程的安全
 *
 * 下面的模式，不适合线上使用，每个线上都会堵塞。
 *
 * @author zhouyang.zhou  2016/12/24.22.
 */
public class SingletonV3 {

  private SingletonV3() {
  }

  private static SingletonV3 instance;

  private static final Lock LOCK = new ReentrantLock();

  public static SingletonV3 getInstance() {
    try {
      LOCK.lock();
      if (instance == null) {
        instance = new SingletonV3();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      LOCK.unlock();
    }
    return instance;
  }
}
