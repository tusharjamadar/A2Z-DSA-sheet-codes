package BinaryTree.Medium;

import java.util.*;

/**
 * ✅ Problem: 236. Lowest Common Ancestor of a Binary Tree
 *
 * Given the root of a binary tree and two nodes `p` and `q`, return their **lowest common ancestor (LCA)**.
 *
 * ➤ A node can be a descendant of itself.
 *
 * 🔸 Constraints:
 * - All node values are unique.
 * - p and q are different and exist in the tree.
 *
 * ✅ Intuition:
 * - If we find either p or q, return that node.
 * - Recursively search left and right subtrees.
 * - If both sides return non-null, current node is LCA.
 * - If one side returns non-null, propagate that node up.
 *
 * ✅ Time Complexity: O(N)
 * - Visit each node once.
 *
 * ✅ Space Complexity: O(H)
 * - H = height of tree (recursion stack), worst case O(N)
 */

// 🔷 Definition for binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val){
        this.val = val;
    }
}

public class LowestCommonAncestor {

    // ✅ Function to find LCA
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) return root;

        return left != null ? left : right;
    }

    // 🔧 Helper to build tree from level order array like [3,5,1,6,2,0,8,null,null,7,4]
    public static TreeNode buildTree(String[] arr) {
        if(arr.length == 0 || arr[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while(!q.isEmpty() && i < arr.length){
            TreeNode current = q.poll();

            if(i < arr.length && !arr[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(current.left);
            }
            i++;

            if(i < arr.length && !arr[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // 🔧 Helper to find node by value
    public static TreeNode findNode(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) return root;

        TreeNode left = findNode(root.left, val);
        if(left != null) return left;

        return findNode(root.right, val);
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        // Example 1
        String[] input1 = {"3","5","1","6","2","0","8","null","null","7","4"};
        TreeNode root1 = buildTree(input1);
        TreeNode p1 = findNode(root1, 5);
        TreeNode q1 = findNode(root1, 1);
        TreeNode lca1 = lowestCommonAncestor(root1, p1, q1);
        System.out.println("LCA of 5 and 1: " + lca1.val);  // Output: 3

        // Example 2
        TreeNode p2 = findNode(root1, 5);
        TreeNode q2 = findNode(root1, 4);
        TreeNode lca2 = lowestCommonAncestor(root1, p2, q2);
        System.out.println("LCA of 5 and 4: " + lca2.val);  // Output: 5

        // Example 3
        String[] input3 = {"1","2"};
        TreeNode root3 = buildTree(input3);
        TreeNode p3 = findNode(root3, 1);
        TreeNode q3 = findNode(root3, 2);
        TreeNode lca3 = lowestCommonAncestor(root3, p3, q3);
        System.out.println("LCA of 1 and 2: " + lca3.val);  // Output: 1
    }
}
