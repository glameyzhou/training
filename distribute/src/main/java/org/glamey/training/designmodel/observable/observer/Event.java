package org.glamey.training.designmodel.observable.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouyang.zhou. 2017.11.07.17.
 */

@Data
@AllArgsConstructor
public class Event<T> {
  private T t;
}
