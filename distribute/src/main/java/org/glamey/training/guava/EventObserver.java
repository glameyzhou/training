package org.glamey.training.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author zhouyang.zhou.2016.10.08 15.
 */
public class EventObserver {

    @Subscribe
    public void onMessage(Integer integer) {
        System.out.println("Integer is --> " + integer);
    }

    @Subscribe
    public void onMessage(String string) {
        System.out.println("String is --> " + string);
    }
}
