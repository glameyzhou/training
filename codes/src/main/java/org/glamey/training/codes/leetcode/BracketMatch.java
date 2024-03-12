package org.glamey.training.codes.leetcode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/valid-parentheses/description/
 * <p>
 * 检查字符串的中括号是否对称
 *
 * @author zhouyang281
 * @date 2024-03-12
 */
public class BracketMatch {
    public static void main(String[] args) {
        String source = "[((({})))]";
        System.out.println(isValid(source.toCharArray()));
    }

    public static boolean isValid(char[] chars) {
        if (chars == null || chars.length == 0 || chars.length % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((top == '(' && c != ')') || (top == '[' && c != ']') || (top == '{' && c != '}')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
