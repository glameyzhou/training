package org.glamey.training.codes;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 字符根据空格进行翻转
 *
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class ReverseWord {

    public static void main(String[] args) {
        String source = "hello world java";
        System.out.println(reverseWords(source));
        System.out.println(_reverseWords(source));

        int[] nums = {0, 0, 1, 1, 2, 3, 3, 4, 5, 5};
        System.out.println(removeDuplicate(nums));
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 删除重复数字，返回不重复的数组长度
     *
     * @param nums 待删除的数组
     * @return int  不重复的数组下标
     */
    public static int removeDuplicate(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index += 1;
                nums[index] = nums[i];
            }
        }
        return index;
    }

    /**
     * 通过双端队列来实现
     * 需要删除字符串头尾的空格
     *
     * @param source
     * @return
     */
    private static String _reverseWords(String source) {
        if (source == null || "".equals(source)) {
            return source;
        }
        int left = 0, right = source.length() - 1;
        while (left <= right && source.charAt(left) == ' ') {
            ++left;
        }
        while (left <= right && source.charAt(right) == ' ') {
            --right;
        }
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = source.charAt(left);
            if (word.length() != 0 && c == ' ') {
                deque.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        return String.join(" ", deque);
    }

    /**
     * 通过栈的形式来处理。
     *
     * @param source
     * @return
     */
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
        if (builder.length() > 0) {
            stack.push(builder.toString());
            builder.setLength(0);
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop()).append(" ");
        }
        return builder.toString();
    }
}
