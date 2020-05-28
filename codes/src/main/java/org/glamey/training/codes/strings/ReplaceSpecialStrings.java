package org.glamey.training.codes.strings;

import java.util.Stack;

/**
 * 替换特殊的字符串为空格
 * 形如：替换相连字符ab为空格 aaaba -> aaa
 * 替换ab为空格, aaabbb -> ''
 */
public class ReplaceSpecialStrings {

    public static void main(String[] args) {
        System.out.println("aaaba -> " + replaceSpacialString("aaaba", "ab"));
        System.out.println("aaabbb -> " + replaceSpacialString("aaabbb", "ab"));
    }

    private static String replaceSpacialString(String source, String replacement) {
        if (source == null || "".equals(source) || replacement == null || "".equals(replacement)) {
            return source;
        }
        char f = replacement.toCharArray()[0], s = replacement.toCharArray()[1];
        Stack<Character> stack = new Stack<>();
        char[] chars = source.toCharArray();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() == f && chars[i] == s) {
                    stack.pop();
                } else {
                    stack.push(chars[i]);
                }
            } else {
                stack.push(chars[i]);
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        char[] ret = new char[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ret[i] = stack.pop();
        }
        return new String(ret);
    }
}
