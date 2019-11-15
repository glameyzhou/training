package org.glamey.training.designmodel.singleton;

/**
 * 单例模式,基于classLoader机制来保证线程的安全
 *
 * @author zhouyang.zhou  2016/12/24.22.
 */
public class SingletonV1 {
    private SingletonV1() {
    }

    private static SingletonV1 instance = new SingletonV1();

    public static SingletonV1 getInstance() {
        return instance;
    }
}
