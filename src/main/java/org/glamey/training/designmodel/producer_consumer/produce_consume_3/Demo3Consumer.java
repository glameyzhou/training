package org.glamey.training.designmodel.producer_consumer.produce_consume_3;

import java.util.concurrent.BlockingQueue;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo3Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Demo3Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                String msg = String.format("consume the queue, %s,%d", Thread.currentThread().getId(), i);
                queue.take(); // notEmpty lock,
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
