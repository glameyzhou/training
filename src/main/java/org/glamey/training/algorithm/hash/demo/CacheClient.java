package org.glamey.training.algorithm.hash.demo;

import java.util.List;
import org.glamey.training.algorithm.hash.consistent_hashing.Sharing;

/**
 * @author zhouyang.zhou. 2017.05.15.16.
 */
public class CacheClient extends Sharing<Cache, CacheShardInfo> {
  public CacheClient(List<CacheShardInfo> shards) {
    super(shards);
  }

  public String set(final String key, final String value) {
    Cache cache = getShard(key);
    return cache.set(key, value);
  }

  public String get(final String key) {
    Cache cache = getShard(key);
    return cache.get(key);
  }
}
