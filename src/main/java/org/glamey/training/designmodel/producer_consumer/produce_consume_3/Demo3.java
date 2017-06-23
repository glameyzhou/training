package org.glamey.training.designmodel.producer_consumer.produce_consume_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhouyang.zhou. 2017.06.22.11.
 */
public class Demo3 {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        Demo3Producer producer = new Demo3Producer(queue);
        Demo3Consumer consumer = new Demo3Consumer(queue);

        for (int i = 0; i < 5; i++) {
            new Thread(producer, "p_" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(consumer, "c_" + i).start();
        }
    }
}
