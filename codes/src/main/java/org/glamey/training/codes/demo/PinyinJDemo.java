package org.glamey.training.codes.demo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinJDemo {

    // 基础转换（带数字声调，如 "zhong1"）
    public static String toPinyin(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 数字声调

        StringBuilder result = new StringBuilder();
        for (char c : chinese.toCharArray()) {
            try {
                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyins != null && pinyins.length > 0) {
                    result.append(pinyins[0]).append(" "); // 取第一个读音
                } else {
                    result.append(c).append(" ");  // 非汉字保留原字符
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String chineseText = "亚朵成都酒店，不是重庆酒店，是不是都想错了";
        System.out.println(toPinyin(chineseText));
    }
}
