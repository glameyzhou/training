package org.glamey.training.io.cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author zhouyang.zhou. 2017.06.05.15.
 */
public interface Cache {

    void put(Object key, Object value);

    Object get(Object key);

    Object remove(Object key);

    void clear();
}
