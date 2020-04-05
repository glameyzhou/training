package org.glamey.training.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 场景：读多写少。
 * 锁降级：
 * 写锁可以降级为读锁:获得写锁->获得读锁->释放写锁->释放读锁
 * 读锁不能升为写锁
 * <p>
 * 基于CAS实现
 * 同步状态下低16 保存writeLock持有次数
 * 同步状态下高16 保存readLock持有次数
 * 写锁互斥，读锁共享
 *
 * @author yang.zhou 2019.11.07.17
 */
public class ReadWriteLockDemo {

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Map<String, Object> map = new ConcurrentHashMap<>();

    public Object get(String key) {
        Object object;
        rwLock.readLock().lock();       // get read lock
        try {
            object = map.get(key);
            if (object == null) {
                rwLock.readLock().unlock();     // release read lock
                rwLock.writeLock().lock();      // get write lock
                try {
                    if (map.get(key) == null) {
                        object = getFromRemote(key);
                        map.put(key, object);
                    } else {
                        object = map.get(key);
                    }
                } finally {
                    rwLock.readLock().lock();  //get read lock 配置下面的 release write lock来实现写锁降级
                    rwLock.writeLock().unlock(); // release write lock
                }
            }
        } finally {
            rwLock.readLock().unlock();     // release read lock
        }

        return object;
    }

    private Object getFromRemote(String key) {
        return key;
    }
}
