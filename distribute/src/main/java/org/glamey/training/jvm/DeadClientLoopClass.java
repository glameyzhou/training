package org.glamey.training.jvm;

/**
 * JVM会保证一个类的<client>()方法会在多线程环境下，正确加锁、同步。
 * 如果多个线程同时初始化class,那么只会有一个线程去执行<client>(),其他线程都要进行等待，直到活动线程执行完毕<client>()
 * <p>
 * 如果class中的<client>()方法特别耗时，可能会造成其他线程的阻塞。
 * <p>
 * <p>
 * import....
 * 其实，其他线程被唤起后，不会再次进入<client>()。同一个类加载器下，一个类型只会被初始化一次。
 *
 * @author yang.zhou 2019.11.18.17
 */
public class DeadClientLoopClass {


    static {
        /**
         * 模拟耗时，阻塞操作
         *
         * 可以看到两个线程同时初始化。
         * 只有一个线程执行<client>()
         *
         *
         * 如果没有if 编译期报错  Initalizer must be able to compelete normally
         */
        if (true) {
            Long id = Thread.currentThread().getId();
            String name = Thread.currentThread().getName();
            System.out.println(id + " " + name + "  client()...");
            while (true) {
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "... start");
                new DeadClientLoopClass();
                System.out.println(Thread.currentThread().getName() + ".... finish");
            }).start();
        }

    }
}
