package org.glamey.training.zookeeper;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.collections.Lists;

/**
 * @author zhouyang.zhou. 2017.11.08.10.
 */

@Slf4j
public class ZKMultiClientTest {

  private List<ZKClient> clients;

  @Before
  public void before() {
    clients = Lists.newArrayList(5);
    for (int i = 0; i < 5; i++) {
      clients.add(new ZKClientImpl(ZKConstants.ZK_ADDRESS));
    }
  }

  @After
  public void after() {
    IterableUtils.forEach(clients, new Closure<ZKClient>() {
      @Override public void execute(ZKClient client) {
        client.close();
      }
    });
  }

  @Test
  public void process() {
    for (ZKClient client : clients) {
      log.info("client is {}", client);
    }
  }
}
