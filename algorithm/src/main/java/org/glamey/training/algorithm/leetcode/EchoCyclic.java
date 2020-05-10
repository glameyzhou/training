package org.glamey.training.algorithm.leetcode;

public class EchoCyclic {

    public static void main(String[] args) {
        System.out.println(isEchoCyclic("A man, a plan, a canal: Panama"));
        System.out.println(isEchoCyclic("race a car"));
    }

    private static boolean isEchoCyclic(String source) {
        if (source == null || "".equals(source)) {
            return true;
        }

        source = source.toLowerCase();
        int left = 0, right = source.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(source.charAt(left))) {
                left ++;
                continue;
            }
            if (!Character.isLetterOrDigit(source.charAt(right))) {
                right --;
                continue;
            }
            if (source.charAt(left) != source.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}
