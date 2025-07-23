package BinarySearchTree.Medium;
/**
 * ✅ Problem Summary:
 * Given a preorder traversal array of a BST, reconstruct the BST and return its root.
 * The BST property: left subtree values < root < right subtree values.
 * The preorder traversal: visit root ➝ left ➝ right.
 * 
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12] (Tree structure)
 *
 * Constraints:
 * - 1 <= preorder.length <= 100
 * - 1 <= preorder[i] <= 1000
 * - All values are unique
 */

/**
 * ✅ Brute Force Approach (Explanation Only):
 * 1. Take first element as root.
 * 2. Partition remaining array into left (less than root) and right (greater).
 * 3. Recursively repeat above for left and right.
 * ❌ Inefficient due to array copying → O(N^2) time complexity.
 */

/**
 * ✅ Alternative Using Preorder + Inorder (Explanation Only):
 * 1. Inorder of BST = sorted preorder.
 * 2. Use preorder and sorted preorder (i.e., inorder) to reconstruct tree.
 * ❌ Still takes O(N log N) due to sorting + O(N) space for inorder.
 */

/**
 * ✅ Optimal Approach (Used in code below):
 * - Use preorder traversal directly and bounds [min, max] to ensure valid BST placement.
 * - Maintain an index while recursing; each valid element becomes node only if it’s in [min, max].
 *
 * 🔁 Time Complexity: O(N) since each node is processed once.
 * 🧠 Space Complexity: O(H), where H is tree height (O(logN) for balanced, O(N) worst).
 */

 class BstFromPreorder {
    // Index tracker to simulate preorder traversal during recursion
    int idx = 0;

    /**
     * Recursive helper function to construct BST using min-max bounds.
     */
    public TreeNode helper(int[] preorder, int minVal, int maxVal){
        // Base case: index out of bounds
        if (idx >= preorder.length) return null;

        int val = preorder[idx];

        // Value not in the valid BST range for this subtree
        if (val < minVal || val > maxVal) return null;

        // Valid node, create and move index forward
        TreeNode node = new TreeNode(val);
        idx++;

        // Recur for left subtree with updated max bound
        node.left = helper(preorder, minVal, val - 1);

        // Recur for right subtree with updated min bound
        node.right = helper(preorder, val + 1, maxVal);

        return node;
    }

    /**
     * Main function to construct BST from preorder traversal
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * ✅ Dry Run Example:
     * Input: [8,5,1,7,10,12]
     * - idx=0, 8 → root
     * - idx=1, 5 < 8 → left of 8
     * - idx=2, 1 < 5 → left of 5
     * - idx=3, 7 > 5 but < 8 → right of 5
     * - idx=4, 10 > 8 → right of 8
     * - idx=5, 12 > 10 → right of 10
     *
     * Tree:
     *         8
     *       /   \
     *     5      10
     *    / \       \
     *   1   7       12
     */

    /**
     * ✅ Utility to print tree inorder (for testing)
     */
    public static void inorderPrint(TreeNode root){
        if(root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    /**
     * ✅ Main method for testing
     */
    public static void main(String[] args) {
        BstFromPreorder sol = new BstFromPreorder();
        
        int[] preorder = {8,5,1,7,10,12};
        TreeNode root = sol.bstFromPreorder(preorder);

        System.out.print("Inorder of Constructed BST: ");
        inorderPrint(root); // Should print: 1 5 7 8 10 12
    }
}

/**
 * ✅ Time Complexity: O(N) — each node processed once.
 * ✅ Space Complexity:
 *     - O(H) for recursion stack, H = height of tree
 *     - Worst-case O(N) (unbalanced), best-case O(logN) (balanced).
 */
