package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListNodeFinderTest {


    ListNode node;

    @Before
    public void before() {
        node = ListNodeUtil.create(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @After
    public void after() {
        node = null;
    }


    @Test
    public void findByIndex() {
        ListNode byIndex = ListNodeFinder.findByIndex(node, 3);
        ListNodeUtil.print(byIndex);

    }

    @Test
    public void findByReverseIndex() {
        ListNode byReverseIndex = ListNodeFinder.findByReverseIndex(node, 4);
        ListNodeUtil.print(byReverseIndex);
    }
}