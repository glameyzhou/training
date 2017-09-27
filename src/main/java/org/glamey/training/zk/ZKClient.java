package org.glamey.training.zk;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.Assert;

import static org.apache.zookeeper.CreateMode.EPHEMERAL;

/**
 * @author zhouyang.zhou. 2017.09.15.16.
 */
public class ZKClient {

  private CuratorFramework client;

  private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

  public ZKClient(String address) {
    initZKClient(address);
  }

  private void initZKClient(String address) {
    client = CuratorFrameworkFactory.builder()
        .connectString(address)
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .build();
    client.start();

    try {
      final NodeCache nodeCache = new NodeCache(client, "/zk_test/test1", false);
      nodeCache.start();
      nodeCache.getListenable().addListener(new NodeCacheListener() {
        @Override public void nodeChanged() throws Exception {
          System.out.printf("Node data is changed,new data:%s\n", byteToString(nodeCache.getCurrentData().getData()));
        }
      }, EXECUTOR);

      /*final PathChildrenCache childrenCache = new PathChildrenCache(client, "/zk_test", false);
      childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
      childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
        @Override public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
          System.out.printf("children node listener....,event Type = %s\n", event.getType());
          switch (event.getType()) {
            case CHILD_ADDED:
              System.out.println("[%s]\n" + event.getData().getPath());
              break;
            case CHILD_REMOVED:
              System.out.println("[%s]\n" + event.getData().getPath());
              break;
            case CHILD_UPDATED:
              System.out.println("[%s]\n" + event.getData().getPath());
              break;
            default:
              break;
          }
        }
      }, EXECUTOR);*/
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean create(String path, String value) {
    try {
      Stat stat = client.checkExists().forPath(path);
      if(stat != null) {
        return false;
      }

      String r;
      if(StringUtils.isBlank(value)) {
        r = client.create().creatingParentsIfNeeded().withMode(EPHEMERAL).forPath(path);
      } else {
        r = client.create()
            .creatingParentsIfNeeded()
            .withMode(CreateMode.PERSISTENT)
            .forPath(path, stringToByte(value));
      }
      return StringUtils.equals(r, path);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean update(String path, String value) {
    try {
      Stat stat = client.checkExists().forPath(path);
      if(stat == null) {
        return false;
      }
      boolean b = client.setData().forPath(path, stringToByte(value)) != null;
      if(true) {
      }
      return b;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public void delete(String path) {
    try {
      client.delete().deletingChildrenIfNeeded().forPath(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public LinkedHashMap<String, String> listChildrenData(String path) {
    LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
    try {
      List<String> children = client.getChildren().forPath(path);
      if(CollectionUtils.isEmpty(children)) {
        return map;
      }
      GetDataBuilder data = client.getData();
      for (String child : children) {
        String propertyPath = ZKPaths.makePath(path, child);
        map.put(child, byteToString(data.forPath(propertyPath)));
      }
      return map;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<String> listChildren(String path) {
    try {
      return client.getChildren().forPath(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void close() {
    if(client != null) {
      client.close();
    }
  }

  public final static String byteToString(byte[] bytes) {
    Assert.state(bytes != null);
    return new String(bytes, Charsets.UTF_8);
  }

  public final static byte[] stringToByte(String string) {
    Assert.state(string != null);
    return string.getBytes(Charsets.UTF_8);
  }
}
