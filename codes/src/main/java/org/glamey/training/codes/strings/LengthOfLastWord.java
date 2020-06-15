package org.glamey.training.codes.strings;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 * 通过次数94,907提交次数285,361
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(byLoop("Hello World"));
        System.out.println(byLoop("Hello World "));
        System.out.println(byPointer("Hello World"));
        System.out.println(byPointer("Hello World "));
    }


    public static int byPointer(String source) {
        if (source == null || "".equals(source)) {
            return 0;
        }
        int tail = source.length() - 1;
        while (tail >= 0 && source.charAt(tail) == ' ') {
            tail--;
        }
        int head = tail;
        while (head >= 0 && source.charAt(head) != ' ') {
            head--;
        }
        return tail - head;
    }

    public static int byLoop(String source) {
        if (source == null || "".equals(source)) {
            return 0;
        }

        int count = 0, lastCount = count;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == ' ') {
                lastCount = count == 0 ? lastCount : count;
                count = 0;
                continue;
            }
            count++;
        }
        return count == 0 ? lastCount : count;
    }


}
