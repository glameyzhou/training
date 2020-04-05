package org.glamey.training.algorithm.leetcode;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
        List<String> list = Lists.newArrayList("([)", "{[]}", "()", "(]", "}(");
        for (String s : list) {
            System.out.println(s + " " + isMatch(s) + "  " + isValid(s));
        }

    }


    private static final ImmutableMap<Character, Character> BRACKET_MAPPING = ImmutableMap.of('(', ')', '[', ']', '{', '}');

    /**
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if ((s.length() & 1) != 0) return false;
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (BRACKET_MAPPING.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if (c != BRACKET_MAPPING.get(stack.peek())) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
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
