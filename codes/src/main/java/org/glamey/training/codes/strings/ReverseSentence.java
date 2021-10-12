package org.glamey.training.codes.strings;

/**
 * https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&&tqId=11197&rp=1&ru=/activity/oj&qru
 * =/ta/coding-interviews/question-ranking
 * <p>
 * <p>
 * 反转整个语句，形如：
 * student. a am I
 * I am a student.
 */
public class ReverseSentence {

    public static void main(String[] args) {
        System.out.println(reverseSentence("student. a am I"));
        System.out.println(reverseSentence("  student. a am I "));
    }

    public static String reverseSentence(String source) {
        if (source == null || source.equals("")) {
            return source;
        }
        char[] chars = source.toCharArray();
        reverse(chars, 0, chars.length - 1);
        int blank = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                int nextBlank = i;
                reverse(chars, blank + 1, nextBlank - 1);
                blank = nextBlank;
            }
        }
        //最后一个
        reverse(chars, blank + 1, chars.length - 1);
        return new String(chars);
    }

    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
            left++;
            right--;
        }
    }
}
