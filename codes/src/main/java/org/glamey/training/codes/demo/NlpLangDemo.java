package org.glamey.training.codes.demo;

import org.nlpcn.commons.lang.pinyin.Pinyin;

import java.util.List;

public class PinyinDemo {

    /**
     * 将汉字转换为拼音
     * @param chinese 中文字符串
     * @return 拼音字符串，每个字之间用空格分隔
     */
    public static String convertToPinyin(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return "";
        }

        // 转换为拼音数组
        List<String> pinyinArray = Pinyin.pinyin(chinese);

        // 将拼音数组拼接为字符串
        StringBuilder result = new StringBuilder();
        for (String pinyin : pinyinArray) {
            if (pinyin != null && !pinyin.isEmpty()) {
                result.append(pinyin).append(" ");
            }
        }

        return result.toString().trim();
    }

    /**
     * 将汉字转换为带声调的拼音
     * @param chinese 中文字符串
     * @return 带声调的拼音字符串，每个字之间用空格分隔
     */
    public static String convertToPinyinWithTone(String chinese) {
        if (chinese == null || chinese.trim().isEmpty()) {
            return "";
        }

        // 转换为带声调的拼音数组
        List<String> pinyinArray = Pinyin.unicodePinyin(chinese);

        // 将拼音数组拼接为字符串
        StringBuilder result = new StringBuilder();
        for (String pinyin : pinyinArray) {
            if (pinyin != null && !pinyin.isEmpty()) {
                result.append(pinyin).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        String chineseText = "亚朵成都酒店";

        System.out.println("不带声调:");
        System.out.println(convertToPinyin(chineseText)); // 输出: ni hao ， shi jie ！

        System.out.println("\n带声调:");
        System.out.println(convertToPinyinWithTone(chineseText)); // 输出: nǐ hǎo ， shì jiè ！
    }
}
