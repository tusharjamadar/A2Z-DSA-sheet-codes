package LinkedList.LL;

/*
✅ Problem: 234. Palindrome Linked List

✅ Problem Details:
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

✅ Intuition:
We can find the middle of the list, reverse the second half, and compare it with the first half.

✅ Algorithm:
1. Use slow and fast pointers to find the middle of the list.
2. Reverse the second half of the list starting from the node after the middle.
3. Compare the values from start and from the reversed half.
4. If any mismatch, return false. Else, true.

✅ Time & Space Complexity:
Time: O(n) – One pass to find middle, one to reverse, one to compare.
Space: O(1) – In-place reversal, no extra memory.
*/


class Solution {
    
    public class ListNode {
        int val;
        ListNode next;
    
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode slow = head, fast = head;

        // Step 1: Find middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode rev = reverse(slow.next);

        // Step 3: Compare both halves
        ListNode curr = head;
        while (rev != null) {
            if (rev.val != curr.val) return false;
            rev = rev.next;
            curr = curr.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

}
