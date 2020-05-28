package org.glamey.training.codes.sort;

import com.google.common.collect.Lists;
import org.glamey.training.codes.Utils;

import java.util.*;

/**
 * 针对集合的洗牌函数
 *
 * @author yang.zhou 2019.11.04.14
 */
public class ShuffleCollection {

    public static void main(String[] args) {
        testNoRandomAccessArray();
        testRandomAccessArray();
    }

    private static ArrayList<Integer> generateList() {
        ArrayList<Integer> list = Lists.newArrayList(1, 3, 45, 6, 8, 1, 19, 30);
        System.out.println("origin list is \n" + list);
        return list;
    }

    public static void testRandomAccessArray() {
        System.out.println("random access list");
        List<Integer> list = generateList();
        for (int i = 0; i < 10; i++) {
            shuffle(list);
            System.out.println(list);
        }
    }

    public static void testNoRandomAccessArray() {
        System.out.println("no random access list");
        List<Integer> list = Lists.newLinkedList(generateList());
        for (int i = 0; i < 10; i++) {
            shuffle(list);
            System.out.println(list);
        }
    }


    private static Random r;

    public static void shuffle(List<?> list) {
        Random rnd = r;
        if (rnd == null) {
            r = rnd = new Random();
        }
        shuffle(list, rnd);
    }

    private static void shuffle(List<?> list, Random rnd) {
        int size = list.size();
        if (list instanceof RandomAccess) {
            for (int i = size; i > 1; i--) {
                swap(list, i - 1, rnd.nextInt(i));
            }
        } else {
            Object[] array = list.toArray();
            for (int i = size; i > 1; i--) {
                Utils.swap(array, i - 1, rnd.nextInt(i));
            }

            ListIterator iterator = list.listIterator();
            for (int i = 0; i < array.length; i++) {
                iterator.next();
                iterator.set(array[i]);
            }

        }
    }

    private static void swap(List<?> list, int i, int j) {
        final List l = list;
        l.set(i, l.set(j, l.get(i)));
    }
}
