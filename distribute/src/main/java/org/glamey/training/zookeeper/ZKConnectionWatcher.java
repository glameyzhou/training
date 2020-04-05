package org.glamey.training.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

import java.util.concurrent.CountDownLatch;

/**
 * ZK建立连接监听器
 *
 * @author zhouyang.zhou. 2017.11.07.18.
 */
@Slf4j
public class ZKConnectionWatcher implements ConnectionStateListener {

  private CountDownLatch latch;

  public ZKConnectionWatcher(CountDownLatch latch) {
    this.latch = latch;
  }

  @Override public void stateChanged(CuratorFramework client, ConnectionState newState) {
    if(newState == ConnectionState.CONNECTED) {
      latch.countDown();
      log.info("........ZK建立连接OK........");
    }
  }
}
