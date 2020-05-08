package org.glamey.training.io.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 安全的LRU缓存
 *
 * @param <K>
 * @param <V>
 */
public class CurrentLrcCache<K, V> {
    private final int capacity;
    private final ConcurrentLinkedQueue<K> keys;
    private ReentrantReadWriteLock lock;
    private ReentrantReadWriteLock.WriteLock writeLock;
    private ReentrantReadWriteLock.ReadLock readLock;
    private ConcurrentHashMap<K, V> map;

    public CurrentLrcCache(int capacity) {
        this.capacity = capacity;
        keys = new ConcurrentLinkedQueue<>();
        map = new ConcurrentHashMap<>(capacity);
        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }


    /**
     * 写入数据
     * 如果
     *
     * @param k
     * @param v
     * @return
     */
    public V put(K k, V v) {
        writeLock.lock();
        try {
            if (map.containsKey(k)) {
                moveToTail(k);
                map.put(k, v);
                return v;
            }
            if (map.size() >= capacity) {
                removeOldestKey();
            }
            keys.add(k);
            map.put(k, v);
            return v;
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K k) {
        readLock.lock();
        try {
            if (map.containsKey(k)) {
                moveToTail(k);
                return map.get(k);
            }
        } finally {
            readLock.unlock();
        }
        return null;
    }

    public V remove(K k) {
        writeLock.lock();
        try {
            if (map.containsKey(k)) {
                keys.remove(k);
                return map.remove(k);
            }
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    public int size() {
        return map.size();
    }


    private void moveToTail(K k) {
        keys.remove(k);
        keys.add(k);
    }

    private void removeOldestKey() {
        K oldestKey = keys.poll();
        if (map.containsKey(oldestKey)) {
            map.remove(oldestKey);
        }
    }
}
