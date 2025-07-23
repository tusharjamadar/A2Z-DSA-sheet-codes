package BinarySearchTree.Medium;

/**
 * ✅ Problem Summary:
 * You're given the root of a binary search tree (BST) where two nodes are swapped by mistake.
 * Your task is to recover the BST without changing its structure.
 * 
 * Example 1:
 * Input:  [1,3,null,null,2]  → Output: [3,1,null,null,2]
 * 
 * Example 2:
 * Input:  [3,1,4,null,null,2] → Output: [2,1,4,null,null,3]
 * 
 * Constraints:
 * - The number of nodes is in the range [2, 1000]
 * - Node values can be any integer within 32-bit range.
 *
 * Follow-up:
 * - Try to solve using O(1) space (Morris Traversal).
 */

/**
 * ✅ Brute Force Approach (Explanation Only):
 * 1. Do an inorder traversal and store the nodes in an ArrayList.
 * 2. The inorder traversal of a BST should be sorted.
 * 3. Identify the two nodes where the ordering breaks.
 * 4. Swap the values of the two incorrect nodes.
 * 
 * Time: O(n), Space: O(n) → storing all nodes.
 */

/**
 * ✅ Optimized Approach (Inorder Traversal with Pointers)
 * Idea:
 * - In a valid BST, inorder traversal gives a sorted list.
 * - If two nodes are swapped, the sorted order will break at 1 or 2 points.
 *   Case 1: Non-adjacent swapped nodes → 2 inversions (e.g., 3, 1, 4, 2)
 *   Case 2: Adjacent swapped nodes → 1 inversion (e.g., 1, 3, 2)
 * 
 * - During inorder traversal:
 *   - Track the previous node.
 *   - If current node < previous node → violation.
 *     - First violation: store 'first' as previous, 'mid' as current.
 *     - Second violation: store 'last' as current.
 * 
 * - After traversal, swap:
 *   - If two violations: swap first and last.
 *   - If one violation: swap first and mid.
 *
 * Time: O(n), Space: O(h) for recursion stack (or O(1) using Morris).
 */

/**
 * ✅ Definition for a binary tree node.
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

class RecoverBST {
    // Pointers to keep track of swapped nodes
    private TreeNode first;
    private TreeNode mid;
    private TreeNode last;
    private TreeNode prev;

    // Standard inorder traversal
    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        // Detect a violation in BST property
        if (prev != null && root.val < prev.val) {
            if (first == null) {
                // First violation
                first = prev;
                mid = root;
            } else {
                // Second violation
                last = root;
            }
        }

        prev = root;

        inorder(root.right);
    }

    // Main recovery function
    public void recoverTree(TreeNode root) {
        first = mid = last = null;
        prev = new TreeNode(Integer.MIN_VALUE); // Initialize prev with lowest possible value

        inorder(root);

        // Swap the nodes to fix the BST
        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && mid != null) {
            int temp = first.val;
            first.val = mid.val;
            mid.val = temp;
        }
    }

    public static void main(String[] args) {
        /*
         * Example: Tree = [3,1,4,null,null,2]
         *     3
         *    / \
         *   1   4
         *      /
         *     2
         * After recovery:
         *     2
         *    / \
         *   1   4
         *      /
         *     3
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("Before recovery (inorder):");
        printInorder(root); // Should be unsorted

        RecoverBST sol = new RecoverBST();
        sol.recoverTree(root);

        System.out.println("\nAfter recovery (inorder):");
        printInorder(root); // Should be sorted
    }

    // Utility function for inorder traversal
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
}

/**
 * ✅ Dry Run:
 * Input: [3,1,4,null,null,2]
 * Inorder traversal: 1 3 2 4 → Not sorted
 * Inversions:
 * - 3 > 2 (First violation) → first = 3, mid = 2
 * Swap first and mid → BST is restored
 */

/**
 * ✅ Time & Space Complexity:
 * Time Complexity: O(n) — Visit every node once in inorder.
 * Space Complexity:
 * - O(h) → recursion stack (h = height of tree)
 * - O(1) if using Morris Traversal (not implemented here)
 */

/**
 * ✅ Main Method to Test the Solution
 */
