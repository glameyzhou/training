package org.glamey.training.demo.easy;

/**
 * @author yang.zhou 2020.01.31.22
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] chars = "HelloWorld".toCharArray();
        reverseString(chars);
        System.out.println(chars);
    }


    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}
