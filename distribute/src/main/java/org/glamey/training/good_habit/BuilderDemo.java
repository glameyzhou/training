package org.glamey.training.good_habit;

/**
 * 效果等同于lombok.builder
 *
 * @author zhouyang.zhou. 2017.09.26.09.
 */

public class BuilderDemo {


  public static void main(String[] args) {
    BuilderDemo demo = new Builder().address("add").userName("zhangsan").build();
    System.out.println(demo.getUserName() + " " + demo.getAddress());

//    BuilderDemo demo = new Builder().address("add").userName("zhangsan").build();
  }

  private String userName;
  private String address;

  public BuilderDemo(Builder builder) {
    this.userName = builder.userName;
    this.address = builder.address;
  }

  public String getUserName() {
    return userName;
  }

  public String getAddress() {
    return address;
  }

  public static class Builder implements org.apache.commons.lang3.builder.Builder<BuilderDemo> {

    private String userName;
    private String address;

    public Builder userName(String userName) {
      this.userName = userName;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    @Override public BuilderDemo build() {
      BuilderDemo demo = new BuilderDemo(this);
      reset();
      return demo;
    }

    private void reset() {
      this.userName = null;
      this.address = null;
    }
  }
}
