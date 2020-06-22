package org.glamey.training.designmodel.observable;

/**
 * @author zhouyang.zhou. 2017.08.29.15.
 */
public interface Observer<T extends Observable> {

    void change(T t);
}
