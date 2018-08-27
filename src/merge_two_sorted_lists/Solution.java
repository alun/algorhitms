package merge_two_sorted_lists;

import util.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0),
                last = result;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                last.next = l2;
                break;
            }
            if (l2 == null) {
                last.next = l1;
                break;
            }
            if (l1.val < l2.val) {
                last.next = l1;
                last = l1;
                l1 = l1.next;
            } else {
                last.next = l2;
                last = l2;
                l2 = l2.next;
            }
        }
        return result.next;
    }
}
