package org.glamey.training.zookeeper;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.curator.utils.ZKPaths;

import static org.glamey.training.zookeeper.ZKConstants.ZK_ADDRESS;
import static org.glamey.training.zookeeper.ZKConstants.ZK_ROOT;

/**
 * @author zhouyang.zhou. 2017.11.07.18.
 */
public class ZKDemo {

  private final ZKClient client;

  public ZKDemo() {
    client = ZKClientCache.get(ZK_ADDRESS);

    //节点变更的观察者
    new ZKObserver(ZK_ADDRESS);
  }

  public void process() throws Exception {
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

  public static void main(String[] args) throws Exception {
    ZKDemo zkDemo = new ZKDemo();
    zkDemo.process();
  }
}
