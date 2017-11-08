package org.glamey.training.zookeeper;

/**
 * 节点变更类型
 *
 * @author zhouyang.zhou. 2017.11.07.19.
 */
public class ChangedEvent {
  public enum Type {
    CHILD_ADDED,
    CHILD_UPDATED,
    CHILD_REMOVED;
  }

  private String path;
  private Type type;

  public ChangedEvent(String path, Type type) {
    this.path = path;
    this.type = type;
  }

  public String getPath() {
    return this.path;
  }

  public Type getType() {
    return type;
  }
}
