package org.glamey.training.codes.strings;

/**
 * 字符串中，找到连续的最长的数字字符串
 * <p>
 * https://www.nowcoder.com/practice/bd891093881d4ddf9e56e7cc8416562d?tpId=179&&tqId=34157&rp=1&ru=/activity/oj&qru=/ta/exam-other/question-ranking
 */
public class LongestContinueNumbers {

    public static void main(String[] args) {
        System.out.println(getLongestContinueNumbers("abcd12345ed125ss123456789"));
        System.out.println(getLongestContinueNumbers("abc"));
        System.out.println(getLongestContinueNumbers("123a"));
        System.out.println(getLongestContinueNumbers("a23"));


        System.out.println(analyzerCharNum("dabcab"));
    }


    public static String analyzerCharNum(String source) {
        if (source == null || "".equals(source)) {
            return "";
        }

        int[] nums = new int[26];
        for (int i = 0; i < source.length(); i++) {
            nums[source.charAt(i) - 'a']++;
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                char c = (char) (nums[i] + 'a');
                System.out.println(c);
                buffer.append(c).append(nums[i]);
            }
        }
        return buffer.toString();
    }


    public static String getLongestContinueNumbers(String source) {
        if (source == null || "".equals(source)) {
            return "";
        }
        int maxLen = 0, end = 0, count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) < '0' || source.charAt(i) > '9') {
                count = 0;
                continue;
            }
            count++;
            if (maxLen < count) {
                maxLen = count;
                end = i;
            }
        }
        return maxLen > 0 ? source.substring(end - maxLen + 1, end + 1) : "";
    }
}
