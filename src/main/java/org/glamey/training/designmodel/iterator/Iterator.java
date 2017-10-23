package org.glamey.training.designmodel.iterator;

/**
 * @author zhouyang.zhou. 2017.10.22.20.
 */
public interface Iterator<E> {

  boolean hasNext();

  <E> E next();
}
