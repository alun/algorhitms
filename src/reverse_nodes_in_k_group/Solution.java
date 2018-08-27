package reverse_nodes_in_k_group;

import util.ListNode;

public class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k < 2) {
      return head;
    }

    ListNode cur = head;
    ListNode res = new ListNode(0);
    ListNode last = res;
    ListNode nextLast = cur;
    ListNode reversed = null;
    int i = 0;

    while (cur != null) {
      ListNode next = cur.next;
      cur.next = reversed;
      reversed = cur;

      cur = next;
      if ((++i) % k == 0) {
        last.next = reversed;
        last = nextLast;
        i = 0;
        nextLast = cur;
        reversed = null;
      }
    }
    if (i > 0) {
      last.next = reverseKGroup(reversed, i);
    }

    return res.next;
  }
}

