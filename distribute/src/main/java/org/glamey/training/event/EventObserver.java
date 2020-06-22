package org.glamey.training.event;

import java.util.EventListener;
import java.util.EventObject;

public interface EventObserver extends EventListener {

    void onEvent(EventObject eventObject);
}
