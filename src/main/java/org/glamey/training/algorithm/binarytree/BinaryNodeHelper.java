package org.glamey.training.algorithm.binarytree;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yang.zhou 2019.11.26.10
 */
public class BinaryNodeHelper {

    public static BinaryNode<Integer> build(List<Integer> array) {
        return new BinarySearchTree<>(array).getRoot();
    }

    public static BinaryNode<Integer> build() {
        return build(Lists.newArrayList(10, 6, 12, 2, 7, 11, 14));
    }


    public static void main(String[] args) {
        BinaryNode<Integer> build = BinaryNodeHelper.build();
        System.out.println(build);
//        boolean success = isBST(build);
//        System.out.println("is BST ->  " + success);

        boolean success = isBST_(build);
        System.out.println("is BST ->  " + success);;
    }

    private static boolean isBST_(BinaryNode<Integer> build) {
        return isBST__(build);
    }

    private static boolean firstCompare = false;
    private static int preVal = Integer.MIN_VALUE;
    private static boolean isBST__(BinaryNode<Integer> build) {
        if (build == null) {
            return true;
        }
        return isBST__(build.left)
                && compare(build.element)
                && isBST__(build.right);
    }

    private static boolean compare(Integer element) {
        if (firstCompare) {
            firstCompare = false;
            preVal = element;
            return true;
        }

        if (element < preVal) {
            return false;
        }
        preVal = element;
        return true;
    }

    private static boolean isBST(BinaryNode<Integer> build) {
        List<Integer> values = Lists.newArrayList();
        _isBST(build, values);
        System.out.println(values);
        for (int i = 0; i < values.size() - 2; i++) {
            if (values.get(i + 1) <= values.get(i)) {
                return false;
            }
        }
        return true;
    }

    private static void _isBST(BinaryNode<Integer> build, List<Integer> values) {
        if (build == null) {
            return;
        }

        _isBST(build.left, values);
        values.add(build.element);
        _isBST(build.right, values);
    }
}
