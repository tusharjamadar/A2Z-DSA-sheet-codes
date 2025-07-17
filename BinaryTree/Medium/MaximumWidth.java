package BinaryTree.Medium;
import java.util.*;

/**
 * ✅ Problem: 662. Maximum Width of Binary Tree
 *
 * Given the root of a binary tree, return the **maximum width** of the tree.
 * Width of a level = distance between leftmost and rightmost non-null nodes, counted as if it's a full binary tree.
 *
 * 🔸 Null nodes between non-null nodes count toward width.
 * 🔸 Width is calculated based on indexing like a full binary tree.
 *
 * ✅ Intuition:
 * - Use BFS level-order traversal.
 * - Track each node’s index as if in a complete binary tree:
 *     * Left = 2*i + 1
 *     * Right = 2*i + 2
 * - To avoid overflow, subtract the index of the first node in each level (minIndex).
 * - Max width is the difference between first and last index in that level.
 *
 * ✅ Time Complexity: O(N)
 * - Every node is visited once.
 *
 * ✅ Space Complexity: O(N)
 * - Queue can store up to N nodes in the worst case.
 */

// 🔸 Tree node definition
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val){
        this.val = val;
    }
}

// 🔸 Pair class to track TreeNode + its index
class Pair {
    TreeNode node;
    int idx;

    Pair(TreeNode node, int idx){
        this.node = node;
        this.idx = idx;
    }
}

public class MaximumWidth {

    // ✅ Function to compute max width of binary tree
    public static int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0)); // root has index 0

        int maxWidth = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            int minIndex = q.peek().idx;  // to avoid overflow
            int first = 0, last = 0;

            for(int i = 0; i < size; i++) {
                Pair pair = q.poll();
                TreeNode node = pair.node;
                int curIdx = pair.idx - minIndex; // normalize index

                if(i == 0) first = curIdx;
                if(i == size - 1) last = curIdx;

                if(node.left != null)
                    q.offer(new Pair(node.left, 2 * curIdx + 1));

                if(node.right != null)
                    q.offer(new Pair(node.right, 2 * curIdx + 2));
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }

    // 🔧 Build tree from level-order array like ["1","3","2","5","3","null","9"]
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
        // Example 1
        String[] input1 = {"1","3","2","5","3","null","9"};
        TreeNode root1 = buildTree(input1);
        System.out.println("Max Width (Example 1): " + widthOfBinaryTree(root1)); // Output: 4

        // Example 2
        String[] input2 = {"1","3","2","5","null","null","9","6","null","7"};
        TreeNode root2 = buildTree(input2);
        System.out.println("Max Width (Example 2): " + widthOfBinaryTree(root2)); // Output: 7

        // Example 3
        String[] input3 = {"1","3","2","5"};
        TreeNode root3 = buildTree(input3);
        System.out.println("Max Width (Example 3): " + widthOfBinaryTree(root3)); // Output: 2
    }
}

