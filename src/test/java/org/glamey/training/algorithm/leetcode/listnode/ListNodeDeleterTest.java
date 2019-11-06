package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.create;
import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.print;

public class ListNodeDeleterTest {
    ListNode node;

    @Before
    public void before() {
        node = ListNodeUtil.create(new int[]{3, 2, 3, 4, 3, 6, 3});
    }

    @Test
    public void after() {
        node = null;
    }

    @Test
    public void deleteByFirstPresent() {
        ListNode deleteFirstPresent = ListNodeDeleter.deleteFirstPresent(node, 3);
        print(deleteFirstPresent);
    }

    @Test
    public void deleteByAllPresent() {
        ListNode deleteFirstPresent = ListNodeDeleter.deleteAllPresent(node, 3);
        print(deleteFirstPresent);
    }

    @Test
    public void testRemoveNthFromEnd() {
        node = ListNodeUtil.create(new int[]{1});

        ListNode delete = ListNodeDeleter.removeNthFromEnd(node, 1);
        print(delete);
    }
}