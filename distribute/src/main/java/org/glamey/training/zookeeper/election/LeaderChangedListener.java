package org.glamey.training.zookeeper.election;

import java.util.EventListener;

/**
 * leader变更监听器
 *
 * @author zhouyang.zhou. 2017.11.08.11.
 */
public interface LeaderChangedListener extends EventListener {

  /**
   * 获取Leader后执行的动作
   */
  void own();

  /**
   * 释放leader之后执行的动作
   */
  void lost();
}
