package LinkedList.LL;
/*
✅ Problem: Reverse Nodes in k-Group (Leetcode 25)

✅ Problem Details:
Given the head of a linked list, reverse the nodes in groups of size k.
If the number of nodes is not a multiple of k, leave the remaining nodes as they are.

You must reverse node *pointers*, not just their values.

✅ Intuition:
To reverse nodes in k-groups:
1. For each k-group, identify the kth node.
2. Reverse the sublist between the current node and the kth node.
3. Connect the reversed group with the previous part and continue.

✅ Algorithm:
1. Start from the head of the list.
2. For each k-group:
   a. Use a helper `getKthNode` to find the kth node from the current position.
   b. If there are fewer than k nodes left, stop.
   c. Temporarily cut the group at kth node's next.
   d. Reverse the group using helper `reverseLL`.
   e. Connect the previous group’s tail to the new head (kth node).
   f. Update pointers for the next iteration.
3. Return the new head after processing all groups.

✅ Time Complexity: O(n), each node is visited once.
✅ Space Complexity: O(1), in-place reversal using constant space.
*/

class ReverseNodeskGroup {
    public static ListNode reverseLL(ListNode head) {
        ListNode temp = head, prev = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }

    public static ListNode getKthNode(ListNode temp, int k) {
        k -= 1;
        while (temp != null && k > 0) {
            temp = temp.next;
            k--;
        }
        return temp;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prevLast = null;

        while (temp != null) {
            ListNode kthNode = getKthNode(temp, k);
            // if we don't want to reverse remaining nodes less than k
            if (kthNode == null) {
                if (prevLast != null) {
                    prevLast.next = temp;
                }
                break;
            }

            // if we want to reverse remaining nodes which are less than k
            // if(kthNode == null){
            //     ListNode revHead = reverseLL(temp);
                
            //     if(prevLast != null){
            //         prevLast.next = revHead;
            //     }else{
            //         head = revHead;
            //     }
            //     break;
            // }

            ListNode nextNode = kthNode.next;
            kthNode.next = null;

            reverseLL(temp);

            if (temp == head) {
                head = kthNode;
            } else {
                prevLast.next = kthNode;
            }

            prevLast = temp;
            temp = nextNode;
        }

        return head;
    }
}

