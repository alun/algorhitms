package remove_nth_node_from_end_of_list;

import util.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode head_2 = new ListNode(0);
        head_2.next = head;
        ListNode j = head_2, i = head_2;
        while (n-- > 0 && j != null) j = j.next;
        while (j != null) {
            j = j.next;
            if (j == null) i.next = i.next.next;
            else i = i.next;
        }
        return head_2.next;
    }
}



