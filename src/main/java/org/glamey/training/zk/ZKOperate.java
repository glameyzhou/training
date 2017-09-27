package org.glamey.training.zk;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.Assert;

import static org.glamey.training.zk.ZKSerializer.byteToString;
import static org.glamey.training.zk.ZKSerializer.stringToByte;

/**
 * @author zhouyang.zhou. 2017.09.18.18.
 */
public class ZKOperate {

  private CuratorFramework client;

  public ZKOperate(CuratorFramework client) {
    Assert.state(client != null);
    this.client = client;
  }

  public boolean create(String path, String value) {
    try {
      Stat stat = client.checkExists().forPath(path);
      if(stat != null) {
        throw new IllegalStateException(String.format("create node error, path=[%s] is exist", path));
      }

      String result;
      if(StringUtils.isEmpty(value)) {
        result = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
      } else {
        result = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, stringToByte(value));
      }
      return result.equals(value);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean update(String path, String value) {
    try {
      Stat stat = client.checkExists().forPath(path);
      if(stat == null) {
        throw new IllegalStateException(String.format("update node error, path=[%s] is not exist", path));
      }
      Stat result;
      if(StringUtils.isEmpty(value)) {
        result = client.setData().forPath(path);
      } else {
        result = client.setData().forPath(path, stringToByte(value));
      }
      return result != null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public void delete(String path, boolean deleteChildren) {
    try {
      Stat stat = client.checkExists().forPath(path);
      if(stat == null) {
        throw new IllegalStateException(String.format("delete node error, path=[%s] is not exist", path));
      }

      if(deleteChildren) {
        client.delete().deletingChildrenIfNeeded().forPath(path);
      } else {
        client.delete().forPath(path);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(String path) {
    delete(path, false);
  }

  public List<String> listChildren(String path) {
    try {
      Stat stat = client.checkExists().forPath(path);
      if(stat == null) {
        throw new IllegalStateException(String.format("delete node error, path=[%s] is not exist", path));
      }
      return client.getChildren().forPath(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public LinkedHashMap<String, String> listChildrenData(String path) {
    List<String> children = listChildren(path);
    GetDataBuilder dataBuilder = client.getData();

    if(CollectionUtils.isEmpty(children)) {
      return null;
    }

    LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
    try {
      for (String child : children) {
        String childPath = ZKPaths.makePath(path, child);
        String data = byteToString(dataBuilder.forPath(childPath));
        map.put(child, data);
      }
      return map;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
