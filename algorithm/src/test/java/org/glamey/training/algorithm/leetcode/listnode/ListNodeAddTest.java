package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListNodeAddTest {


    ListNode n1, n2;

    @Before
    public void init() {
        int[] array1 = {1, 2, 8, 3};
        n1 = ListNodeUtil.create(array1);

        int[] array2 = {8, 1, 7};
        n2 = ListNodeUtil.create(array2);
    }

    @After
    public void destory() {
        n1 = null;
        n2 = null;

        System.out.println("------------");
    }


    @Test
    public void addToNode() {
        ListNode node = ListNodeAdd.addTwoNodeByStack(n1, n2);
        ListNodeUtil.print(node);
    }

    @Test
    public void addToNodeByReverse() {
        ListNode node = ListNodeAdd.addTwoNodeByReverse(n1, n2);
        ListNodeUtil.print(node);
    }
}