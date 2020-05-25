package org.glamey.training.concurrent;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://juejin.im/post/5eacc1c75188256d976df748
 *
 * 场景：线程A-B交替执行。
 *  A先获取锁，然后业务逻辑，然后释放CPU。
 *  B获取锁之后，执行业务逻辑，然后唤醒A。
 *
 *  流程如下：
 *      1、AB获取锁，最终A拿到锁，B在FIFO队列中。
 *      2、A执行业务逻辑后，通过await释放CPU，这个时候A进行Condition队列。
 *      3、B获取CPU时间片，尝试获取锁。
 *      4、B执行业务逻辑后，signal唤醒A。
 *      5、B执行后续业务逻辑，unlock。
 *      6、A执行后续业务逻辑，unlock。
 *
 *  总结：
 *      condition/object(wait/notify)
 *      1、Condition可以根据一个或者多个condition作为条件进行控制。 object(wait/notify)只能和sync公用，并且只能唤醒一个或者全部。
 *      2、condition与lock一起使用，注意lock与unlock匹配即可。有类似于await机制，不会出现唤醒挂起后的死锁现象。
 *      底层是通过LockSupport.park/unPark实现。
 *
 *
 *
 */
public class AnalyzerCondition {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("线程1加锁成功");
                System.out.println("线程1挂起");
                condition.await();
                System.out.println("线程1被唤起");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程1解锁成功");
            }

        }).start();


        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("线程2加锁成功");
                condition.signal();

                /**
                 * 一定要注意，唤起其他线程之后，自己线程内部的方法执行完毕，才会执行唤起线程的方法体内容
                 */
                System.out.println("线程2唤醒线程1");
            } finally {
                lock.unlock();
                System.out.println("线程2解锁成功");
            }
        }).start();
    }
}
