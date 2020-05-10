package org.glamey.training.algorithm.offers.domain;

public class Tree {
    int val;
    Tree left;
    Tree right;

    public Tree(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
