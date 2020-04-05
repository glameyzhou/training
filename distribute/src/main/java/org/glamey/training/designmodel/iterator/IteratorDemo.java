package org.glamey.training.designmodel.iterator;

/**
 * @author zhouyang.zhou. 2017.10.22.20.
 */
public class IteratorDemo {

  public static void main(String[] args) {
    Container repository = new Repository();

    Iterator iterator = repository.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
