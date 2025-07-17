package BinaryTree.Easy;

import java.util.ArrayList;

/*
 ✅ Problem Statement:
 Given a Binary Tree, perform the Boundary Traversal in this order:
    1. Left boundary (excluding leaves)
    2. All leaf nodes (left to right)
    3. Reverse right boundary (excluding leaves)

 ✅ Intuition:
 Traverse the tree in 3 phases:
   - Collect all nodes in the left boundary by always preferring the left child.
   - Collect all leaf nodes using DFS traversal.
   - Collect all nodes in the right boundary by preferring the right child, and reverse them.

 ✅ Step-by-step Algorithm:
 1. Add root node to the result if it's not a leaf.
 2. Traverse the left boundary:
    - Go left as far as possible, or go right if left is not available.
    - Do not include leaf nodes.
 3. Collect all leaf nodes using DFS (preorder or inorder).
 4. Traverse the right boundary:
    - Go right as far as possible, or go left if right is not available.
    - Do not include leaf nodes.
    - Add them in reverse order to the result.

 ✅ Time Complexity:
 O(N) – each node is visited once.

 ✅ Space Complexity:
 O(H) – for recursion stack where H is tree height, plus O(N) for output list.
*/


class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}


class BoundaryTraversal {

    // Check if a node is a leaf node
    public boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    // Add all the left boundary nodes (excluding leaves)
    public void addLeftNodes(Node node, ArrayList<Integer> res) {
        Node curr = node.left;
        while (curr != null) {
            if (!isLeaf(curr)) res.add(curr.data);

            // Prefer left child, if not present go to right
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    // Add all leaf nodes (from left to right)
    public void addLeafNodes(Node node, ArrayList<Integer> res) {
        if (node == null) return;
        if (isLeaf(node)) {
            res.add(node.data);
            return;
        }

        addLeafNodes(node.left, res);
        addLeafNodes(node.right, res);
    }

    // Add all the right boundary nodes in reverse (excluding leaves)
    public void addRightNodes(Node node, ArrayList<Integer> res) {
        Node curr = node.right;
        ArrayList<Integer> temp = new ArrayList<>();

        while (curr != null) {
            if (!isLeaf(curr)) temp.add(curr.data);

            // Prefer right child, if not present go to left
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }

        // Add right boundary in reverse order
        for (int i = temp.size() - 1; i >= 0; i--) {
            res.add(temp.get(i));
        }
    }

    // Main boundary traversal function
    ArrayList<Integer> boundaryTraversal(Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null) return res;

        // Add root if not a leaf
        if (!isLeaf(node)) res.add(node.data);

        addLeftNodes(node, res);   // Step 1: Left boundary
        addLeafNodes(node, res);   // Step 2: Leaf nodes
        addRightNodes(node, res);  // Step 3: Right boundary (reverse)

        return res;
    }
}
