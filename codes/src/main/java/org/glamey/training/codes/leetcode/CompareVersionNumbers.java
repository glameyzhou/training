package org.glamey.training.codes.leetcode;

/**
 * done 20240314
 * 比较两个版本号 version1和 version2。
 * 如果version1>version2返回1，如果version1<version2 返回 -1， 除此之外返回 0。
 * 你可以假设版本字符串非空，并且只包含数字和. 字符。
 * . 字符不代表小数点，而是用于分隔数字序列。
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 *
 * <p>
 * 示例1:
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * <p>
 * 示例 2:
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * <p>
 * 示例4：
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
 * <p>
 * 示例 5：
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
 *
 * <p>
 * 提示：
 * 版本字符串由以点（.）分隔的数字字符串组成。这个数字字符串可能有前导零。
 * 版本字符串不以点开始或结束，并且其中不会有两个连续的点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compare-version-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CompareVersionNumbers {

    public static void main(String[] args) {
        int compare = compareVersion("0.1", "1.1");
        System.out.println(compare);
    }


    public static int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0, ret = -1;
        while (i < nums1.length || j < nums2.length) {
            String n1 = i < nums1.length ? nums1[i] : "0";
            String n2 = i < nums2.length ? nums2[i] : "0";
            ret = compare(n1, n2);
            if (ret != 0) {
                return ret;
            }
            i++;
            j++;
        }
        return ret;
    }

    private static int compare(String n1, String n2) {
        n1 = removeStartZero(n1);
        n2 = removeStartZero(n2);
        if (n1.length() > n2.length()) {
            return 1;
        } else if (n1.length() < n2.length()) {
            return -1;
        } else {
            //如果长度一样，一位一位进行比较
            for (int i = 0; i < n1.length(); i++) {
                int compare = n1.charAt(i) - n2.charAt(i);
                if (compare > 0) {
                    return 1;
                } else if (compare < 0) {
                    return -1;
                }
            }
            return 0;
        }
    }

    private static String removeStartZero(String source) {
        int index = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(0) != '0') {
                break;
            }
            index++;
        }
        return source.substring(index);
    }
}
