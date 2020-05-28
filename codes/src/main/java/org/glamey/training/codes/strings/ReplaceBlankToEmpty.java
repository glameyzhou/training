package org.glamey.training.codes.strings;

import org.glamey.training.codes.Utils;

/**
 * 将字符串中的空格替换为%20
 * 形如： we are the world. 替换完毕的结果是 %20we%20are%20the%20world.
 * <p>
 * 1、统计原始字符串中的空格个数。
 * 2、构建结果字符串的char数组，长度是源字符串长度+ 2*空格数量。
 * 3、拷贝原始字符串到结果char数组中。
 * 4、声明两个指针，分别指向ret char原始字符串的尾部和ret char尾部。
 * 5、如果origin index 非空格，retIndex的值等于originIndex的值，同时 retIndex originIndex分别向前移动一位。
 * 6、如果origin index 为空格，retIndex--分别设置'0' '2' '%'
 * 7、构建new String(ret)。
 */
public class ReplaceBlankToEmpty {

    public static void main(String[] args) {
        System.out.println(replaceBlankToEmpty(" we are the world.").equals("%20we%20are%20the%20world."));
    }

    private static String replaceBlankToEmpty(String source) {
        if (Utils.isBlank(source)) {
            return source;
        }
        int blankCount = getBlankCount(source);
        char[] origin = source.toCharArray();
        char[] ret = new char[origin.length + blankCount * 2];
        System.arraycopy(origin, 0, ret, 0, origin.length);
        int originIndex = origin.length - 1, retIndex = ret.length - 1;
        while (originIndex >= 0) {
            if (ret[originIndex] == ' ') {
                ret[retIndex--] = '0';
                ret[retIndex--] = '2';
                ret[retIndex--] = '%';
            } else {
                ret[retIndex--] = origin[originIndex];
            }
            originIndex--;
        }
        return new String(ret);
    }

    private static int getBlankCount(String source) {
        int count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }
}
