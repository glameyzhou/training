package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.Test;

import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.create;
import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.print;

public class ListNodeMergerTest {

    @Test
    public void mergeTwoListNode() {
        int[] arr1 = {1, 2, 3, 5, 7};
        ListNode n1 = create(arr1);
        print(n1);
        System.out.println("--------------------");

        int[] arr2 = {1, 4, 6, 10};
        ListNode n2 = create(arr2);
        print(n2);
        System.out.println("--------------------");

        ListNode listNode = ListNodeMerger.mergeTwoListNode(n1, n2);
        print(listNode);
    }
}