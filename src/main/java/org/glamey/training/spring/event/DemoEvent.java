package org.glamey.training.spring.event;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author zhouyang.zhou. 2017.07.20.14.
 */
@ToString
public class DemoEvent extends ApplicationEvent {

    private String message;

    public DemoEvent(String source) {
        super(source);
        this.message = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
