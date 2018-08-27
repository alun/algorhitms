package add_two_numbers;

import util.ListNode;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryOn = 0;
        ListNode first = new ListNode(0);
        ListNode current = first;
        while (l1 != null || l2 != null || carryOn != 0) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = a + b + carryOn;

            ListNode next = new ListNode(sum % 10);
            current.next = next;
            current = next;

            carryOn = sum / 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return first.next;
    }
}

