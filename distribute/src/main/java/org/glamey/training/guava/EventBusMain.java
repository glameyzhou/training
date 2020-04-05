package org.glamey.training.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou.2016.10.08 15.
 */
public class EventBusMain {

    public static void main(String[] args) throws InterruptedException {
//        EventBus eventBus = new EventBus();
//        eventBus.register(new EventObserver());
//        doWork(eventBus);
//        System.out.println("---------------------------------------->");

        //消息收到后立即响应，处理时间以来Observer
        EventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(2));
        asyncEventBus.register(new EventObserver());
        doWork(asyncEventBus);
        System.out.println("---------------------------------------->");
    }

    private static void doWork(EventBus eventBus) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                eventBus.post(i);
            } else {
                eventBus.post(String.valueOf(i));
            }
            TimeUnit.MICROSECONDS.sleep(100);
        }
    }


}
