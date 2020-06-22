package org.glamey.training.designmodel.observable.observer;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou. 2017.11.07.17.
 */
public class Demo {

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        List<Event<String>> events = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            events.add(new Event("我是消息_" + i));
        }

        final BroadCaster broadCaster = new BroadCaster(EXECUTOR);
        broadCaster.register(event -> System.out.printf("我是监听器[%s],收到的消息内容是:%s\n", "a", event));

        broadCaster.register(event -> System.out.printf("我是监听器[%s],收到的消息内容是:%s\n", "b", event));

        events.forEach(e -> broadCaster.publishEvent(e));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((ThreadPoolExecutor) EXECUTOR).shutdown();
    }
}
