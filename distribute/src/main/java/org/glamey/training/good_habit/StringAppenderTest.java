package org.glamey.training.good_habit;

import com.google.common.collect.Lists;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author zhouyang.zhou. 2017.09.26.14.
 */
public class StringAppenderTest {

    public static void main(String[] args) {
        plus();
        stringPlusEffective();
        sortChinaLetters();
    }

    /**
     * 中文字符排序
     */
    private static void sortChinaLetters() {
        List<String> list = Lists.newArrayList("张三(Z)", "李四(L)", "王五(W)");
        String[] array = list.toArray(new String[list.size()]);
        /**
         * 按照中文来排序
         * 如果没有比较器的话，默认按照String.compareTo来比较，底层是Unicode码值进行操作。
         *
         * 一般排序使用Collator即可，如果要求严格的话，可以转化为拼音来操作，但是就需要考虑多音字之类的操作
         */
        Comparator comparator = Collator.getInstance(Locale.CHINA);
        Arrays.sort(array, comparator);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 字符串相加：正常相加一直碰到字符串之后，才转变为字符串拼接，如果是对象的话，调用默认的toString方法。
     */
    private static void plus() {
        String base = "apple";
        String a = 1 + 2 + base;
        System.out.println(a);

        String b = base + 1 + 2;
        System.out.println(b);

        String c = (1 + 2) + base;
        System.out.println(c);
    }

    /**
     * 通过+进行字符串拼接，耗时[6372]
     * 通过concat进行字符串拼接，耗时[4718]
     * 通过StringBuffer进行字符串拼接，耗时[11]
     * 通过StringBuilder进行字符串拼接，耗时[3]
     */
    public static void stringPlusEffective() {
        int count = 100000;
        /**
         * 加号
         * 最慢，虽然JVM做了优化，转变成了 result = new StringBuffer(result).append("a").toString();
         * 但是创建了10W个StringBuilder对象，比较耗时。
         */
        String result = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += "a";
        }
        long end = System.currentTimeMillis();
        System.out.printf("通过+进行字符串拼接，耗时[%d]\n", end - start);

        /**
         * concat
         * 底层实现了，进行了大量的ArrayCopy,但是最终却新建了10WString对象，比较耗时
         */
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result = result.concat("a");
        }
        end = System.currentTimeMillis();
        System.out.printf("通过concat进行字符串拼接，耗时[%d]\n", end - start);

        /**
         * StringBuffer
         * 虽然使用了字符串拼接的方式，但是append方法使用了sync，因此加锁会耗费一定的性能。
         */
        StringBuffer buffer = new StringBuffer();
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            buffer.append("a");
        }
        end = System.currentTimeMillis();
        System.out.printf("通过StringBuffer进行字符串拼接，耗时[%d]\n", end - start);

        /**
         * StringBuilder
         * 最快，仅仅是最底层的数组扩容和数组移动
         */
        StringBuilder builder = new StringBuilder();
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            builder.append("a");
        }
        end = System.currentTimeMillis();
        System.out.printf("通过StringBuilder进行字符串拼接，耗时[%d]\n", end - start);
    }
}
