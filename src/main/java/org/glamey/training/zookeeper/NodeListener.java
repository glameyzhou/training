package org.glamey.training.zookeeper;

/**
 * @author zhouyang.zhou. 2017.11.07.19.
 */
public interface NodeListener {
  void onChanged(ZKClient client, ChangedEvent event) throws Exception;
}
