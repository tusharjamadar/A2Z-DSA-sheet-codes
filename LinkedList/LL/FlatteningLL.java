package LinkedList.LL;

/*
✅ Problem: Flattening a Linked List

✅ Problem Details:
You are given a linked list where:
- Each node has a `.next` pointer to the next head node
- Each node also has a `.bottom` pointer to a sorted sub-linked list

The goal is to flatten the list into a single-level sorted list using only `.bottom` pointers.

✅ Intuition:
This is a variation of merging multiple sorted linked lists.
Use recursion to reduce the problem:
- Flatten the rest of the list (i.e., from root.next onward)
- Merge the current root list with the flattened rest using a bottom-wise merge (similar to merge in merge sort)

✅ Algorithm:
1. Recursively flatten from the rightmost list towards the left.
2. At each step, merge the current node’s list (via `.bottom`) with the already flattened list.
3. Use a helper `mergeList` function to merge two bottom-linked sorted lists.

✅ Time Complexity: O(N * M)
Where:
- N = number of top-level nodes
- M = average number of nodes in each bottom list
Each merge operation takes O(M), and we perform it N times.

✅ Space Complexity: O(1) auxiliary + O(N) recursion stack
*/

class FlatteningLL {
    public class Node {
        int data;
        Node next;
        Node bottom;

        Node(int data) {
            this.data = data;
            next = null;
            bottom = null;
        }

    }

    // Helper function to merge two sorted bottom-linked lists
    Node mergeList(Node list1, Node list2){
        Node dummy = new Node(-1);
        Node res = dummy;
        
        while(list1 != null && list2 != null){
            if(list1.data <= list2.data){
                res.bottom = list1;
                res = list1;
                list1 = list1.bottom;
            } else {
                res.bottom = list2;
                res = list2;
                list2 = list2.bottom;
            }
            // Ensure next pointers are discarded to avoid misuse
            res.next = null;
        }
        
        // Attach remaining nodes
        if(list1 != null) res.bottom = list1;
        else res.bottom = list2;
        
        return dummy.bottom;
    }
    
    // Main function to flatten the linked list
    Node flatten(Node root) {
        if(root == null || root.next == null) return root;

        // Recursively flatten the rest of the list
        Node mergedHead = flatten(root.next);

        // Merge current root with flattened next list
        root = mergeList(root, mergedHead);

        return root;
    }
}
