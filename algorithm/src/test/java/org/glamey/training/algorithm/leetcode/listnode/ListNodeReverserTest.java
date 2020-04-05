package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.Test;

import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.create;
import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.print;

public class ListNodeReverserTest {

    @Test
    public void reverse() {
        int[] arrs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ListNode listNode = create(arrs);
        print(listNode);
        System.out.println("---------------------------");

        ListNode reverse = ListNodeReverser.reverse(listNode);
        print(reverse);
        System.out.println("---------------------------");
    }
}