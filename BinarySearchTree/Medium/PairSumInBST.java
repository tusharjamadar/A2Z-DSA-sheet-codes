package BinarySearchTree.Medium;

import java.util.Stack;

/**
 * ✅ Problem Summary:
 * Given the root of a Binary Search Tree (BST) and an integer `k`, 
 * return true if there exist two elements in the BST such that their sum is equal to `k`.
 * Otherwise, return false.
 * 
 * A BST has the property:
 * - left subtree < root
 * - right subtree > root
 */

/**
 * ✅ Brute Force Approach (Explanation Only):
 * 1. Traverse all nodes in the BST and store their values in an ArrayList.
 * 2. Use two nested loops to check if any two values sum up to k.
 *    Time: O(n^2), Space: O(n)
 * 
 * ✅ Better Approach:
 * - Use HashSet while doing in-order traversal.
 * - For each node, check if (k - current.val) exists in the set.
 *   If yes, return true.
 *   Time: O(n), Space: O(n)
 * 
 * ✅ Optimized Approach using Preorder + Bounds:
 * - Use two BSTIterators:
 *   one for smallest values (in-order),
 *   one for largest values (reverse in-order).
 * - Apply two-pointer technique (like in sorted array).
 *   Time: O(n), Space: O(h) where h = tree height.
 */

/**
 * ✅ Definition for a binary tree node
 */
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * ✅ BSTIterator Class
 * Works for both in-order and reverse in-order traversal
 */
class BSTIterator {
    private Stack<TreeNode> st = new Stack<>();
    private boolean reverse;

    public BSTIterator(TreeNode root, boolean isReverse) {
        this.reverse = isReverse;
        pushAll(root);
    }

    // Return true if more nodes are available
    public boolean hasNext() {
        return !st.isEmpty();
    }

    // Return next value based on direction
    public int next() {
        TreeNode node = st.pop();
        if (reverse) pushAll(node.left);  // reverse in-order → left
        else pushAll(node.right);         // in-order → right
        return node.val;
    }

    // Push left or right path based on direction
    private void pushAll(TreeNode node) {
        while (node != null) {
            st.push(node);
            node = reverse ? node.right : node.left;
        }
    }
}

/**
 * ✅ Main Solution Class
 */
class PairSumInBST {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        // Left iterator → smallest
        // Right iterator → largest
        BSTIterator left = new BSTIterator(root, false);
        BSTIterator right = new BSTIterator(root, true);

        int i = left.next();    // smallest
        int j = right.next();   // largest

        while (i < j) {
            int sum = i + j;
            if (sum == k) return true;
            else if (sum < k) i = left.next();     // need bigger sum
            else j = right.next();                 // need smaller sum
        }
        return false;
    }

    public static void main(String[] args) {
        /*
            Tree:
                   5
                  / \
                 3   6
                / \   \
               2   4   7
        */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3, new TreeNode(2), new TreeNode(4));
        root.right = new TreeNode(6, null, new TreeNode(7));

        PairSumInBST sol = new PairSumInBST();

        // Test Case 1: k = 9 → 2 + 7 = 9 → true
        System.out.println("Test Case 1 (k = 9): " + sol.findTarget(root, 9)); // true

        // Test Case 2: k = 28 → no such pair → false
        System.out.println("Test Case 2 (k = 28): " + sol.findTarget(root, 28)); // false

        // Test Case 3: Single node
        TreeNode single = new TreeNode(10);
        System.out.println("Test Case 3 (k = 20): " + sol.findTarget(single, 20)); // false
    }
}

/**
 * ✅ Dry Run Example:
 * Tree:
 *        5
 *       / \
 *      3   6
 *     / \   \
 *    2   4   7
 * k = 9
 * 
 * left = 2 → 3 → 4 → 5 → 6 → 7 (in-order)
 * right = 7 → 6 → 5 → 4 → 3 → 2 (reverse in-order)
 * 
 * Iteration 1: i = 2, j = 7 → sum = 9 → ✅ return true
 */

/**
 * ✅ Time Complexity:
 * - O(n) in worst-case for traversing all nodes.
 * - Each next() and hasNext() is O(1) amortized.
 * 
 * ✅ Space Complexity:
 * - O(h) where h is the height of the BST.
 * - Two stacks are maintained: one for left, one for right
 */
