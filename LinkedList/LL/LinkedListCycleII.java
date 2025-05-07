package LinkedList.LL;

/*
✅ Problem: 142. Linked List Cycle II

✅ Problem Details:
Given the head of a linked list, return the node where the cycle begins. 
If there is no cycle, return null. Do not modify the linked list.

✅ Intuition:
Use Floyd's Tortoise and Hare algorithm. If slow and fast pointers meet, there's a cycle.
To find the start of the cycle, reset one pointer to the head and move both one step at a time until they meet again.

✅ Algorithm:
1. Use two pointers (slow and fast).
2. Detect cycle using slow = slow.next, fast = fast.next.next.
3. Once detected, move slow to head.
4. Move slow and fast one step until they meet.
5. Return meeting point as cycle start.

✅ Time & Space Complexity:
Time: O(n)
Space: O(1)
*/

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Step 2: Reset slow to head
                slow = head;

                // Step 3: Find entry point of cycle
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;  // cycle start
            }
        }

        return null;  // no cycle
    }
}
