package org.glamey.training.io.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhouyang.zhou. 2017.06.05.15.
 */
public class LruCache implements Cache {

    private Map<Object, Object> store;
    private Map<Object, Object> keyStore;
    private Object eldestKey;
    private ReadWriteLock lock;

    public LruCache(final int capacity) {
        lock = new ReentrantReadWriteLock(true);
        store = new HashMap<>(capacity);
        this.keyStore = new LinkedHashMap<Object, Object>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean tooBig = size() > capacity;
                if (tooBig) {
                    eldestKey = eldest.getKey();
                }
                return tooBig;
            }
        };
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
            keyStore.clear();
            store.clear();
        } finally {
            releaseWriteLock();
        }
    }


    private void process(Object key, Object value) {
        keyStore.put(key, key);
        store.put(key, value);
        if (eldestKey != null) {
            store.remove(eldestKey);
            eldestKey = null;
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
