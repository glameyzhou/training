package org.glamey.training.designmodel.producer_consumer.produce_consume_1;

/**
 * @author zhouyang.zhou. 2017.06.22.10.
 */
public class Demo1Machine {

    private static final int threshold = 10;
    private int count = 0;
    private Object lock = new Object();

    public void produce(String name) throws InterruptedException {

        synchronized (lock) {
            while (count == threshold) {
                System.out.printf("the queue[%s] is full[%d], please waiting...\r\n", name, count);
                lock.wait();
            }
            count++;
            System.out.printf("produce[%s] ok, count=[%d]\r\n", name, count);
            lock.notifyAll();
        }
    }

    public void consume(String name) throws InterruptedException {
        synchronized (lock) {
            while (count == 0) {
                System.out.printf("the queue[%s] is empty[%d], please waiting...\r\n", name, count);
                lock.wait();
            }

            count--;
            System.out.printf("consume[%s] ok, count=[%d]\r\n", name, count);
            lock.notifyAll();

        }
    }
}
