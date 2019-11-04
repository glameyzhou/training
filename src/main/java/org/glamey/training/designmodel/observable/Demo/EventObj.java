package org.glamey.training.designmodel.observable.Demo;

/**
 * @author yang.zhou 2019.11.04.15
 */
public class EventObj<T extends java.io.Serializable> {
    private int id;
    private T t;

    public EventObj(T t) {
        this.t = t;
        this.id = t.hashCode();
    }

    public int getId() {
        return id;
    }

    public T getT() {
        return t;
    }
}

