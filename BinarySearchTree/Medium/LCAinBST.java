package BinarySearchTree.Medium;

/**
 * Problem: 235. Lowest Common Ancestor of a Binary Search Tree
 * 
 * Given a Binary Search Tree (BST), find the lowest common ancestor (LCA) 
 * of two nodes p and q.
 *
 * Definition:
 * - The lowest common ancestor is the lowest node in the tree that has both p and q
 *   as descendants (a node can be a descendant of itself).
 *
 * Examples:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8 → Output: 6
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4 → Output: 2
 * Input: root = [2,1], p = 2, q = 1 → Output: 2
 *
 * Constraints:
 * - 2 <= number of nodes <= 10^5
 * - All node values are unique
 * - Node values range: -10^9 <= Node.val <= 10^9
 * - p != q, both p and q will always exist in the tree
 * 
 * ------------------------------------------------------------------
 *
 * Intuition:
 * - This is a BST, so we can use its properties to find LCA efficiently.
 * - If both p and q are less than root, go to left subtree.
 * - If both p and q are greater than root, go to right subtree.
 * - Else, root is the LCA.
 *
 * ------------------------------------------------------------------
 *
 * Algorithm:
 * 1. Compare current root value with p and q.
 * 2. If root > p and root > q → recurse left.
 * 3. If root < p and root < q → recurse right.
 * 4. Else → root is between p and q → return root.
 *
 * ------------------------------------------------------------------
 *
 * Time Complexity: O(H)
 * - H is the height of the tree (O(log N) for balanced BST, O(N) worst case).
 *
 * Space Complexity: O(H)
 * - Due to recursion stack. O(log N) best, O(N) worst.
 *
 * ------------------------------------------------------------------
 */

/**
 * Definition for a binary tree node.
 */


class LCAinBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    
        TreeNode(int x) { val = x; }
    }
    
    // Function to find LCA in a BST
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // If both nodes are smaller than root → move left
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If both nodes are greater than root → move right
        else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // One is on the left and one is on the right (or root is one of them)
        else {
            return root;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        LCAinBST sol = new LCAinBST();

        // Tree: [6,2,8,0,4,7,9,null,null,3,5]
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        TreeNode p1 = root.left;             // Node with value 2
        TreeNode q1 = root.right;            // Node with value 8
        System.out.println("LCA of 2 and 8: " + sol.lowestCommonAncestor(root, p1, q1).val); // Output: 6

        TreeNode p2 = root.left;             // Node with value 2
        TreeNode q2 = root.left.right;       // Node with value 4
        System.out.println("LCA of 2 and 4: " + sol.lowestCommonAncestor(root, p2, q2).val); // Output: 2

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        TreeNode p3 = root2;                 // Node with value 2
        TreeNode q3 = root2.left;            // Node with value 1
        System.out.println("LCA of 2 and 1: " + sol.lowestCommonAncestor(root2, p3, q3).val); // Output: 2
    }
}
