package org.glamey.training.io.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
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
        Lock lock = this.lock.writeLock();
        try {
            lock.lock();
            process(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object get(Object key) {
        Lock lock = this.lock.readLock();
        try {
            lock.lock();
            return store.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object remove(Object key) {
        Lock lock = this.lock.writeLock();
        try {
            lock.lock();
            return store.remove(key);
        } finally {
            lock.unlock();
        }
    }

    private void process(Object key, Object value) {
        keyStore.put(key, value);
        store.put(key, value);
        if (eldestKey != null) {
            store.remove(eldestKey);
            eldestKey = null;
        }
    }
}
