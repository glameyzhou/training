package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListNodeMergerTest {
    ListNode n1, n2, n3;

    @Before
    public void before() {
        n1 = ListNodeUtil.create(new int[]{1, 3, 5, 7});
        n2 = ListNodeUtil.create(new int[]{2, 4});
        n3 = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5, 7});
    }

    @After
    public void after() {
        n1 = n2 = n3 = null;
    }

    @Test
    public void testMerge() {
        ListNode mergeListNode = ListNodeMerger.merge(n1, n2);
        ListNodeUtil.print(mergeListNode);
    }

    @Test
    public void testMergeByLoopV2() {
        ListNode listNode = ListNodeMerger.mergeByLoopV2(n1, n2);
        ListNodeUtil.print(listNode);
    }


    @Test
    public void mergeByRecursive() {
        ListNode merge = ListNodeMerger.mergeByRecursive(n1, n2);
        ListNodeUtil.print(merge);
        Assert.assertTrue(ListNodeUtil.eq(merge, n3));
    }

    @Test
    public void mergeByLoop() {
        ListNode merge = ListNodeMerger.mergeByLoop(n1, n2);
        ListNodeUtil.print(merge);
        Assert.assertTrue(ListNodeUtil.eq(merge, n3));
    }
}