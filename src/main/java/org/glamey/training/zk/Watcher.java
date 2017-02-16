package org.glamey.training.zk;

import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author zhouyang.zhou  2017/1/25.21.
 */
public class Watcher {

  private static final String zkConnection = "127.0.0.1:2181";

  public static void main(String[] args) throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.newClient(zkConnection, new RetryNTimes(10, 5000));
    client.start();

    //watch
    String path = "/test";


    PathChildrenCache watcher = new PathChildrenCache(client, path, true);
    watcher.getListenable().addListener(new PathChildrenCacheListener() {
      @Override public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        ChildData childData = event.getData();
        if (childData == null) {
          System.out.println("no data event");
        }
        else {
          System.out.println("event Type --> " + event.getType());;
          System.out.println("data path --> " + childData.getPath());
          System.out.println("data data --> " + new String(childData.getData()));
          System.out.println("data stat --> " + childData.getStat());
        }

      }
    });
    watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

    TimeUnit.HOURS.sleep(1);
  }
}
