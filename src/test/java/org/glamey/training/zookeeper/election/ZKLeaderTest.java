package org.glamey.training.zookeeper.election;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.glamey.training.zookeeper.ZKClient;
import org.glamey.training.zookeeper.ZKClientImpl;
import org.glamey.training.zookeeper.ZKConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.collections.Lists;
import org.testng.collections.Sets;

/**
 * 测试 创建 leader
 *
 * @author zhouyang.zhou. 2017.11.08.12.
 */
@Slf4j
public class ZKLeaderTest {

  private final int SERVER_COUNT = 3;

  private List<LeaderEntry> leaderEntries;

  private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  //随机构建五个服务实例名称
  private Set<String> machines = Sets.newHashSet();

  @Before
  public void before() {

    while (machines.size() < SERVER_COUNT) {
      machines.add(String.format("machine_%d", new Random().nextInt(10)));
    }

    leaderEntries = Lists.newArrayList(SERVER_COUNT);
    log.info("构建[{}]个服务实例", SERVER_COUNT);
    for (int i = 0; i < SERVER_COUNT; i++) {
      ZKClient client = new ZKClientImpl(ZKConstants.ZK_ADDRESS);
      String machineId = Lists.newArrayList(machines).get(i);
      Leader leader = client.createLeader(ZKConstants.LEADER_ROOT, machineId);
      LeaderEntry entry = new LeaderEntry(machineId, leader, client);
      leaderEntries.add(entry);
      log.info("构建实例[{}]成功,{}", i + 1, entry);
    }
  }

  @After
  public void after() {

    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    //关闭所有链接
    IterableUtils.forEach(leaderEntries, new Closure<LeaderEntry>() {
      @Override public void execute(LeaderEntry entry) {
        entry.leader.destroy();
        entry.client.close();
      }
    });

    //关闭线程池
    if(!executorService.isShutdown()) {
      executorService.shutdownNow();
    }
  }

  @Test
  public void elect() {
    for (final LeaderEntry entry : leaderEntries) {
      executorService.execute(new Runnable() {
        @Override public void run() {
          leaderElection(entry);
        }
      });
    }
  }

  /**
   * 选主
   *
   * @param entry
   */
  private void leaderElection(final LeaderEntry entry) {
    final Thread thread = Thread.currentThread();
    Leader leader = entry.leader;
    leader.addListener(new LeaderChangedListener() {
      @Override public void own() {
        executeByOwnLeader(thread, entry);
      }

      @Override public void lost() {
        executeByLostLeader(thread, entry);
      }
    });
    leader.start();
  }

  private void executeByLostLeader(Thread thread, LeaderEntry entry) {
    log.info("({}-{})-[{}] LOST Leader", thread.getName(), thread.getId(), entry.machineId);
  }

  private void executeByOwnLeader(Thread thread, LeaderEntry entry) {
    log.info("({}-{})-[{}] OWN Leader", thread.getName(), thread.getId(), entry.machineId);
  }

  @Data
  @AllArgsConstructor
  private class LeaderEntry {
    private String machineId;
    private Leader leader;
    private ZKClient client;
  }
}