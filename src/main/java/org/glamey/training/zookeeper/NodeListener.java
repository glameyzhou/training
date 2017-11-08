package org.glamey.training.zookeeper;

/**
 * 节点监听事件
 *
 * @author zhouyang.zhou. 2017.11.07.19.
 */
public interface NodeListener {
  void onChanged(ZKClient client, ChangedEvent event) throws Exception;
}
