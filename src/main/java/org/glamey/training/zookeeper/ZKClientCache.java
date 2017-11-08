package org.glamey.training.zookeeper;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端缓存对象
 *
 * @author zhouyang.zhou. 2017.11.07.19.
 */
@Slf4j
public class ZKClientCache {
  private static final Map<String, ZKClientImpl> CACHE = new HashMap<>();

  public synchronized static ZKClient get(String address) {
    log.info("Get zkclient for {}", address);
    ZKClientImpl client = CACHE.get(address);
    if(client == null) {
      CACHE.put(address, new ZKClientImpl(address));
    }
    client = CACHE.get(address);
    return client;
  }
}
