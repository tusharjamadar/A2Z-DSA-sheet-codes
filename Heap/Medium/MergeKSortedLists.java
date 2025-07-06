import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class MergeKSortedLists {

    /**
     * Problem: Merge k sorted linked lists into one sorted list.
     * 
     * Intuition:
     * Use a Min Heap (PriorityQueue) to always fetch the smallest node.
     * 
     * Time Complexity: O(N log k), where N is total number of nodes across all lists,
     * and k is the number of lists. Heap operations are log k, and we do N such operations.
     * 
     * Space Complexity: O(k) for the priority queue (at most k nodes inside).
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-heap to store the smallest nodes
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add initial head of each list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(-1);  // dummy node to start result list
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();  // get node with smallest value
            curr.next = minNode;
            curr = curr.next;

            if (minNode.next != null) {
                pq.offer(minNode.next);  // push next node of the extracted list
            }
        }

        return dummy.next;
    }

    // Helper function to create linked list from array
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(-1), tail = dummy;
        for (int val : arr) {
            tail.next = new ListNode(val);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Helper function to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }

    // Main function to test
    public static void main(String[] args) {
        MergeKSortedLists sol = new MergeKSortedLists();

        ListNode[] lists = new ListNode[]{
            buildList(new int[]{1, 4, 5}),
            buildList(new int[]{1, 3, 4}),
            buildList(new int[]{2, 6})
        };

        ListNode result = sol.mergeKLists(lists);
        System.out.print("Merged List: ");
        printList(result);  // Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    }
}
