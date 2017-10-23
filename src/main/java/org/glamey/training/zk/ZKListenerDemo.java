package org.glamey.training.zk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author zhouyang.zhou. 2017.09.18.14.
 */
public class ZKListenerDemo {

  public static void main(String[] args) throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.builder()
        .connectString("127.0.0.1:3301,127.0.0.1:3302,127.0.0.1:3303")
        .sessionTimeoutMs(5000)
        .connectionTimeoutMs(3000)
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .build();
    client.start();
    client.create()
        .creatingParentsIfNeeded()
        .forPath("/zk_test/test1", "hello".getBytes());
    /**
     * 在注册监听器的时候，如果传入此参数，当事件触发时，逻辑由线程池处理
     */

    ExecutorService pool = Executors.newFixedThreadPool(2);

    /**
     * 监听数据节点的变化情况
     */
    final NodeCache nodeCache = new NodeCache(client, "/zk_test/test1", false);
    nodeCache.start(true);
    nodeCache.getListenable().addListener(
        new NodeCacheListener() {
          @Override
          public void nodeChanged() throws Exception {
            System.out.println("Node data is changed, new data: " +
                new String(nodeCache.getCurrentData().getData()));
          }
        },
        pool
    );
    /**
     * 监听子节点的变化情况
     */
    final PathChildrenCache childrenCache = new PathChildrenCache(client, "/zk_test", true);
    childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
    childrenCache.getListenable().addListener(
        new PathChildrenCacheListener() {
          @Override
          public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
              throws Exception {
            switch (event.getType()) {
              case CHILD_ADDED:
                System.out.println("CHILD_ADDED: " + event.getData().getPath());
                break;
              case CHILD_REMOVED:
                System.out.println("CHILD_REMOVED: " + event.getData().getPath());
                break;
              case CHILD_UPDATED:
                System.out.println("CHILD_UPDATED: " + event.getData().getPath());
                break;
              default:
                break;
            }
          }
        },
        pool
    );

    client.setData().forPath("/zk_test/test1", "world".getBytes());

    client.setData().forPath("/zk_test/test1", "world_3".getBytes());
    client.setData().forPath("/zk_test/test1", "world_4".getBytes());

    Thread.sleep(10 * 1000);
    pool.shutdown();
    client.close();
  }
}
