package org.glamey.training.codes.tree;

import java.util.*;

/**
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * 输入:
 * <p>
 * 5
 * /  \
 * 2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 * <p>
 * 示例 2：
 * 输入：
 * 5
 * /  \
 * 2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 * <p>
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-frequent-subtree-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MostFrequentSubtreeSum {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-5);

        int[] ret = findMostFrequentSubtreeSum(root);
        System.out.println(Arrays.toString(ret));
    }

    private static int max = 0;

    public static int[] findMostFrequentSubtreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        //key=sum, value=count
        Map<Integer, Integer> map = new HashMap<>();
        mostFrequentProcessor(root, map);
        List<Map.Entry<Integer, Integer>> countList = new ArrayList<>(map.entrySet());
//        countList.sort((a, b) -> b.getValue() - a.getValue());
        Collections.sort(countList, (o1, o2) -> o2.getValue() - o1.getValue());
        return countList.stream().filter(e -> e.getValue() == max).mapToInt(Map.Entry::getKey).toArray();
    }

    private static int mostFrequentProcessor(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int curSum = root.val + mostFrequentProcessor(root.left, map) + mostFrequentProcessor(root.right, map);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        max = Math.max(max, curSum);
        return curSum;
    }


}
