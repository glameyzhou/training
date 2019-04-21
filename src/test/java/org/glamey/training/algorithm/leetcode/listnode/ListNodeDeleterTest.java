package org.glamey.training.algorithm.leetcode.listnode;

import org.junit.Test;

import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.create;
import static org.glamey.training.algorithm.leetcode.listnode.ListNodeUtil.print;

public class ListNodeDeleterTest {

    @Test
    public void delete() {
        for (int i = 1; i <= 11; i++) {
            System.out.println("-----------------删除[" + i + "]----------------------------");
            int[] arrs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ListNode listNode = create(arrs);
            ListNode delete = ListNodeDeleter.delete(listNode, i);
            print(delete);
        }
    }
}