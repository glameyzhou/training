package org.glamey.training.zookeeper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.curator.utils.ZKPaths;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.glamey.training.zookeeper.ZKConstants.ZK_ROOT;

/**
 * @author zhouyang.zhou. 2017.11.07.20.
 */
public class ZKClientTest {

  private ZKClient client;

  @Before
  public void before() {
    client = ZKClientCache.get(ZKConstants.ZK_ADDRESS);

    //初始化节点变更观察者
    new ZKNodeObserver(ZKConstants.ZK_ADDRESS);
  }

  @After
  public void after() {
    client.close();
  }

  @Test
  public void test() throws Exception {
    //create the persistent nodes
    for (int i = 0; i < 10; i++) {
      TimeUnit.SECONDS.sleep(1);
      client.createPersistentSeq(ZKPaths.makePath(ZK_ROOT, "persistent"), "perisitent_" + i);
    }
    List<String> persistentChildren = client.listChildren(ZK_ROOT);
    for (String child : persistentChildren) {
      String data = client.get(ZKPaths.makePath(ZK_ROOT, child));
      client.update(ZKPaths.makePath(ZK_ROOT, child), data + "_v2");
      client.del(ZKPaths.makePath(ZK_ROOT, child));
      TimeUnit.SECONDS.sleep(1);
    }

    //create the epheremal nodes
    for (int i = 0; i < 10; i++) {
      TimeUnit.SECONDS.sleep(1);
      client.createEphemeralSeq(ZKPaths.makePath(ZK_ROOT, "ephemeral_seq_"), "ephemeral_seq_" + i);
    }

    List<String> ephemeralChildren = client.listChildren(ZK_ROOT);
    for (String child : ephemeralChildren) {
      String data = client.get(ZKPaths.makePath(ZK_ROOT, child));
      client.update(ZKPaths.makePath(ZK_ROOT, child), data + "_v2");
      TimeUnit.SECONDS.sleep(1);
    }
  }
}