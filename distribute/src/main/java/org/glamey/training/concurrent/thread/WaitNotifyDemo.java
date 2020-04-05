package org.glamey.training.concurrent.thread;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.17.21.
 */
public class WaitNotifyDemo {

    private static final Object LOCK = new Object();
    private static int pool_size = 0;
    private static final int POOL_MAX_SIZE = 5;

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread p = new Thread(new Producer(""), "producer_" + i);
            p.start();
        }

        for (int i = 0; i < 15; i++) {
            Thread c = new Thread(new Consumer(), "consumer_" + i);
            c.start();
        }
    }

    private static class Producer implements Runnable {
        private String message;

        public Producer(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (pool_size == POOL_MAX_SIZE) {
                        try {
                            System.out.println("队列已满，...");
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        pool_size++;
                        System.out.println("发送消息 --> " + message);
                        LOCK.notifyAll();
                    }
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        public Consumer() {
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (pool_size == 0) {
                        System.out.println("队列无任务，唤醒生产者...");
                        LOCK.notifyAll();
                    } else {
                        pool_size--;
                        System.out.println("成功消费数据...");
                    }
                }
            }
        }
    }
}
