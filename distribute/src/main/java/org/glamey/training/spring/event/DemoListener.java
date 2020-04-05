package org.glamey.training.spring.event;

import org.springframework.context.ApplicationListener;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou. 2017.07.20.14.
 */
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        try {
            System.out.println("listener.....start..." + event);
            System.out.println("listener.....processing..." + event);
            TimeUnit.SECONDS.sleep(5);
            System.out.println("listener.....finish..." + event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
