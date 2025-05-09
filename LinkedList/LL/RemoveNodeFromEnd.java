package LinkedList.LL;

/*
✅ Problem: 19. Remove Nth Node From End of List

✅ Problem Details:
Given the head of a singly linked list, remove the nth node from the end and return the modified list.

✅ Intuition:
Use two pointers – fast and slow.
Move fast n steps ahead, then move both fast and slow one step at a time.
When fast reaches the end, slow will be just before the node to remove.

✅ Algorithm:
1. Move `fast` n steps ahead.
2. If `fast` becomes null, the node to remove is the head. Return `head.next`.
3. Move both `fast` and `slow` until `fast.next` is null.
4. Remove the node by `slow.next = slow.next.next`.
5. Return the head.

✅ Time & Space Complexity:
Time: O(n) – traverse the list once.
Space: O(1) – constant space used.
*/

class RemoveNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;
        int cnt = 0;

        // Move fast pointer n steps ahead
        while (cnt != n && fast != null) {
            cnt++;
            fast = fast.next;
        }

        // If fast reached null, we need to remove the head node
        if (fast == null) return head.next;

        // Move both pointers until fast reaches the end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return head;
    }
}
