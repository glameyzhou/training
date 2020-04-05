package org.glamey.training.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author zhouyang.zhou. 2017.07.20.14.
 */
public class DemoPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    public void publish(String message) {
        publisher.publishEvent(new DemoEvent(message));
    }
}
