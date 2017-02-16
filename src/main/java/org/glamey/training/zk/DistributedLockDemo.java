package org.glamey.training.zk;

import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author zhouyang.zhou  2017/1/4.00.
 */
public class DistributedLockDemo {

  public static final CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryNTimes(10, 5000));

  public void doSomething(String threadName) throws Exception {
    String nodePath = "/test/mutexLock/demo1";
    InterProcessLock lock = new InterProcessMutex(client, nodePath);
    try {
      if (lock.acquire(10, TimeUnit.SECONDS)) {
        System.out.println("start .... " + threadName);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("finish .... " + threadName);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.release();
    }
  }

  public void test() {
    for (int i = 0; i < 10; i++) {
      new Thread(new Runnable() {
        public void run() {
          Thread thread = Thread.currentThread();
          try {
            doSomething(String.format("%d,%s", thread.getId(), thread.getName()));
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    client.start();
    DistributedLockDemo demo = new DistributedLockDemo();
    demo.test();

    TimeUnit.SECONDS.sleep(100);
    client.close();
  }
}
