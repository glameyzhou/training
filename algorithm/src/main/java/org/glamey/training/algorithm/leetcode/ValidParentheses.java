package org.glamey.training.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yang.zhou 2020.01.09.22
 */
public class ValidParentheses {

    public static int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,1,1,33,3,3};
        System.out.println(majorityElement(nums));
//        System.out.println(new ValidParentheses().isValid("()[]{}"));
//        System.out.println(new ValidParentheses().isValid(""));
//        System.out.println(new ValidParentheses().isValid("{"));
//        System.out.println(new ValidParentheses().isValid("]"));
    }

    public boolean isValid(String s) {
        if (s == null) return false;
        if ("".equals(s)) return true;
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            //left
            if (map.containsKey(c)) stack.push(c);
                //right
            else {
                if (stack.isEmpty()) return false;
                if (map.get(stack.peek()) == c) stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
