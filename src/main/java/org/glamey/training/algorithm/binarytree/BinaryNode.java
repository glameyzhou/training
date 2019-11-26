package org.glamey.training.algorithm.binarytree;

/**
 * 二叉树对象定义
 *
 * @author yang.zhou 2019.11.25.18
 */
public class BinaryNode<T> {
    public T element;
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T element) {
        this(element, null, null);
    }
}
