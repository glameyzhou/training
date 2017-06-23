package org.glamey.training.algorithm.demo.produce_consume_2;

import org.glamey.training.algorithm.demo.produce_consume_1.Demo1Machine;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo2Producer implements Runnable {
    private String name;
    private Demo2Machine machine;

    public Demo2Producer(String name, Demo2Machine machine) {
        this.name = name;
        this.machine = machine;
    }

    @Override
    public void run() {
        while (true) {
            try {
                machine.produce(this.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
