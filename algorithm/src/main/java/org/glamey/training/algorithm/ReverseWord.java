package org.glamey.training.algorithm;

import java.util.Stack;

/**
 * 字符根据空格进行翻转
 *
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class ReverseWord {

    public static void main(String[] args) {
        String source = "hello world java";
        String reverseWords = reverseWords(source);
        System.out.println(reverseWords);

        int[] array = {0, 0, 1, 1, 2, 3, 3, 4, 5, 5};
        int len = removeDuplicate(array);
        for (int i = 0; i <= len; i++) {
            System.out.println(array[i]);
        }
    }


    /**
     * 删除重复数字，返回不重复的数组长度
     * @param array 待删除的数组
     * @return int  不重复的数组下标
     */
    public static int removeDuplicate(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[index] != array[i]) {
                index += 1;
                array[index] = array[i];
            }
        }
        return index;
    }

    private static String reverseWords(String source) {
        Stack<String> stack = new Stack<>();
        char[] chars = source.toCharArray();
        StringBuilder builder = new StringBuilder(20);
        for (char aChar : chars) {
            boolean whitespace = Character.isWhitespace(aChar);
            if (whitespace) {
                if (builder.length() > 0) {
                    stack.push(builder.toString());
                    builder.setLength(0);
                }
            } else {
                builder.append(aChar);
            }
        }
        if (builder .length() > 0) {
            stack.push(builder.toString());
            builder.setLength(0);
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop()).append(" ");
        }
        return builder.toString();
    }
}
