package org.glamey.training.designmodel.observable.Demo;

/**
 * @author yang.zhou 2019.11.04.15
 */
public class Main {
    public static void main(String[] args) {
        EventPublisher publisher = new EventPublisher();
        publisher.registe(eventObj -> System.out.println(String.format("listener[1] -> %d -> %s", eventObj.getId(), eventObj.getT())));
        publisher.registe(eventObj -> System.out.println(String.format("listener[2] -> %d -> %s", eventObj.getId(), eventObj.getT())));
        publisher.publish(new EventObj("asdfsd"));
    }
}
