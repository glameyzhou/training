package org.glamey.training.proxy;

/**
 * @author zhouyang.zhou. 2016.12.26.14.
 */
public class HelloWordImpl implements HelloWord {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override public void sayHello(String name) {
    System.out.println(String.format("hello, %s", name));
  }
}
