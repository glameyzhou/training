package org.glamey.training.event;

import java.util.EventObject;

public class Main {


    public static void main(String[] args) {
        EventContext context = new EventContext();

        //新增两个observer
        context.register(eventObject -> System.out.println("[event_observer]-[a]-[" + eventObject + "]"));

        EventObserver eventObserver = eventObject -> System.out.println("[event_observer]-[b]-[" + eventObject + "]");
        context.register(eventObserver);


        for (int i = 0; i < 4; i++) {
            context.publish(new EventObject("[event_object]-[" + i + "]"));
        }


        System.out.println("--------------------------------");
        context.deRegister(eventObserver);

        for (int i = 0; i < 4; i++) {
            context.publish(new EventObject("[event_object]-[" + i + "]"));
        }

    }
}
