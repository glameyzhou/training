package org.glamey.training.designmodel.producer_consumer;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou. 2017.02.15.17.
 */
public class Producer {

  private final Storage storage = new Storage();
  public void pushItem(Integer item) throws InterruptedException {
      storage.push(item);
  }

  public static void main(String[] args) throws InterruptedException {
    Producer producer = new Producer();
    int index = 0;
    while (true) {
      producer.pushItem(index);
      index ++;
      TimeUnit.SECONDS.sleep(1);
    }
  }
}
