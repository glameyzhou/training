package org.glamey.training.good_habit;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;

/**
 *
 * ArrayList.subList返回的是SubList，仅仅是ArrayList的一个视图，所有的针对SubList的操作都会映射到ArrayList上面，
 * 具体内容，参考源代码，例如SubList.update(index, element)-->ArrayList.this.update(offset + index, element);
 * @author zhouyang.zhou. 2017.09.28.18.
 */
public class SubListDemo {

  public static void main(String[] args) {
    List<Integer> list = Lists.newArrayList();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    List<Integer> subList = list.subList(0, 4);
    for (Integer sub : subList) {
      System.out.println(sub);
    }
    System.out.println("----");

    subList.set(0, 1);
    for (Integer sub : subList) {
      System.out.println(sub);
    }
    System.out.println("---");

    for (Integer integer : list) {
      System.out.println(integer);
    }


    //to Array
    Integer[] integers = list.toArray(new Integer[list.size()]);
    List<Integer> asList = Arrays.asList(integers);
    // asList是Arrays的子类，并没有实现add方法，所以会抛出异常
    asList.add(1);

  }
}
