package org.glamey.training.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yang.zhou 2020.01.19.16
 */
public class Demo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.remove(1);
        System.out.println(list.size());

        List<String> targetList = Lists.newArrayList("b", "c");
        String[] target = targetList.toArray(new String[0]);
        String[] originData = list.toArray(new String[0]);
        int r = 0, w = 0;

        for (; r < list.size(); r++) {
            if (targetList.contains(originData[r])) {
                originData[w++] = originData[r++];
            }
        }

        if (w != list.size()) {

        }







        /*Father[] fathers = new son[]{};
        System.out.println(fathers.getClass());


        List<String> list = new MyList();
        list.add("bbb");
        String[] objects = list.toArray(new String[0]);
        System.out.println(objects.getClass());

        Object[] objects1 = list.toArray();
        System.out.println(objects1.getClass());


        System.out.println(list.toArray().getClass());*/
    }


    static class Father {

    }

    static class son extends Father {

    }

    static class MyList extends ArrayList<String> {
        @Override
        public String[] toArray() {
            return new String[]{"a", "b"};
        }
    }
}
