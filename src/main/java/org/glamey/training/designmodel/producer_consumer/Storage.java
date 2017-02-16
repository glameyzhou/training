package org.glamey.training.designmodel.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 存储对象
 * @author zhouyang.zhou. 2017.02.15.17.
 */
public class Storage {

  private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(50);

  public void push(Integer item) throws InterruptedException {
    queue.put(item);
    System.out.println(String.format("push message --> %s", item));
  }

  public Integer pop() throws InterruptedException {
    Integer item = queue.poll();
    System.out.println(String.format("pop message --> %s", item));
    return item;
  }
}
