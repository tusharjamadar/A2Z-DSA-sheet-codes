package BinarySearchTree.Medium;
// ✅ Problem Summary:
// Given a binary tree, find the size of the largest subtree which is also a Binary Search Tree (BST).
// A subtree is considered a BST if for every node, left < root < right and no duplicates.
// Return the size (number of nodes) of the largest such BST.

// ✅ Brute Force Approach (Explanation Only):
// For every node, check if the subtree rooted at that node is a BST and calculate its size.
// Use a helper function to validate BST properties and compute size recursively.
// Time Complexity: O(n^2) in worst case (as each node might recheck all its subtree nodes)

// ✅ Optimized Approach:
// Use postorder traversal. For each node, collect information:
// - Minimum value in the subtree
// - Maximum value in the subtree
// - Size of the largest BST in the subtree
// This allows merging subtrees efficiently and detecting whether current subtree is a valid BST.

class LargestBSTinBinaryTree {

    // Helper class to store info from subtrees
    static class NodeValue {
        int minVal;   // Minimum value in the subtree
        int maxVal;   // Maximum value in the subtree
        int maxSize;  // Size of the largest BST in the subtree

        NodeValue(int minVal, int maxVal, int maxSize) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.maxSize = maxSize;
        }
    }

    // Postorder traversal to find largest BST
    static NodeValue helper(Node root) {
        // Base case: Empty node is a BST of size 0
        if (root == null)
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        // Recurse left and right
        NodeValue left = helper(root.left);
        NodeValue right = helper(root.right);

        // Check if current subtree is a valid BST
        if (left.maxVal < root.data && root.data < right.minVal) {
            // It's a BST — update min, max, and size
            return new NodeValue(
                Math.min(left.minVal, root.data),
                Math.max(right.maxVal, root.data),
                left.maxSize + right.maxSize + 1
            );
        }

        // If not a BST, return invalid range and max of children
        return new NodeValue(
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            Math.max(left.maxSize, right.maxSize)
        );
    }

    // ✅ Main method to call helper and return final result
    static int largestBst(Node root) {
        return helper(root).maxSize;
    }

    // ✅ Dry Run Example:
    // Input: [5, 2, 4, 1, 3]
    // Tree:
    //        5
    //       / \
    //      2   4
    //     / \
    //    1   3
    //
    // Subtree rooted at 2 -> [2,1,3] is a BST of size 3 (valid BST)
    // Subtree rooted at 5 is not a BST (4 < 5 but is in the right subtree)
    // => Output: 3

    // ✅ Time Complexity:
    // O(n), where n is number of nodes in the tree (each node is visited once)

    // ✅ Space Complexity:
    // O(h), where h is height of the tree (stack space due to recursion)

    // ✅ Main Method for Testing
    public static void main(String[] args) {
        // Constructing the tree: [5, 2, 4, 1, 3]
        Node root = new Node(5);
        root.left = new Node(2);
        root.right = new Node(4);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        int result = largestBst(root);
        System.out.println("Size of Largest BST is: " + result); // Expected: 3
    }
}

// ✅ Tree Node class definition
class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}
