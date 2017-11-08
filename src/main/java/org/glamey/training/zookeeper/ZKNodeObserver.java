package org.glamey.training.zookeeper;

import lombok.extern.slf4j.Slf4j;

/**
 * ZK节点观察者
 *
 * @author zhouyang.zhou. 2017.11.07.19.
 */
@Slf4j
public class ZKNodeObserver {

  private final ZKClient client;

  private SubjectChangedListener subjectChangedListener;

  public ZKNodeObserver(String address) {
    client = ZKClientCache.get(address);
    subjectChangedListener = new SubjectChangedListener();
    initPathListener();
  }

  private void initPathListener() {
    try {
      client.listChildren(ZKConstants.ZK_ROOT, subjectChangedListener, true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private class SubjectChangedListener implements NodeListener {
    @Override
    public void onChanged(ZKClient client, ChangedEvent event) throws Exception {
      ChangedEvent.Type type = event.getType();
      switch (type) {
        //子节点新增，可以做服务上线通知
        case CHILD_ADDED:
          log.info("child added,path=[{}],data=[{}]", event.getPath(), client.get(event.getPath()));
          break;

        //子节点删除，可以做服务下线通知
        case CHILD_REMOVED:
          log.info("child remove,path=[{}]", event.getPath());
          break;

        //子节点数据变更，可以做服务地址变更通知
        case CHILD_UPDATED:
          log.info("child update,path=[{}],new data=[{}]", event.getPath(), client.get(event.getPath()));
          break;
      }
    }
  }

  public void process() {
    //  服务上下线，可以通过event bus 进行通知
  }
}
