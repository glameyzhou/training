package org.glamey.training.codes.leetcode;

/**
 * 替换空格字符串为 %20
 */
public class ReplaceBlankString {

    public static void main(String[] args) {
        String source = "a b c ";
        System.out.println(new ReplaceBlankString().replaceBlank(source));
        source.replaceAll("", "");
    }


    /**
     * 时间复杂度O(2n)
     * @param source
     * @return
     */
    public String replaceBlank(String source) {
        if (source == null || "".equals(source)) {
            return source;
        }

        int blankCount = 0;
        char[] chars = source.toCharArray();
        for (char c : chars) {
            if (' ' == c) blankCount ++;
        }
        char[] retChars = new char[chars.length + blankCount * 2];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                retChars[index ++] = '%';
                retChars[index ++] = '2';
                retChars[index ++] = '0';
            } else {
                retChars[index ++] = chars[i];
            }
        }
        return new String(retChars);
    }
}
