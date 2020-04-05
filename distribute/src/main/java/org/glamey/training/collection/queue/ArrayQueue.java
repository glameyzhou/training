package org.glamey.training.collection.queue;

/**
 * {@link com.sun.jmx.remote.internal.ArrayQueue}
 *
 * @author yang.zhou 2019.11.14.17
 */
public class ArrayQueue<T> implements Queue<T> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void enqueue(T t) {

    }

    @Override
    public T dequeue() {
        return null;
    }
}
