package org.glamey.training.codes.strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhouyang281
 * @date 2024-03-08
 */
public class StringMultiply {

    public static void main(String[] args) {
        //97047
        String a = "123";
        String b = "789";
        //2367 1578 789

        String multiply = multiply(a, b);
        System.out.println(multiply);
    }

    private static String multiply(String a, String b) {
        if (a == "0" || b == "0") {
            return "0";
        }
        List<LinkedList<Integer>> retList = new ArrayList<>();
        char[] char_a = a.toCharArray(), char_b = b.toCharArray();
        int m = char_a.length, n = char_b.length;
        for (int i = m - 1; i >= 0; i--) {
            LinkedList<Integer> subRetList = new LinkedList<>();
            //先把后面的0补上,0的个数是
            int lastZeroCount = char_a.length - i - 1;
            fillLastZero(subRetList, lastZeroCount);
            int over = 0, multiply;
            for (int j = n - 1; j >= 0; j--) {
                int v1 = char_a[i] - '0';
                int v2 = char_b[j] - '0';
                multiply = v1 * v2 + over;
                subRetList.offerFirst(multiply % 10);
                over = multiply / 10;
            }
            if (over > 0) {
                subRetList.offerFirst(over);
            }
            retList.add(subRetList);
        }
        String ret = sum(retList);
        return ret;
    }

    private static String sum(List<LinkedList<Integer>> retList) {
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;
        int over = 0, sum = 0;
        for (LinkedList<Integer> list : retList) {
            if (flag) {
                break;
            }
            int val = 0;
            Integer lastOne = list.peekLast();
            if (lastOne == null) {
                val = 0;
                flag = flag && true;
            }
            sum += val + over;
            stack.push(sum % 10);
            over = sum / 10;
        }
        if (over > 0) {
            stack.push(over);
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private static void fillLastZero(LinkedList<Integer> subRet, int lastZeroCount) {
        if (lastZeroCount > 0) {
            for (int i = 0; i < lastZeroCount; i++) {
                subRet.add(0);
            }
        }
    }
}
