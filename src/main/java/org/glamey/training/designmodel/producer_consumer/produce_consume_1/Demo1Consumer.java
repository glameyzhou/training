package org.glamey.training.algorithm.demo.produce_consume_1;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo1Consumer implements Runnable {
    private String name;
    private Demo1Machine machine;

    public Demo1Consumer(String name, Demo1Machine machine) {
        this.name = name;
        this.machine = machine;
    }

    @Override
    public void run() {
        while (true) {
            try {
                machine.consume(this.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
