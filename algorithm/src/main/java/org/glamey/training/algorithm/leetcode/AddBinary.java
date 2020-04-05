package org.glamey.training.algorithm.leetcode;

import org.springframework.util.Assert;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.06.18
 */
public class AddBinary {

    public static void main(String[] args) {
        Assert.isTrue(new AddBinary().addBinary("11", "1").equals("100"));
        Assert.isTrue(new AddBinary().addBinary("1010", "1011").equals("10101"));
    }

    public String addBinary(String a, String b) {
        if (a == null || "".equals(a))
            return b;

        if (b == null || "".equals(b))
            return a;

        StringBuffer buffer = new StringBuffer();
        int overVal = 0, sum = overVal;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            sum += overVal;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            buffer.append(sum % 2);
            overVal = sum / 2;
            sum = 0;
        }
        buffer.append(overVal == 1 ? "1" : "");
        return buffer.reverse().toString();
    }
}
