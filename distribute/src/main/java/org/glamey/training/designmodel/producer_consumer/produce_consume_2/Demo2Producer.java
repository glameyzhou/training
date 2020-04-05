package org.glamey.training.designmodel.producer_consumer.produce_consume_2;

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
