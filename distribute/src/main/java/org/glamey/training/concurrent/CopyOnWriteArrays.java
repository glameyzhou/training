package org.glamey.training.concurrent;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yang.zhou 2019.11.08.11
 */
public class CopyOnWriteArrays<E> {

    private ReentrantLock lock = new ReentrantLock();
    private volatile Object[] array;


    private Object[] getArray() {
        return array;
    }

    private void setArray(Object[] a) {
        array = a;
    }

    public CopyOnWriteArrays() {
        setArray(new Object[0]);
    }

    public boolean add(E e) {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * may throws outOfIndexException
     *
     * @param index
     * @return
     */
    public E get(int index) {
        return get(getArray(), index);
    }

    private E get(Object[] array, int index) {
        return (E) array[index];
    }
}
