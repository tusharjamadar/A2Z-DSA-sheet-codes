package LinkedList.LL;
/*
✅ Problem: Add 1 to a Linked List Number

✅ Problem Details:
You are given a singly linked list where each node contains a single digit. The entire list represents a number.
You need to add 1 to this number and return the new head of the modified list.

Example:
Input:  4 -> 5 -> 6
Output: 4 -> 5 -> 7

✅ Intuition:
We want to add 1 to the number represented by the linked list. The easiest way to do this is:
- Reverse the list to process from least significant digit.
- Add 1 and manage carry.
- Reverse the list back.

✅ Algorithm:
1. Reverse the linked list.
2. Traverse through the list while:
   - Adding carry to the current node.
   - If result < 10, set carry to 0 and stop.
   - Else, set node value to 0 and continue with carry = 1.
3. If carry is still 1 after traversal, add a new node with value 1 at the head.
4. Reverse the list again and return the head.

✅ Time Complexity: O(n)
✅ Space Complexity: O(1)
*/

class Add1ToLL {
    public class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }

    }

    public Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node newhead = reverse(head.next);
        Node front = head.next;
        front.next = head;
        head.next = null;
        return newhead;
    }

    public Node addOne(Node head) {
        head = reverse(head);
        Node temp = head;
        int carry = 1;

        while (temp != null) {
            temp.data += carry;
            if (temp.data < 10) {
                carry = 0;
                break;
            } else {
                temp.data = 0;
                carry = 1;
            }
            if (temp.next == null)
                break;
            temp = temp.next;
        }

        if (carry == 1) {
            if (temp != null && temp.next == null && temp.data == 0) {
                temp.next = new Node(1);
            } else {
                Node newNode = new Node(1);
                temp.next = newNode;
            }
        }

        return reverse(head);
    }
}
