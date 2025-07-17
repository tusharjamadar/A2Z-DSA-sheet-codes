package BinaryTree.Medium;
import java.util.*;

/**
 * ✅ Problem: 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Given preorder and inorder traversal of a binary tree, construct the tree.
 *
 * 🔸 preorder = [root, left subtree..., right subtree...]
 * 🔸 inorder  = [left subtree..., root, right subtree...]
 *
 * ✅ Intuition:
 * - The first element in preorder is always the root.
 * - Find that root in inorder → elements to the left are in the left subtree, right are in right subtree.
 * - Recursively repeat this for left and right subtrees.
 *
 * ✅ Algorithm:
 * 1. Use a hashmap to store value → index for inorder traversal for O(1) lookup.
 * 2. Start from the first element in preorder as root.
 * 3. Find the index of root in inorder to split left/right subtree.
 * 4. Recursively build left and right subtree using calculated bounds.
 *
 * ✅ Time Complexity: O(N)
 * - Each node is visited once, and hashmap gives O(1) lookup.
 *
 * ✅ Space Complexity: O(N)
 * - HashMap stores inorder indices: O(N)
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

public class ConstructTreeFromPreorderInorder {
    // Map to store inorder value → index
    private Map<Integer, Integer> inorderIndexMap;

    // ✅ Main function to build tree from preorder and inorder
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();

        // Fill map for quick lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Call recursive builder
        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // 🔁 Recursive helper to build tree
    private TreeNode build(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        // Root is the first element in preorder
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Find index of root in inorder to divide tree
        int rootIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = rootIndex - inStart;

        // 🔁 Recursively build left subtree
        root.left = build(preorder, preStart + 1, preStart + leftTreeSize, inStart, rootIndex - 1);

        // 🔁 Recursively build right subtree
        root.right = build(preorder, preStart + leftTreeSize + 1, preEnd, rootIndex + 1, inEnd);

        return root;
    }

    // ✅ Print tree in level-order to verify output
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size; i++){
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
        ConstructTreeFromPreorderInorder sol = new ConstructTreeFromPreorderInorder();

        // Example 1
        int[] preorder1 = {3,9,20,15,7};
        int[] inorder1  = {9,3,15,20,7};
        TreeNode root1 = sol.buildTree(preorder1, inorder1);
        System.out.println("Level-order (Example 1): " + levelOrder(root1)); // Output: [[3], [9, 20], [15, 7]]

        // Example 2
        int[] preorder2 = {-1};
        int[] inorder2  = {-1};
        TreeNode root2 = sol.buildTree(preorder2, inorder2);
        System.out.println("Level-order (Example 2): " + levelOrder(root2)); // Output: [[-1]]
    }
}
