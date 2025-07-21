package BinarySearchTree.Medium;
/**
 * ✅ Problem: Delete Node in a BST (Leetcode 450)
 * 
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated).
 *
 * 🔸 Deletion has 3 cases:
 *   1. Node is a leaf -> just delete it
 *   2. Node has 1 child -> connect parent to the child
 *   3. Node has 2 children -> replace node with inorder predecessor or successor
 *
 * ---------------------------------------------------------
 * ✅ Intuition:
 * - BST has ordered structure: left < root < right
 * - Use BST property to find node to delete efficiently
 * - Replace node properly based on child configuration
 *
 * ---------------------------------------------------------
 * ✅ Algorithm:
 * 1. Search for node with value == key
 * 2. If found:
 *    a) If it has no left, return right subtree
 *    b) If no right, return left subtree
 *    c) If both children exist:
 *       - Find rightmost node in left subtree (inorder predecessor)
 *       - Attach right subtree to this node
 *       - Return left subtree as new root
 * 3. Else, recursively search and update left or right subtree
 *
 * ---------------------------------------------------------
 * ✅ Time Complexity:
 * - O(H), where H = height of tree (O(logN) in balanced BST, O(N) in worst case)
 *
 * ✅ Space Complexity:
 * - O(H) recursive stack in worst case
 *
 * ---------------------------------------------------------
 * ✅ Dry Run:
 * Input: root = [5,3,6,2,4,null,7], key = 3
 *        => Delete node 3 (has 2 children)
 *        => Replace node 3 with 4 (rightmost in left subtree)
 * Output: [5,4,6,2,null,null,7]
 */

// ✅ Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // If key is found at root
        if (root.val == key) {
            return helper(root);
        }

        TreeNode dummy = root;

        // Traverse the BST to find and delete key
        while (root != null) {
            if (root.val > key) {
                if (root.left != null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }

        return dummy;
    }

    // Helper to return proper replacement for deleted node
    public TreeNode helper(TreeNode node) {
        // Case 1: No left child
        if (node.left == null) return node.right;

        // Case 2: No right child
        else if (node.right == null) return node.left;

        // Case 3: Both children exist
        TreeNode rightChild = node.right;
        TreeNode lastRight = findLastRight(node.left); // inorder predecessor
        lastRight.right = rightChild; // attach right subtree
        return node.left; // return left as new root
    }

    // Get the rightmost node in a subtree
    public TreeNode findLastRight(TreeNode node) {
        if (node.right == null) return node;
        return findLastRight(node.right);
    }
}

// ✅ Driver Code for testing
class Main {
    public static void main(String[] args) {
        DeleteNodeInBST sol = new DeleteNodeInBST();

        // Construct sample tree: [5,3,6,2,4,null,7]
        TreeNode root = new TreeNode(5,
                            new TreeNode(3,
                                new TreeNode(2),
                                new TreeNode(4)),
                            new TreeNode(6,
                                null,
                                new TreeNode(7)));

        int key = 3;

        TreeNode newRoot = sol.deleteNode(root, key);

        // Print inorder traversal of updated BST
        System.out.print("Inorder of Updated Tree: ");
        inorder(newRoot); // Should print 2 4 5 6 7
    }

    // Inorder traversal
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }
}
