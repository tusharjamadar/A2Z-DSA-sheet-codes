package LinkedList.LL;
/*
✅ Problem: 160. Intersection of Two Linked Lists

✅ Problem Details:
Given the heads of two singly linked lists, return the node where they intersect, or null if they don't.

✅ Intuition:
If two linked lists intersect, then the nodes after the intersection point are shared.
To find the intersection, we can use two pointers starting at the heads of the two lists.
Switch them to the head of the other list when they reach the end. They will meet at the intersection node.

✅ Algorithm:
1. Initialize two pointers, t1 and t2, at headA and headB respectively.
2. Traverse the lists:
   - Move t1 and t2 forward by one node each step.
   - When one pointer reaches the end, switch it to the other list's head.
   - Continue until they either meet or both become null.
3. Return the meeting point.

✅ Time & Space Complexity:
Time: O(m + n), where m and n are the lengths of the two lists.
Space: O(1), constant space used.
*/

public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA, t2 = headB;

        while (t1 != t2) {
            t1 = (t1 != null) ? t1.next : headB;
            t2 = (t2 != null) ? t2.next : headA;
        }

        return t1;
    }
}
