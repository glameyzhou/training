package org.glamey.training.codes.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 *
 * 二叉树的层平均值
 * 返回一个列表，每个值代表当前行的平均值,值的类型记得标记为Double类型。
 */
public class AverageOfLevels {

    public static void main(String[] args) {

    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue<TreeNode> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null) {
                    temp.add(n.left);
                }
                if (n.right != null) {
                    temp.add(n.right);
                }
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }
}
