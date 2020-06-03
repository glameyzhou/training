package org.glamey.training.codes.leetcode;

import org.glamey.training.codes.Utils;

/**
 * 替换空格字符串为 %20
 */
public class ReplaceBlankString {

    public static void main(String[] args) {
        String source = " a b c ";
        System.out.println(byBeginToEnd(source));
        System.out.println(byEndToBegin(source));
    }

    public static String byEndToBegin(String source) {
        if (Utils.isBlank(source)) {
            return source;
        }
        char[] originChars = source.toCharArray();
        int blankCount = 0;
        for (int i = 0; i < originChars.length ; i++) {
            blankCount += originChars[i] == ' ' ? 1 : 0;
        }
        int retLen = originChars.length + blankCount * 2;
        char[] retChars = new char[retLen];
        System.arraycopy(originChars, 0, retChars, 0, originChars.length);
        int originPointer = originChars.length - 1, retPointer = retLen - 1;
        while (originPointer >= 0) {
            if (retChars[originPointer] == ' ') {
                retChars[retPointer --] = '0';
                retChars[retPointer --] = '2';
                retChars[retPointer --] = '%';
            } else {
                retChars[retPointer --] = originChars[originPointer];
            }
            originPointer --;
        }
        return new String(retChars);
    }

    /**
     * 时间复杂度O(N)
     * @param source
     * @return
     */
    public static String byBeginToEnd(String source) {
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
