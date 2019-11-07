package org.glamey.training.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 多个线程交替打印递增数列
 * <p>
 * 形如：3个线程交替打印 1-100
 * <p>
 * 原理 ： 利用semaphore单信号量，实现单线程同步执行。
 *
 * @author yang.zhou 2019.11.07.12
 */
public class Semaphores {
    static int result = 0;
    static int THREAD_COUNT = 3;
    static int MAX = 100;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        Semaphore[] semaphores = new Semaphore[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            //均设置permit = 1
            semaphores[i] = new Semaphore(1);

            //只保留一个thread的permit = 1，线程开启的时候，先通过此线程进入获取授权，做打印输出。
            if (i != THREAD_COUNT - 1) {
                semaphores[i].acquire();
            }
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            final Semaphore cur = semaphores[i];
            final Semaphore worker = i == 0 ? semaphores[THREAD_COUNT - 1] : semaphores[i - 1];
            final int index = i;
            threads[index] = new Thread(() -> {
                while (true) {
                    try {
                        worker.acquire();
                        System.out.println(String.format("thread %d -> %d", index, result++));
                        if (result > MAX - 1) {
                            System.exit(0);
                        }
                        cur.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            threads[index].start();
        }
    }
}
