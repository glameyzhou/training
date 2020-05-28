package org.glamey.training.codes.hash.demo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yang.zhou 2019.11.04.18
 */
public class CacheResource {
    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>(1024);

    public CacheResource(CacheNode cacheNode) {
        System.out.println(String.format("the cache resource is inited ...\n ip=%s, port=%d, name=%s",
                cacheNode.getIp(), cacheNode.getPort(), cacheNode.getName()));
    }

    public final Object get(String key) {
        return map.get(key);
    }

    public final void set(String key, Object value) {
        map.put(key, value);
    }
}
