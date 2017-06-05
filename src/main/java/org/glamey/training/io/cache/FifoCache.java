package org.glamey.training.io.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhouyang.zhou. 2017.06.05.16.
 */
public class FifoCache implements Cache {
    private final Map<Object, Object> store;
    private final LinkedList<Object> keyList;
    private final ReadWriteLock lock;
    private final int size;

    public FifoCache(int capacity) {
        size = capacity;
        lock = new ReentrantReadWriteLock(true);
        keyList = new LinkedList<>();
        store = new HashMap<>();
    }

    @Override
    public void put(Object key, Object value) {
        Lock lock = this.lock.writeLock();
        lock.lock();
        try {
            process(key, value);
        } finally {
            lock.unlock();
        }
    }


    @Override
    public Object get(Object key) {
        Lock lock = this.lock.readLock();
        lock.lock();
        try {
            return store.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object remove(Object key) {
        Lock lock = this.lock.writeLock();
        lock.lock();
        try {
            return store.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        Lock lock = this.lock.writeLock();
        lock.lock();
        try {
            keyList.clear();
            store.clear();
        } finally {
            lock.unlock();
        }
    }

    private void process(Object key, Object value) {
        keyList.addLast(key);
        store.put(key, value);
        boolean tooBig = keyList.size() > size;
        if (tooBig) {
            Object eldestKey = keyList.removeFirst();
            store.remove(eldestKey);
        }
    }
}
