/**
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
**/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 /**
 这道题让我们以每k个为一组来翻转链表，实际上是把原链表分成若干小段，然后分别对其进行翻转，那么肯定总共需要两个函数，
 
 一个是用来分段的，一个是用来翻转的，以题目中给的例子来看，对于给定链表 1->2->3->4->5，一般在处理链表问题时，
 
 大多时候都会在开头再加一个 dummy node，因为翻转链表时头结点可能会变化，为了记录当前最新的头结点的位置而引入的 dummy node，
 
 加入 dummy node 后的链表变为 -1->1->2->3->4->5，如果k为3的话，目标是将 1,2,3 翻转一下，那么需要一些指针，
 
 pre 和 next 分别指向要翻转的链表的前后的位置，然后翻转后 pre 的位置更新到如下新的位置：

以此类推，只要 cur 走过k个节点，那么 next 就是 cur->next，就可以调用翻转函数来进行局部翻转了，

注意翻转之后新的 cur 和 pre 的位置都不同了，那么翻转之后，cur 应该更新为 pre->next，而如果不需要翻转的话，cur 更新为 cur->next，代码如下所示：
**/

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = head;
        dummy.next = head;
        
        for (int i = 1; cur != null; i++) {
            if (i % k == 0) {
                pre = reverseOneGroup(pre, cur.next);
                cur = pre.next;
            }else {
                cur = cur.next;
            }
        }
        
        return dummy.next;
    }
    
    private ListNode reverseOneGroup(ListNode pre, ListNode next) {
        ListNode last = pre.next;
        ListNode cur = last.next;
        while (cur != next) {
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        
        return last;
    }
}
