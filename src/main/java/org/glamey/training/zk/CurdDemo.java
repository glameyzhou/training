package org.glamey.training.zk;

import com.google.common.base.Charsets;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author zhouyang.zhou  2017/1/3.23.
 */
public class CurdDemo {

  public static void main(String[] args) throws Exception {
    String zkConnection = "127.0.0.1:2181";
    /*CuratorFramework client = CuratorFrameworkFactory.newClient(zkConnection, new RetryPolicy() {
      public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
        return false;
      }
    });*/
    CuratorFramework client = CuratorFrameworkFactory.newClient(zkConnection, new RetryNTimes(10, 5000));
    client.start();

    String nodeName = "/test/curator/c_1";
    byte[] nodeData = "创建curator测试节点/test/curator/c_1".getBytes(Charsets.UTF_8.name());

    //create
    String response = client.create().creatingParentsIfNeeded().forPath(nodeName, nodeData);
    System.out.println("create ok --> " + response);

    //get data
    byte[] zkData = client.getData().forPath(nodeName);
    System.out.println("get data --> " + new String(zkData));

    //modify
    byte[] nodeDataForModify = "创建curator测试节点/test/curator/c_1_1".getBytes(Charsets.UTF_8.name());
    System.out.println("modify ok --> " + client.setData().forPath(nodeName, nodeDataForModify));
    System.out.println("get modify ok --> " + new String(client.getData().forPath(nodeName)));

    //delete
    client.delete().deletingChildrenIfNeeded().forPath(nodeName);
    System.out.println("delete ok ");

    client.close();
  }
}
