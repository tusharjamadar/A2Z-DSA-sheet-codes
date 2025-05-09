package LinkedList.LL;

/*
✅ Problem: 328. Odd Even Linked List

✅ Problem Details:
Rearrange a linked list so that all nodes at odd indices come first, followed by nodes at even indices.
Indices start at 1 (not 0), and the relative order of nodes in both groups must remain unchanged.

✅ Intuition:
We maintain two pointers:
- `odd` for odd-indexed nodes
- `even` for even-indexed nodes

We rearrange the `next` pointers to separate odd and even lists,
then join the even list to the end of the odd list.

✅ Algorithm:
1. Handle edge case where head is null or has only one element.
2. Create pointers: `odd`, `even`, and store `evenHead` to attach later.
3. Rearrange using a loop:
   - Point odd to next odd.
   - Point even to next even.
4. Finally, attach even list to the end of odd list.

✅ Time & Space Complexity:
Time: O(n) – we traverse each node once.
Space: O(1) – no extra space used.
*/

class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;  // Store start of even list to append later

        // Traverse and rearrange nodes
        while (even != null && even.next != null) {
            odd.next = even.next;  // connect current odd to next odd
            odd = odd.next;

            even.next = odd.next;  // connect current even to next even
            even = even.next;
        }

        odd.next = evenHead;  // append even list after odd list
        return head;
    }
}
