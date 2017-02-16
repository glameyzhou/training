package org.glamey.training.designmodel.producer_consumer;

/**
 * @author zhouyang.zhou. 2017.02.15.17.
 */
public class Consumer {

  private Storage storage = new Storage();

  public void consume() throws InterruptedException {
    storage.pop();
  }

  public static void main(String[] args) throws InterruptedException {
    Consumer consumer = new Consumer();
    while (true) {
      consumer.consume();
    }
  }
}
