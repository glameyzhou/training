package org.glamey.training.io.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
        acquireWriteLock();
        try {
            process(key, value);
        } finally {
            releaseWriteLock();
        }
    }


    @Override
    public Object get(Object key) {
        acquireReadLock();
        try {
            return store.get(key);
        } finally {
            releaseReadLock();
        }
    }

    @Override
    public Object remove(Object key) {
        acquireWriteLock();
        try {
            return store.remove(key);
        } finally {
            releaseWriteLock();
        }
    }

    @Override
    public void clear() {
        acquireWriteLock();
        try {
            keyList.clear();
            store.clear();
        } finally {
            releaseWriteLock();
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

    private void acquireReadLock() {
        this.lock.readLock().lock();
    }

    private void releaseReadLock() {
        this.lock.readLock().unlock();
    }

    private void acquireWriteLock() {
        this.lock.writeLock().lock();
    }

    private void releaseWriteLock() {
        this.lock.writeLock().unlock();
    }
}
