package org.glamey.training.jvm.oom;

/**
 * @author zhouyang.zhou. 2017.09.14.09.
 */
public class OOMObject {

  private Long id;
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override public String toString() {
    return "OOMObject{" +
        "id=" + id +
        ", description='" + description + '\'' +
        '}';
  }
}
