package BinaryTree.Medium;
import java.util.*;

/**
 * ✅ Problem: 106. Construct Binary Tree from Inorder and Postorder Traversal
 *
 * Given inorder and postorder traversal of a binary tree, construct the tree.
 *
 * 🔸 inorder    = [left subtree..., root, right subtree...]
 * 🔸 postorder  = [left subtree..., right subtree..., root]
 *
 * ✅ Intuition:
 * - The last element in postorder is always the root.
 * - Find root in inorder → elements to the left are in the left subtree, right in right subtree.
 * - Recursively build the tree from postorder and inorder segments.
 *
 * ✅ Algorithm:
 * 1. Create a map of inorder value → index for quick lookup.
 * 2. Use a helper function `solve()`:
 *    - Last element in current postorder range is the root.
 *    - Find that value in inorder to determine left/right subtree sizes.
 *    - Recur for left and right subtrees using calculated bounds.
 *
 * ✅ Time Complexity: O(N)
 * - Each node is visited once; hashmap lookup is O(1)
 *
 * ✅ Space Complexity: O(N)
 * - HashMap stores indices: O(N)
 * - Recursion stack: O(N) in worst case (skewed tree)
 */

// 🔸 Tree node definition
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val){
        this.val = val;
    }
}

public class ConstructTreeFromPostorderInorder {
    // Map for quick index lookup from inorder
    private Map<Integer, Integer> inorderIndexMap;

    // ✅ Main method to build tree from inorder & postorder
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderIndexMap = new HashMap<>();
        int n = inorder.length;

        // Build value → index map for inorder
        for(int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Recursively build tree
        return solve(postorder, 0, n - 1, 0, n - 1);
    }

    // 🔁 Recursive helper to build tree
    private TreeNode solve(int[] postorder, int postStart, int postEnd, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;

        // Last value in postorder is the root
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // Find root in inorder to split tree
        int rootIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = rootIndex - inStart;

        // 🔁 Recursively build left subtree
        root.left = solve(postorder, postStart, postStart + leftTreeSize - 1,
                          inStart, rootIndex - 1);

        // 🔁 Recursively build right subtree
        root.right = solve(postorder, postStart + leftTreeSize, postEnd - 1,
                           rootIndex + 1, inEnd);

        return root;
    }

    // ✅ Print tree in level-order to verify
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            res.add(level);
        }

        return res;
    }

    // 🔧 Main method for testing
    public static void main(String[] args) {
        ConstructTreeFromPostorderInorder sol = new ConstructTreeFromPostorderInorder();

        // Example 1
        int[] inorder1 = {9,3,15,20,7};
        int[] postorder1 = {9,15,7,20,3};
        TreeNode root1 = sol.buildTree(inorder1, postorder1);
        System.out.println("Level-order (Example 1): " + levelOrder(root1)); // Output: [[3], [9, 20], [15, 7]]

        // Example 2
        int[] inorder2 = {-1};
        int[] postorder2 = {-1};
        TreeNode root2 = sol.buildTree(inorder2, postorder2);
        System.out.println("Level-order (Example 2): " + levelOrder(root2)); // Output: [[-1]]
    }
}
