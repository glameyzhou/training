package org.glamey.training.zk.listener;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.collections4.MapUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.glamey.training.zk.ZKOperate;
import org.glamey.training.zk.ZKSerializer;

/**
 * Path Cache用来监控一个ZNode的子节点. 当一个子节点增加， 更新，删除时， Path Cache会改变它的状态， 会包含最新的子节点， 子节点的数据和状态。 <p> (此处需要注意，他只会监听一级子节点，不会循环监听子节点下面的child）
 *
 * @author zhouyang.zhou. 2017.09.18.14.
 */
public class PathCacheListenerDemo {

  private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);
  private static CuratorFramework CLIENT = null;

  private static final String ZK_ADDRESS = "127.0.0.1:3301,127.0.0.1:3302,127.0.0.1:3303";

  public void init() {
    CLIENT = CuratorFrameworkFactory.builder()
        .connectString(ZK_ADDRESS)
        .sessionTimeoutMs(5000)
        .connectionTimeoutMs(3000)
        .retryPolicy(new ExponentialBackoffRetry(100, 3))
        .build();

    CLIENT.start();
    //监听子节点变化
    watchChildren("/zk_test");
  }

  private void watchChildren(String path) {
    try {
      PathChildrenCache childrenCache = new PathChildrenCache(CLIENT, path, false);
      childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
        @Override public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
          PathChildrenCacheEvent.Type type = event.getType();
          System.out.println("event type --> " + type);
          event.getData().getData();
          switch (event.getType()) {
            case CHILD_ADDED:
              System.out.println(ZKSerializer.byteToString(event.getData().getData()));
              break;
            case CHILD_UPDATED:
              System.out.println(ZKSerializer.byteToString(event.getData().getData()));
              break;
            case CHILD_REMOVED:
              System.out.println(ZKSerializer.byteToString(event.getData().getData()));
              break;
            default:
              System.out.println(ZKSerializer.byteToString(event.getData().getData()));
              break;
          }
        }
      }, EXECUTOR);
      childrenCache.start();
    } catch (
        Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {
    new PathCacheListenerDemo().init();

    ZKOperate operate = new ZKOperate(CLIENT);

    operate.delete("/zk_test", true);

    operate.create("/zk_test/demo_1", "我是demo_1,v_1");
    operate.update("/zk_test/demo_1", "我是demo_1,v_2");

    operate.create("/zk_test/demo_2", "我是demo_2,v_1");
    operate.update("/zk_test/demo_2", "我是demo_2,v_2");

    //只能监听一级节点，后续的子节点无法监控
    operate.create("/zk_test/demo_1/demo_demo_1", "我是demo_1_demo_1,v_1");
    operate.update("/zk_test/demo_1/demo_demo_1", "我是demo_1_demo_1,v_2");

    LinkedHashMap<String, String> childrenData = operate.listChildrenData("/zk_test");
    if(MapUtils.isNotEmpty(childrenData)) {
      for (Map.Entry<String, String> entry : childrenData.entrySet()) {
        System.out.printf("[%s] --> [%s]\n", entry.getKey(), entry.getValue());
      }
    } else {
      System.out.println("/zk_test没有子节点");
    }

    operate.delete("/zk_test", true);

    EXECUTOR.shutdown();
    CLIENT.close();
  }
}
