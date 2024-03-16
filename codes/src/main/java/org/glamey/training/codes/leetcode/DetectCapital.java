package org.glamey.training.codes.leetcode;

public class DetectCapital {

    public static void main(String[] args) {
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("leetcode"));
        System.out.println(detectCapitalUse("Google"));
        System.out.println(detectCapitalUse("flaG"));




    }

    public static boolean detectCapitalUse(String word) {
        if (word == null || "".equals(word)) {
            return false;
        }
        char[] chars = word.toCharArray();
        if (chars.length == 1) {
            return true;
        }
        int right = chars.length - 1;
        boolean lastLowerCase = Character.isLowerCase(chars[right]);
        //大写
        if (!lastLowerCase) {
            for (int i = 0; i < chars.length - 1; i++) {
                if (Character.isLowerCase(chars[i])) {
                    return false;
                }
            }
        }
        //小写
        else {
            boolean firstUpperCase = Character.isUpperCase(chars[0]);
            if (chars.length == 2) {
                return true;
            }
            for (int i = 1; i < chars.length - 1; i++) {
                if (Character.isUpperCase(chars[i])) {
                    return false;
                }
            }

        }
        return true;
    }
}
