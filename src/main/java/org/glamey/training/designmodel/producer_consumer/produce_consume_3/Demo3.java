package org.glamey.training.algorithm.demo.produce_consume_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhouyang.zhou. 2017.06.22.11.
 */
public class Demo3 {

    public static void main(String[] args) {
        /*BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        Demo3Producer producer = new Demo3Producer(queue);
        Demo3Consumer consumer = new Demo3Consumer(queue);

        for (int i = 0; i < 5; i++) {
            new Thread(producer, "p_" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(consumer, "c_" + i).start();
        }


        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();*/


        String s1 = "abc";
        String s2 = "abc";
        String s3 = "ab" + "c";
        String s4 = new String("abc");
        String s5 = s1;
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s1 == s4);
        System.out.println(s5 == s1);

    }
}
