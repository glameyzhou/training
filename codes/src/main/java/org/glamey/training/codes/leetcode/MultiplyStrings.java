package org.glamey.training.codes.leetcode;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yang.zhou 2020.01.06.18
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(new MultiplyStrings().multiply("2", "3"));
        System.out.println(new MultiplyStrings().multiply("123", "456"));
    }


    public String multiply(String a, String b) {
        if (a == null || "".equals(a)) {
            return b;
        }

        if (b == null || "".equals(b)) {
            return a;
        }

        if (a.startsWith("0") || b.startsWith("0")) {
            return "";
        }


        if (a.equals("0") && b.equals("0")) {
            return "0";
        }

        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }


        //采取的方法是b[index] * a
        String result = "0";
        for (int i = b.length() - 1; i >= 0; i--) {
            //保存a * b[index]
            StringBuffer buffer = new StringBuffer();
            //补〇
            int fillZeroCount = a.length() - 1 - i;
            for (int j = 0; j < fillZeroCount; j++) {
                buffer.append("0");
            }

            int overVal = 0, bVal = b.charAt(i) - '0', sum;
            for (int j = a.length() - 1; j >= 0; j--) {
                sum = (a.charAt(j) - '0') * bVal + overVal;
                buffer.append(sum % 10);
                overVal = sum / 10;
            }
            buffer.append(overVal == 1 ? "1" : "");

            result = addStrings(result, buffer.reverse().toString());

        }

        return result;
    }

    private String addStrings(String a, String b) {
        if (a.equals("0")) {
            return b;
        }

        if (b.equals("0")) {
            return a;
        }

        StringBuffer buffer = new StringBuffer();
        int overVal = 0, sum;
        for (int i = a.length() - 1, j = a.length() - 1; i >= 0 || j >= 0; i--, j--) {
            sum = (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? a.charAt(j) - '0' : 0) + overVal;
            buffer.append(sum % 10);
            overVal = sum / 10;
        }
        buffer.append(overVal == 1 ? "1" : "");
        return buffer.reverse().toString();
    }
}
