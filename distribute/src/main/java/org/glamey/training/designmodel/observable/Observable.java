package org.glamey.training.designmodel.observable;

import java.util.Vector;

/**
 * @author zhouyang.zhou. 2017.08.29.15.
 */
public class Observable {

  private final Vector<Observer> observers = new Vector<>();

  public void addObserver(Observer observer) {
    this.observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    this.observers.remove(observer);
  }

  public void notifyAllObservers() {
    for (Observer observer : observers) {
      observer.change(this);
    }
  }
}
