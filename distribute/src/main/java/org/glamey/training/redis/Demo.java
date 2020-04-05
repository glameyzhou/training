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

        TreeSet<Integer> treeSet = new TreeSet<>((a, b) -> b - a);
        treeSet.add(1);
        treeSet.add(10);
        treeSet.add(4);
        System.out.println(treeSet);


        /**
         * 按照value 倒叙排列
         * 结果应该是：
         *
         * 3,20 1,10 2,8 4,3
         */
        TreeSet<Entry> entries = new TreeSet<>((a, b) -> b.value - a.value);
        entries.add(new Entry(1, 10));
        entries.add(new Entry(2, 8));
        entries.add(new Entry(3, 20));
        entries.add(new Entry(4, 3));

        for (Entry entry : entries) {
            System.out.println(entry.key + " " + entry.key);
        }
    }


    static class Entry {
        public Integer key;
        public Integer value;

        Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
