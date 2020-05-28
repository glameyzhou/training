package org.glamey.training.codes.listnode;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * <p>
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * <p>
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * <p>
 * 如果不得使用临时缓冲区，该怎么解决？
 * <p>
 * 通过次数5,090提交次数7,714
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicateNode {
    public static void main(String[] args) {
        ListNode node = ListNodeUtil.create(new int[]{1});
        ListNode remove = removeDuplicateNodes(node);
        ListNodeUtil.print(remove);

    }

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode cur = head;
        Set<Integer> set = new HashSet<>();
        while (cur != null) {
            if (!set.contains(cur.val)) {
                set.add(cur.val);
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
