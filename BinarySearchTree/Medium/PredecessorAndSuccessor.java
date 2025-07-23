package BinarySearchTree.Medium;

import java.util.ArrayList;
/* ✅ Problem Statement:
   Given the root of a Binary Search Tree and an integer key,
   find the inorder predecessor and successor of the given key.

   ➤ Inorder predecessor: Node with the largest value smaller than key.
   ➤ Inorder successor: Node with the smallest value greater than key.
   ➤ If predecessor or successor doesn’t exist, return null for that.
*/


/* ✅ Brute Force Approach:
   1. Do an inorder traversal of BST.
   2. Store all values in a list.
   3. Find the predecessor (value just before key) and successor (just after key) by scanning the list.

   ➤ Time Complexity: O(n)
   ➤ Space Complexity: O(n)
   ⚠️ Not efficient for large BSTs.
*/


/* ✅ Optimized BST-based Approach (used in code):
   Since it's a BST:
   ➤ If key < root -> successor could be root (move left).
   ➤ If key > root -> predecessor could be root (move right).
   ➤ Repeat until node is null.
   ➤ This gives us O(h) time complexity (h = height of BST).

   ➤ Time Complexity: O(h)
   ➤ Space Complexity: O(1)
*/

// Tree node definition
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}

class PredecessorAndSuccessor {

    // ✅ Helper to find successor (next greater node)
    public Node findSuccessor(Node root, int key) {
        Node succ = null;

        while (root != null) {
            if (key >= root.data) {
                // Go right as key >= current node
                root = root.right;
            } else {
                // Current node could be successor
                succ = root;
                root = root.left;
            }
        }

        return succ;
    }

    // ✅ Helper to find predecessor (next smaller node)
    public Node findPredecessor(Node root, int key) {
        Node pred = null;

        while (root != null) {
            if (key <= root.data) {
                // Go left as key <= current node
                root = root.left;
            } else {
                // Current node could be predecessor
                pred = root;
                root = root.right;
            }
        }

        return pred;
    }

    // ✅ Main function to find both predecessor and successor
    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> res = new ArrayList<>();

        Node pred = findPredecessor(root, key);
        Node succ = findSuccessor(root, key);

        res.add(pred);  // index 0 → predecessor
        res.add(succ);  // index 1 → successor

        return res;
    }
}

/* ✅ Main function to test the above code */
class Main {
    public static void main(String[] args) {
        /*
              10
             /  \
            2   11
           / \
          1   5
             / \
            3   6
               /
              4
        */

        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(11);
        root.left.left = new Node(1);
        root.left.right = new Node(5);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(6);
        root.left.right.right.left = new Node(4);

        int key = 11;

        PredecessorAndSuccessor sol = new PredecessorAndSuccessor();
        ArrayList<Node> result = sol.findPreSuc(root, key);

        Node pred = result.get(0);
        Node succ = result.get(1);

        System.out.println("Predecessor: " + (pred != null ? pred.data : -1));
        System.out.println("Successor: " + (succ != null ? succ.data : -1));
    }
}
