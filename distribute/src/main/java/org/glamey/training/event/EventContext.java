package org.glamey.training.event;

import com.google.common.collect.Lists;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class EventContext {

    private List<EventObserver> eventListenerList;

    private Executor executor;

    public EventContext() {
        eventListenerList = Lists.newArrayList();
    }

    public EventContext(Executor executor) {
        this();
        this.executor = executor;
    }

    public void register(EventObserver eventObserver) {
        this.eventListenerList.add(eventObserver);
    }


    public void deRegister(EventObserver eventObserver) {
        this.eventListenerList.remove(eventObserver);
    }

    public void publish(EventObject eventObject) {
        if (eventListenerList.size() == 0) {
            return;
        }

        if (Objects.nonNull(executor)) {
            eventListenerList.stream().forEach(listener -> executor.execute(() -> listener.onEvent(eventObject)));
        } else {
            eventListenerList.stream().forEach(listener -> listener.onEvent(eventObject));
        }


    }

}
