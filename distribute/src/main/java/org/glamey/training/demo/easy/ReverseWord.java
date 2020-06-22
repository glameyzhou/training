package org.glamey.training.demo.easy;

/**
 * 按照单词反转
 * <p>
 * I am living in china. --> china. in living am I
 */
public class ReverseWord {

    public static void main(String[] args) {
        String source = "";
        String result = reverseWord(source);
        System.out.println(result);
    }

    /**
     * 1、字符串按照字符全部反转一遍。
     * 2、按照空格，反转每一个单词
     *
     * @param source
     * @return
     */
    private static String reverseWord(String source) {
        if (source == null || "".equals(source)) {
            return source;
        }

        String string = reverseAll(source);


        return null;
    }

    private static String reverseAll(String source) {
        return null;
    }

}
