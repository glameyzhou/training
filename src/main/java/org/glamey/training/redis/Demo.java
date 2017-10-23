package org.glamey.training.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author zhouyang.zhou. 2017.10.08.21.
 */
public class Demo {

  public static void main(String[] args) {
    List<Integer> list = Lists.newArrayList();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    System.out.println(list);

    Set<String> set = Sets.newHashSet();
    set.add("c");
    set.add("a");
    set.add("w");
    for (String s : set) {
      System.out.println(s);
    }

    TreeSet<Integer> treeSet = new TreeSet<>();
    treeSet.add(1);
    treeSet.add(10);
    treeSet.add(4);
    System.out.println(treeSet);
  }
}
