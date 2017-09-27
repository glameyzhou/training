package org.glamey.training.zk;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhouyang.zhou. 2017.09.15.17.
 */
public class ZKClientTest {

  private static final String address = "127.0.0.1:3301,127.0.0.1:3302,127.0.0.1:3303";

  private ZKClient client;

  @Before
  public void before() {
    client = new ZKClient(address);
  }

  @After
  public void after() {
    client.close();
  }

  @Test
  public void create() {
    client.create("/zk_test/test1", "test_1");
    client.create("/zk_test/test2", "test_2");
    client.create("/zk_test/test3", "test_3");
  }

  @Test
  public void listChildren() {
    List<String> children = client.listChildren("/zk_test");
    for (String child : children) {
      System.out.println(child);
    }
  }

  @Test
  public void listChildData() {
    LinkedHashMap<String, String> map = client.listChildrenData("/zk_test");
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.printf("%s-->%s\n", entry.getKey(), entry.getValue());
    }
  }

  @Test
  public void update() {
    client.update("/zk_test/test1", "a");
    client.update("/zk_test/test1", "b");
    client.update("/zk_test/test1", "c");
  }

  @Test
  public void nodeListener() {
    //client.create("/zk_test/test1", "a1");
    //client.update("/zk_test/test1", "a2");
    //client.update("/zk_test/test1", "a3");
    client.delete("/zk_test/test1");
  }
}