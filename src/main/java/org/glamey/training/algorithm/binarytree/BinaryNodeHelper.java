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
}
