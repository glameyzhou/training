package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * 查看表达式中的括号是否匹配
 *
 * @author zhouyang
 * @date 2019.03.13.17.
 */
public class Bracket {


    public static void main(String[] args) {

        List<String> list = Lists.newArrayList("(((a+b)))", "(((a+b))");
        for (String s : list) {
            System.out.println(s + " " + isBracketMatch(s));
        }
    }


    public static boolean isBracketMatch(String expression) {
        Stack<Object> stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for (char aChar : chars) {

            if (isLeftBracket(aChar)) {
                stack.push(aChar);
            }

            if (isRightBracket(aChar)) {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static boolean isLeftBracket(char source) {
        return '(' == source;
    }

    public static boolean isRightBracket(char source) {
        return ')' == source;
    }
}
