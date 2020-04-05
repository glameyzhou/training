package org.glamey.training.zookeeper.election;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhouyang.zhou. 2017.11.08.11.
 */
@Slf4j
public class ZKLeader implements Leader, LeaderSelectorListener {
  private final List<LeaderChangedListener> leaderChangedListeners;

  private LeaderSelector selector;

  private Semaphore taskLatch = new Semaphore(0);

  private volatile boolean isLeader = Boolean.FALSE;

  private AtomicBoolean STATE = new AtomicBoolean(Boolean.TRUE);

  public ZKLeader() {
    leaderChangedListeners = Lists.newArrayList();
  }

  public void setSelector(LeaderSelector selector) {
    this.selector = selector;
  }

  @Override public String getMyId() {
    return selector.getId();
  }

  @Override public String getLeaderId() throws Exception {
    return selector.getLeader().getId();
  }

  @Override public void addListener(LeaderChangedListener listener) {
    leaderChangedListeners.add(listener);
  }

  @Override public void start() {
    selector.start();
  }

  @PreDestroy
  @Override public void destroy() {
    if(STATE.compareAndSet(Boolean.TRUE, Boolean.FALSE)) {
      if(selector != null) {
        selector.close();
      }
    }
  }

  @Override public void takeLeadership(CuratorFramework client) throws Exception {
    try {
      isLeader = true;
      for (LeaderChangedListener listener : leaderChangedListeners) {
        listener.own();
      }
    } catch (Exception e) {
      log.error("after take leader, execute the method error.", e);
      taskLatch.release();
    }
    try {
      taskLatch.acquire();
    } catch (Exception e) {
      log.error("taskLatch.acquire error, error message is {}", e.getMessage(), e);
    } finally {
      for (LeaderChangedListener listener : leaderChangedListeners) {
        listener.lost();
      }
    }
  }

  @Override public void stateChanged(CuratorFramework client, ConnectionState state) {
    if(isLeader) {
      isLeader = false;
      if(state == ConnectionState.LOST) {
        taskLatch.release();
      }
      if(state == ConnectionState.SUSPENDED) {
        taskLatch.release();
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          log.error("thread sleep error", e);
        }
        selector.requeue();
      }
    }
  }
}
