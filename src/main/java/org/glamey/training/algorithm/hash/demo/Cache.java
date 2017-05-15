package org.glamey.training.algorithm.hash.demo;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 * @author zhouyang.zhou. 2017.05.15.16.
 */
public class Cache {

  private static final Map<String, String> map = Maps.newConcurrentMap();

  public Cache(CacheShardInfo cacheShardInfo) {
    System.out.println(" new Cache ok ....");
  }

  public String set(final String key, final String value) {
    return map.put(key, value);
  }

  public String get(final String key) {
    return map.get(key);
  }
}
