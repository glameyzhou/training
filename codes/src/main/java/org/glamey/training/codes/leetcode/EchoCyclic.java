package org.glamey.training.codes.leetcode;

import org.glamey.training.codes.Utils;

public class EchoCyclic {

    public static void main(String[] args) {
        System.out.println(isEchoCyclic("A man, a plan, a canal: Panama"));
        System.out.println(isEchoCyclic("race a car"));
        System.out.println(isEchoCyclic("  A "));
    }

    private static boolean isEchoCyclic(String source) {
        if (Utils.isBlank(source)) return true;
        source = source.toLowerCase();
        int left = 0, right = source.length() - 1;
        while (left < right) {
            while (source.charAt(left) > 'z' || source.charAt(left) < 'a') {
                left++;
            }
            while (source.charAt(right) > 'z' || source.charAt(right) < 'a') {
                right--;
            }
            if (left == right) return true;
            if (left > right) return false;
            if (source.charAt(left) != source.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
