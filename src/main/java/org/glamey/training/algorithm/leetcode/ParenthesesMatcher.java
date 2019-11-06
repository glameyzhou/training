package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * <p>
 * 查看表达式中的括号是否匹配
 * <p>
 * ASCII  十进制
 * (       40
 * )       41
 * [       91
 * ]       93
 * {       123
 * }       125
 *
 * @author zhouyang
 * @date 2019.03.13.17.
 */
public class ParenthesesMatcher {


    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("([)", "{[]}", "()");
        for (String s : list) {
            System.out.println(s + " " + isMatch(s));
        }
    }


    public static boolean isMatch(String expression) {
        Stack<Character> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for (char c : chars) {
            if (c == 40 || c == 91 || c == 123) {
                stack.push(c);
            } else if (c == 41 || c == 93 || c == 125) {
                if (stack.isEmpty()) {
                    return false;
                }
                char peep = stack.peek().charValue();
                if (peep + 1 != c && peep + 2 != c) {
                    return false;
                }
                stack.pop();
            } else {
                //其他字符不做任何处理
            }
        }
        return stack.isEmpty();
    }
}
