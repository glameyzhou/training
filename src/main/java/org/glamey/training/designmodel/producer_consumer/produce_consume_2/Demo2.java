package org.glamey.training.designmodel.producer_consumer.produce_consume_2;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou. 2017.06.22.11.
 */
public class Demo2 {
    public static void main(String[] args) {
        Demo2Machine machine = new Demo2Machine();

        for (int i = 0; i < 5; i++) {
            new Thread(new Demo2Producer("p_" + i, machine)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Demo2Consumer("c_" + i, machine)).start();
        }


        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
