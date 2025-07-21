package BinarySearchTree.Medium;
/**
 * Problem: 230. Kth Smallest Element in a BST
 * 
 * Given the root of a binary search tree (BST) and an integer k,
 * return the k-th smallest value (1-indexed) among all the nodes.
 * 
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * 
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * 
 * Constraints:
 * - The number of nodes in the tree is n.
 * - 1 <= k <= n <= 10^4
 * - 0 <= Node.val <= 10^4
 * 
 * ---------------------------------------------------------------------
 * 
 * Intuition:
 * - Inorder traversal of BST gives nodes in ascending order.
 * - Perform an inorder traversal and keep a count of nodes visited.
 * - When count == k, return that node's value.
 * 
 * ---------------------------------------------------------------------
 * 
 * Algorithm:
 * 1. Traverse left subtree recursively.
 * 2. Increment counter at each node visit.
 * 3. When counter == k, store the node's value.
 * 4. Stop further traversal once the k-th element is found.
 * 
 * ---------------------------------------------------------------------
 * 
 * Time Complexity: O(H + k)
 * - Worst case O(n) when the tree is skewed or k is large.
 * - Best case O(H + k), where H = height of the tree (log n for balanced).
 * 
 * Space Complexity: O(H)
 * - Due to recursion stack. No extra space used apart from that.
 * 
 * ---------------------------------------------------------------------
 */

/**
 * Definition for a binary tree node.
 */


class KthSmallestElement {
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }
    // Helper function for inorder traversal
    public void inorder(TreeNode root, int k, int[] smallest, int[] counter) {
        if (root == null || counter[0] >= k) return;

        inorder(root.left, k, smallest, counter);

        counter[0]++;
        if (counter[0] == k) {
            smallest[0] = root.val;
            return;
        }

        inorder(root.right, k, smallest, counter);
    }

    // Main function to return k-th smallest element
    public int kthSmallest(TreeNode root, int k) {
        int[] smallest = new int[1];  // To hold result
        int[] counter = new int[1];   // To count visited nodes

        inorder(root, k, smallest, counter);
        return smallest[0];
    }

    // Main method for testing
    public static void main(String[] args) {
        KthSmallestElement sol = new KthSmallestElement();

        // Example 1: root = [3,1,4,null,2], k = 1 → Output: 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println("Kth Smallest (k=1): " + sol.kthSmallest(root1, 1));  // Output: 1

        // Example 2: root = [5,3,6,2,4,null,null,1], k = 3 → Output: 3
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        System.out.println("Kth Smallest (k=3): " + sol.kthSmallest(root2, 3));  // Output: 3
    }
}
