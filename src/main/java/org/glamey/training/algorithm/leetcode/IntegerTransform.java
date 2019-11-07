package org.glamey.training.algorithm.leetcode;

/**
 * 字符串转化为int类型
 * <p>
 * 方法：
 * string to int '' - '0'
 * int to string '' + '0'
 *
 * @author zhouyang
 * @date 2019.03.12.18.
 */
public class IntegerTransform {


    public static void main(String[] args) {

        System.out.println("123 -> " + strToInt("123"));
        System.out.println("1230 -> " + strToInt("1230"));
        System.out.println("0123 -> " + strToInt("0123"));
        System.out.println("-123 -> " + strToInt("-123"));
        System.out.println("2147483647 -> " + strToInt("2147483647"));
        System.out.println("-2147483647 -> " + strToInt("-2147483647"));
//        System.out.println("-2147483648 -> " + strToInt("-2147483648"));
//        System.out.println("2147483648 -> " + strToInt("2147483648"));
    }

    public static int strToInt(String source) {
        if (source == null || "".equals(source) || "".equals(source.trim())) {
            throw new IllegalArgumentException("illegalArgument " + source);
        }
        source = source.trim();
        int startIndex = 0;
        boolean negative = false;
        char[] chars = source.toCharArray();
        int len = chars.length;
        char firstChar = chars[0];
        if (firstChar < '0') {
            if (firstChar == '-') {
                startIndex = 1;
                negative = true;
            } else if (firstChar == '+') {
                startIndex = 1;
            } else {
                throw new IllegalArgumentException("illegalArgument " + source);
            }
        }
        int val = 0;
        char tmp;
        for (int i = startIndex; i < len; i++) {
            tmp = chars[i];
            if (tmp > '9' || tmp < '0') {
                throw new IllegalArgumentException("illegalArgument " + source);
            }
            int curInt = tmp - '0';
            //越界问题 MAX_VALUE = 2147483647, MIN_VALUE = -2147483648

            //负数
            if (negative && (val == Integer.MAX_VALUE / 10 && (curInt + Integer.MIN_VALUE % 10) == 0)) {
                throw new IllegalArgumentException(String.format("the value <= %d, %s", Integer.MIN_VALUE, source));
            }

            //正数
            if (!negative && (val == Integer.MAX_VALUE && curInt > Integer.MAX_VALUE % 10)) {
                throw new IllegalArgumentException(String.format("the value > %d, %s", Integer.MAX_VALUE, source));
            }

            val = val * 10 + curInt;
        }
        return negative ? -val : val;
    }
}
