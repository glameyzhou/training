package org.glamey.training.codes.listnode;

import java.util.HashMap;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的 深拷贝。 
 * <p>
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *  
 * <p>
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * <p>
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * <p>
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * 通过次数32,260提交次数61,905
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CopyRandomList {


    static class Node {
        int val;
        Node next;
        Node randomNode;

        Node(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {

    }


    /**
     * 时间复杂度O(N)
     * 空间复杂度O(N) 栈深问题
     */
    private static final HashMap<Node, Node> map = new HashMap<>();

    public static Node copyRandomListByRecursion(Node head) {
        if (head == null) {
            return null;
        }

        if (map.containsKey(head)) {
            return map.get(head);
        }

        Node node = new Node(head.val);
        map.put(head, node);
        node.next = copyRandomListByRecursion(head.next);
        node.randomNode = copyRandomListByRecursion(head.randomNode);
        return node;
    }

    /**
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public static Node copyRandomListByLoop(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;

        while (p != null) {
            map.get(p).next = map.get(p.next);
            map.get(p).randomNode = map.get(p.randomNode);
        }
        return map.get(head);
    }
}
