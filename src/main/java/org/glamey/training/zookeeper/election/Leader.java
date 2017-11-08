package org.glamey.training.zookeeper.election;

/**
 * 用于Leader选举
 *
 * @author zhouyang.zhou. 2017.11.08.11.
 */
public interface Leader {

  /**
   * 获取自己的ID
   *
   * @return
   */
  String getMyId();

  /**
   * 获取当前集群中的leader ID
   *
   * @return
   *
   * @throws Exception
   */
  String getLeaderId() throws Exception;

  /**
   * 注册监听leader切换事件
   *
   * @param listener
   */
  void addListener(LeaderChangedListener listener);

  /**
   * 开始选举leader
   */
  void start();

  /**
   * 销毁选举对象
   */
  void destroy();
}
