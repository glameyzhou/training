package org.glamey.training.designmodel.observable.Demo;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yang.zhou 2019.11.04.15
 */
public class EventPublisher {
    private List<EventListener> listeners;

    public EventPublisher() {
        this.listeners = new CopyOnWriteArrayList<>();
    }

    public void registe(EventListener listener) {
        listeners.add(listener);
    }

    public void remote(EventListener listener) {
        listeners.remove(listener);
    }

    public void publish(EventObj obj) {
        if (CollectionUtils.isEmpty(listeners)) {
            return;
        }
        listeners.forEach(listener -> listener.onListening(obj));
    }
}
