package org.glamey.training.zookeeper;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;

import static org.apache.zookeeper.CreateMode.EPHEMERAL;
import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;
import static org.apache.zookeeper.CreateMode.PERSISTENT;
import static org.apache.zookeeper.CreateMode.PERSISTENT_SEQUENTIAL;
import static org.glamey.training.zookeeper.ZKConstants.UTF_8;

/**
 * @author zhouyang.zhou. 2017.11.07.18.
 */
public class ZKClientImpl implements ZKClient {
  private CuratorFramework client;

  private final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  private final ExecutorService SAME_EXECUTOR = MoreExecutors.sameThreadExecutor();

  public ZKClientImpl(String address) {
    Preconditions.checkNotNull(address);
    client = CuratorFrameworkFactory.builder()
        .connectString(address)
        .connectionTimeoutMs(5000)
        .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
        .build();
    waitUnitZkStarted();
  }

  private void waitUnitZkStarted() {
    CountDownLatch latch = new CountDownLatch(1);
    client.getConnectionStateListenable().addListener(new ZKConnectionWatcher(latch));
    client.start();
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  public void createPersistent(String path, String data) throws Exception {
    client.create().creatingParentsIfNeeded().withMode(PERSISTENT).forPath(path, data.getBytes(UTF_8));
  }

  public void createPersistentSeq(String path, String data) throws Exception {
    client.create().creatingParentsIfNeeded().withMode(PERSISTENT_SEQUENTIAL).forPath(path, data.getBytes(UTF_8));
  }

  public void createEphemeral(String path, String data) throws Exception {
    client.create().creatingParentsIfNeeded().withMode(EPHEMERAL).forPath(path, data.getBytes(UTF_8));
  }

  public void createEphemeralSeq(String path, String data) throws Exception {
    client.create().creatingParentsIfNeeded().withMode(EPHEMERAL_SEQUENTIAL).forPath(path, data.getBytes(UTF_8));
  }

  public String get(String path) throws Exception {
    byte[] bytes = client.getData().forPath(path);
    return new String(bytes, UTF_8);
  }

  public void update(String path, String data) throws Exception {
    client.setData().forPath(path, data.getBytes(UTF_8));
  }

  public void del(String path) throws Exception {
    client.delete().forPath(path);
  }

  public void delDoAll(String path) throws Exception {
    client.delete().deletingChildrenIfNeeded().forPath(path);
  }

  public List<String> listChildren(String parent) throws Exception {
    List<String> children = client.getChildren().forPath(parent);
    return children;
  }

  @Override public List<String> listChildren(final String parent, final NodeListener nodeListener, final boolean sync) throws Exception {
    final PathChildrenCache cache = new PathChildrenCache(client, parent, false, false, EXECUTOR);
    cache.getListenable().addListener(new PathChildrenCacheListener() {
      @Override public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        if(event == null || event.getData() == null) {
          return;
        }

        PathChildrenCacheEvent.Type type = event.getType();

        switch (type) {
          case CHILD_ADDED:
            nodeListener.onChanged(ZKClientImpl.this, new ChangedEvent(event.getData().getPath(), ChangedEvent.Type.CHILD_ADDED));
            break;

          case CHILD_REMOVED:
            nodeListener.onChanged(ZKClientImpl.this, new ChangedEvent(event.getData().getPath(), ChangedEvent.Type.CHILD_REMOVED));
            break;
          case CHILD_UPDATED:
            nodeListener.onChanged(ZKClientImpl.this, new ChangedEvent(event.getData().getPath(), ChangedEvent.Type.CHILD_UPDATED));
            break;
        }
      }
    });

    PathChildrenCache.StartMode mode = sync ? PathChildrenCache.StartMode.BUILD_INITIAL_CACHE : PathChildrenCache.StartMode.NORMAL;
    cache.start(mode);
    List<ChildData> children = cache.getCurrentData();
    List<String> result = Lists.newArrayList();
    for (ChildData child : children) {
      result.add(child.getPath());
    }
    return result;
  }

  @Override
  public void close() {
    if(client != null) {
      client.close();
    }
  }
}
