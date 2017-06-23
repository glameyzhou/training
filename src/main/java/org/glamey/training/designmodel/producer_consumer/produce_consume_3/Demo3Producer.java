package org.glamey.training.algorithm.demo.produce_consume_3;

import java.util.concurrent.BlockingQueue;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo3Producer implements Runnable {
    private BlockingQueue<String> queue;

    public Demo3Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                String msg = String.format("put into the queue, %s,%d", Thread.currentThread().getId(), i);
                queue.put(msg); // notFull lock
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
