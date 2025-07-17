package BinaryTree.Easy;
import java.util.*;

/**
 * ✅ Problem: 222. Count Complete Tree Nodes
 *
 * Given the root of a **complete binary tree**, return the number of nodes.
 *
 * 🔸 A complete binary tree is filled on all levels except possibly the last.
 * 🔸 All nodes in the last level are as far left as possible.
 *
 * 🔸 Goal: O(log^2 N) solution, faster than O(N)
 *
 * ✅ Intuition:
 * - For a complete binary tree:
 *     * If left height == right height: it's a full tree → node count = 2^h - 1
 *     * Else: recursively count left and right subtrees
 *
 * ✅ Algorithm:
 * 1. Find left and right subtree heights.
 * 2. If equal → full tree → return (2^h - 1)
 * 3. Else → count recursively left + right + 1 (current node)
 *
 * ✅ Time Complexity:
 * - O(log^2 N):
 *     * log N levels of recursion
 *     * Each height computation is O(log N)
 *
 * ✅ Space Complexity:
 * - O(log N) recursion stack
 */

// 🔸 Tree node definition
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val){
        this.val = val;
    }
}

public class CountCompleteNodes {

    // ✅ Function to count nodes in complete binary tree
    public static int countNodes(TreeNode root) {
        if(root == null) return 0;

        int lh = findLeftHeight(root);
        int rh = findRightHeight(root);

        if(lh == rh) {
            // 2^h - 1 nodes in a perfect binary tree
            return (1 << (lh + 1)) - 1;
        }

        // Recursively count left and right + root
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 🔸 Leftmost height (top to bottom)
    public static int findLeftHeight(TreeNode root) {
        int count = 0;
        while(root.left != null) {
            count++;
            root = root.left;
        }
        return count;
    }

    // 🔸 Rightmost height (top to bottom)
    public static int findRightHeight(TreeNode root) {
        int count = 0;
        while(root.right != null) {
            count++;
            root = root.right;
        }
        return count;
    }

    // 🔧 Build tree from level-order array like ["1","2","3","4","5","6"]
    public static TreeNode buildTree(String[] arr) {
        if(arr.length == 0 || arr[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while(!q.isEmpty() && i < arr.length) {
            TreeNode current = q.poll();

            if(i < arr.length && !arr[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(current.left);
            }
            i++;

            if(i < arr.length && !arr[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(arr[i]));
                q.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // ✅ Main method to test examples
    public static void main(String[] args) {
        // Example 1: [1,2,3,4,5,6] => 6 nodes
        String[] input1 = {"1", "2", "3", "4", "5", "6"};
        TreeNode root1 = buildTree(input1);
        System.out.println("Total Nodes (Example 1): " + countNodes(root1)); // Output: 6

        // Example 2: [] => 0 nodes
        String[] input2 = {};
        TreeNode root2 = buildTree(input2);
        System.out.println("Total Nodes (Example 2): " + countNodes(root2)); // Output: 0

        // Example 3: [1] => 1 node
        String[] input3 = {"1"};
        TreeNode root3 = buildTree(input3);
        System.out.println("Total Nodes (Example 3): " + countNodes(root3)); // Output: 1
    }
}
