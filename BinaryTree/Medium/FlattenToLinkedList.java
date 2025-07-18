package BinaryTree.Medium;

import java.util.*;

/**
 * ✅ Problem: Flatten Binary Tree to Linked List (LC 114)
 *
 * Given the root of a binary tree, flatten it into a "linked list" in-place
 * using preorder traversal (Root -> Left -> Right).
 * The resulting "list" must use the right pointer and all left pointers must be
 * null.
 *
 * ✅ Constraints:
 * - Number of nodes in the tree: 0 to 2000
 * - Values: -100 to 100
 */

public class FlattenToLinkedList {

    /**
     * ✅ TreeNode Class Definition
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * ✅ 1. Brute Force Solution
     * ------------------------------------
     * ✅ Intuition:
     * Use preorder traversal to collect all node values in a list,
     * then reconstruct the flattened tree using new nodes.
     *
     * ✅ Algorithm:
     * 1. Traverse in preorder and collect node values.
     * 2. Rebuild the tree with new TreeNode instances using the collected values.
     *
     * ✅ Time Complexity: O(N) — visit each node once
     * ✅ Space Complexity: O(N) — extra space for list
     */
    public void flattenBrute(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return;

        List<Integer> preorder = new ArrayList<>();
        collectPreorder(root, preorder);

        root.left = null;
        TreeNode curr = root;
        for (int i = 1; i < preorder.size(); i++) {
            curr.right = new TreeNode(preorder.get(i));
            curr = curr.right;
        }
    }

    private void collectPreorder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        list.add(node.val);
        collectPreorder(node.left, list);
        collectPreorder(node.right, list);
    }

    /**
     * ✅ 2. Recursive Solution (Better)
     * ------------------------------------
     * ✅ Intuition:
     * Modify the tree in reverse postorder traversal (Right -> Left -> Root)
     * and track the previously processed node to rewire the right pointers.
     *
     * ✅ Algorithm:
     * Use a class-level `prev` pointer to attach current node's right to it.
     * Nullify left pointer.
     *
     * ✅ Time Complexity: O(N)
     * ✅ Space Complexity: O(H) — due to recursion stack
     */
    TreeNode prev = null;

    public void flattenRecursive(TreeNode root) {
        if (root == null)
            return;
        flattenRecursive(root.right);
        flattenRecursive(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    /**
     * ✅ 3. Iterative Solution (Using Stack)
     * ------------------------------------
     * ✅ Intuition:
     * Simulate preorder traversal using a stack.
     * Rewire nodes as we visit them in the traversal order.
     *
     * ✅ Algorithm:
     * - Push right first, then left (because stack is LIFO).
     * - Set current node’s right to stack.peek() (next preorder node).
     *
     * ✅ Time Complexity: O(N)
     * ✅ Space Complexity: O(N)
     */
    public void flattenIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);

            if (!stack.isEmpty())
                curr.right = stack.peek();
            curr.left = null;
        }
    }

    /**
     * ✅ 4. Morris Traversal (Most Optimal, O(1) Space)
     * ------------------------------------
     * ✅ Intuition:
     * Use Morris traversal to flatten the tree in-place.
     * Connect the rightmost node in left subtree to current.right.
     *
     * ✅ Algorithm:
     * - For each node with a left child:
     * - Find the rightmost node in left subtree.
     * - Link its right to current.right.
     * - Move left subtree to right.
     * - Nullify left pointer.
     *
     * ✅ Time Complexity: O(N)
     * ✅ Space Complexity: O(1)
     */
    public void flattenMorris(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;

                while (prev.right != null) {
                    prev = prev.right;
                }

                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    /**
     * ✅ Main Method for Testing
     * Example: root = [1,2,5,3,4,null,6]
     * Tree:
     * 1
     * / \
     * 2 5
     * / \ \
     * 3 4 6
     * Expected Flattened: 1 → 2 → 3 → 4 → 5 → 6
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6)));

        FlattenToLinkedList sol = new FlattenToLinkedList();

        // Uncomment one at a time for testing:

        // sol.flattenBrute(root);
        // sol.flattenRecursive(root);
        // sol.flattenIterative(root);
        sol.flattenMorris(root);

        System.out.print("Flattened Tree: ");
        printFlattened(root); // Output: 1 2 3 4 5 6
    }

    // Utility: Print flattened tree
    public static void printFlattened(TreeNode root) {
        while (root != null) {
            System.out.print(root.val + " ");
            if (root.left != null) {
                System.out.print("(Invalid: left not null) ");
            }
            root = root.right;
        }
        System.out.println();
    }
}
