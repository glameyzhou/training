package org.glamey.training.algorithm.hash.demo;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author zhouyang.zhou. 2017.05.15.16.
 */
public class Main {

  public static void main(String[] args) {
    List<CacheShardInfo> shards = generateShards();
    CacheClient cache = new CacheClient(shards);

    for (int i = 0; i < 100; i++) {
      cache.set("key_" + i, "value_" + i);
    }

    for (int i = 0; i < 100; i++) {
      String cacheValue = cache.get("key_" + i);
      System.out.printf("key_%d --> %s \r\n", i, cacheValue);
    }
  }

  private static List<CacheShardInfo> generateShards() {
    List<CacheShardInfo> list = Lists.newArrayList(
        new CacheShardInfo(2),
        new CacheShardInfo(4),
        new CacheShardInfo(2)
    );
    return list;
  }
}
