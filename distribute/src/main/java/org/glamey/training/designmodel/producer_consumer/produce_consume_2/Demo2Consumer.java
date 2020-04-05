package org.glamey.training.designmodel.producer_consumer.produce_consume_2;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo2Consumer implements Runnable {
    private String name;
    private Demo2Machine machine;

    public Demo2Consumer(String name, Demo2Machine machine) {
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
