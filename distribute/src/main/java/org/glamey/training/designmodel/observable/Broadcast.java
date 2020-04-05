package org.glamey.training.designmodel.observable;

/**
 * @author zhouyang.zhou. 2017.08.29.15.
 */
public class Broadcast extends Observable {

  private String message;

  /**
   * send the message
   *
   * @param message
   */
  public void sendMessage(String message) {
    System.out.println("广播消息：" + message);
    this.message = message;
    super.notifyAllObservers();
  }

  /**
   * get the message
   *
   * @return
   */
  public String getMessage() {
    return message;
  }
}
