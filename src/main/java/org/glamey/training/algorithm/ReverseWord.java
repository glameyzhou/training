package org.glamey.training.algorithm;

import java.util.Stack;

/**
 * 字符根据空格进行翻转
 *
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class ReverseWord {

    public static void main(String[] args) {
        methodA();

        int[] array = {0, 0, 1, 1, 2, 3, 3, 4, 5, 5};
        int len = removeDuplicate(array);
        for (int i = 0; i <= len; i++) {
            System.out.println(array[i]);
        }
    }

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

    private static void methodA() {
        String string = "hello world java";
        Stack<String> stack = new Stack<>();
        char[] chars = string.toCharArray();
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


        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
