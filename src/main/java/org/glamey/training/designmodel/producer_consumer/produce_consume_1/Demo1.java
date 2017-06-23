package org.glamey.training.algorithm.demo.produce_consume_1;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo1 {

    public static void main(String[] args) {
        Demo1Machine machine = new Demo1Machine();

        for (int i = 0; i < 5; i++) {
            new Thread(new Demo1Producer("p_" + i, machine)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Demo1Consumer("c_" + i, machine)).start();
        }


        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
