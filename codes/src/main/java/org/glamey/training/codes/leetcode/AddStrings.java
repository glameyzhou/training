package org.glamey.training.codes.leetcode;


/**
 * done 20240314
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2的长度都小于 5100.
 * num1 和num2 都只包含数字0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库，也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.06.10
 */
public class AddStrings {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "7";
        String add = new AddStrings().addStrings(num1, num2);
        System.out.println(add);
    }

    public String addStrings(String num1, String num2) {
        if ("".equals(num1) || num1 == null) {
            return num2;
        }

        if ("".equals(num2) || num2 == null) {
            return num1;
        }

        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, overFlowVal = 0, v1, v2, sum;
        while (i >= 0 || j >= 0) {
            v1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            v2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            sum = v1 + v2 + overFlowVal;
            builder.append(sum % 10);
            overFlowVal = sum / 10;
            i--;
            j--;
        }

        if (overFlowVal == 1) {
            builder.append(1);
        }

        return builder.reverse().toString();
    }
}
