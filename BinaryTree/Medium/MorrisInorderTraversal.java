package BinaryTree.Medium;
/**
 * ✅ Problem Summary:
 * Given the root of a binary tree, return the inorder traversal (Left, Root, Right)
 * of its nodes' values. The follow-up is to solve it iteratively without recursion or stack.
 * 
 * Morris Traversal is used here — it uses threaded binary trees to simulate recursion 
 * with O(1) space by modifying the tree temporarily.
 *
 * ✅ Intuition:
 * Morris traversal creates temporary threads (right pointers) to in-order predecessors.
 * It avoids using a stack or recursion. We "visit" the node only when the left subtree
 * is done — true to in-order traversal. When we're done, we restore the tree.
 *
 * ✅ Algorithm:
 * 1. Initialize current node = root
 * 2. While current is not null:
 *     a. If current.left == null:
 *          - Add current.val to result
 *          - Move to current.right
 *     b. Else:
 *          - Find the inorder predecessor (rightmost node in left subtree)
 *          - If its right is null:
 *              - Set right = current (create thread)
 *              - Move to current.left
 *          - Else:
 *              - Remove thread
 *              - Add current.val to result
 *              - Move to current.right
 *
 * ✅ Time Complexity: O(N) — Each node is visited at most twice
 * ✅ Space Complexity: O(1) — No stack or recursion used (excluding output list)
 *
 * ✅ Dry Run:
 * Input Tree: [1,null,2,3]
 * Structure:
 *     1
 *      \
 *       2
 *      /
 *     3
 *
 * Steps:
 * - curr = 1 (left is null): add 1 → go to 2
 * - curr = 2 (left is 3): set 3.right = 2 → go to 3
 * - curr = 3 (left is null): add 3 → go to 2 (via thread)
 * - Remove thread, add 2 → go to null
 * Output: [1,3,2]
 */

import java.util.*;

public class MorrisInorderTraversal {
    
    // ✅ Morris Inorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // No left child — visit the node and move to right
                inorder.add(curr.val);
                curr = curr.right;
            } else {
                // Find the rightmost node in left subtree (inorder predecessor)
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    // First visit: create a thread to come back later
                    prev.right = curr;
                    // inorder.add(curr.val); if we add here then it will preorder traversal
                    curr = curr.left;
                } else {
                    // Second visit: remove the thread and process node
                    prev.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return inorder;
    }

    // ✅ Test Main Method
    public static void main(String[] args) {
        /*
            Tree:
                  1
                   \
                    2
                   /
                  3
            Input: [1,null,2,3]
            Expected Output: [1,3,2]
        */

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        MorrisInorderTraversal sol = new MorrisInorderTraversal();
        List<Integer> result = sol.inorderTraversal(root);

        System.out.println("Inorder Traversal (Morris): " + result);  // Output: [1,3,2]
    }
}

// ✅ TreeNode Definition
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
