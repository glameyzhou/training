package org.glamey.training.codes.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.glamey.training.codes.Utils;

/**
 * 最长连续的数字字符串
 *
 * @author zhouyang.zhou. 2017.10.10.14.
 */
public class ContinueString {
    public static void main(String[] args) {
        /*List<String> list = Lists.newArrayList("ab1235cd56", "ab1235cd56e", "we1we", "12456");
        for (String s : list) {
            Pair continueNumbers = getContinueNumbers(s);
            System.out.printf("%s --> %s\n", s, continueNumbers);
        }*/
        System.out.println(longestContinueNumber("ab1235cd56"));
        System.out.println(longestContinueNumber("ab1235"));
        System.out.println(longestContinueNumber("a12b34c123f"));
    }

    public static String longestContinueNumber(String source) {
        if (Utils.isBlank(source)) {
            return source;
        }
        char[] chars = source.toCharArray();
        int start = 0, maxLen = 0, lastMaxLen = 0;
        String maxString = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                start = start == 0 ? i : start;
                maxLen = maxLen == 0 ? 1 : maxLen + 1;
            } else {
                if (start > 0 || maxLen > 0) {
                    //长度相同的话，直接覆盖处理
                    if (maxLen >= lastMaxLen) {
                        maxString = new String(chars, start, maxLen);
                        start = 0;
                        lastMaxLen = maxLen;
                        maxLen = 0;
                    }
                }
            }
        }
        return start >= 0 && maxLen >= 0 && maxLen >= lastMaxLen ? new String(chars, start, maxLen) : maxString;
    }


    private static Pair getContinueNumbers(String source) {
        if (source == null || "".equals(source)) {
            return new Pair(source, 0);
        }
        Pair pair = new Pair(null, 0);
        char[] chars = source.toCharArray();
        int len = chars.length;
        int maxCount = 0;
        StringBuilder builder = new StringBuilder(source.length());
        char c;
        char last = '0';
        for (int i = 0; i < len; i++) {
            last = c = chars[i];
            if (Character.isDigit(c)) {
                builder.append(c);
                maxCount += 1;
            } else if (maxCount > 0) {
                if (pair.getCount() == 0 || pair.getCount() <= maxCount) {
                    pair = new Pair(builder.toString(), maxCount);
                    builder.setLength(0);
                    maxCount = 0;
                }
            }
        }
        //处理最后一位(如果非数字的话，上层已经处理过，如果是数组，需要做比较)
        if (Character.isDigit(last) && maxCount > 0) {
            if (pair.getCount() == 0 || pair.getCount() <= maxCount) {
                pair = new Pair(builder.toString(), maxCount);
            }
        }
        return pair;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    static class Pair {
        private String val;
        private int count;
    }
}
