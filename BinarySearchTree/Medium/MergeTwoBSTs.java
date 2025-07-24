package BinarySearchTree.Medium;

import java.util.ArrayList;
import java.util.Stack;

// ✅ Problem Summary:
// Given two Binary Search Trees (BSTs), merge them into a single sorted list containing all their node values.
// Return this merged list in sorted order.

// ✅ Approach:
// We use **inorder traversal** for both BSTs using **two stacks**. This helps avoid storing all elements beforehand.
// At every step, we compare the top elements of both stacks (smallest unprocessed values from each tree), and add the smaller one to our result list.

// ✅ Time Complexity:
// O(m + n), where m and n are the number of nodes in the two BSTs.
// Every node is visited exactly once.

// ✅ Space Complexity:
// O(h1 + h2), where h1 and h2 are the heights of the two BSTs (stack space).

// ✅ Dry Run Example:
// root1 = [5, 3, 6, 2, 4], root2 = [2, 1, 3, N, N, N, 7, 6]
// In-order traversal gives:
// root1 => [2, 3, 4, 5, 6], root2 => [1, 2, 3, 6, 7]
// Merging => [1, 2, 2, 3, 3, 4, 5, 6, 6, 7]

// ✅ Node class used for BST
class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

class MergeTwoBSTs {

    // ✅ Helper function to push all left-side nodes into stack
    private void addLeftSide(Node node, Stack<Node> stack){
        while(node != null){
            stack.push(node); // push current node
            node = node.left; // move to left child
        }
    }

    // ✅ Main function to merge two BSTs and return elements in sorted order
    public ArrayList<Integer> merge(Node root1, Node root2) {
        Stack<Node> stack1 = new Stack<>(); // Stack for root1
        Stack<Node> stack2 = new Stack<>(); // Stack for root2
        
        // Initialize both stacks with leftmost nodes
        addLeftSide(root1, stack1);
        addLeftSide(root2, stack2);
        
        ArrayList<Integer> result = new ArrayList<>();

        // ✅ Merge process using iterative in-order traversal
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            // Compare top elements from both stacks
            if (stack1.peek().data < stack2.peek().data) {
                Node node = stack1.pop();           // Pop smaller value
                result.add(node.data);              // Add to result
                addLeftSide(node.right, stack1);    // Push left path of right subtree
            } else {
                Node node = stack2.pop();
                result.add(node.data);
                addLeftSide(node.right, stack2);
            }
        }

        // ✅ Add remaining elements from stack1 if any
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            result.add(node.data);
            addLeftSide(node.right, stack1);
        }

        // ✅ Add remaining elements from stack2 if any
        while (!stack2.isEmpty()) {
            Node node = stack2.pop();
            result.add(node.data);
            addLeftSide(node.right, stack2);
        }

        return result; // Final merged sorted list
    }

    // ✅ main method for testing
    public static void main(String[] args) {
        MergeTwoBSTs sol = new MergeTwoBSTs();

        // Construct first BST: [5, 3, 6, 2, 4]
        Node root1 = new Node(5);
        root1.left = new Node(3);
        root1.right = new Node(6);
        root1.left.left = new Node(2);
        root1.left.right = new Node(4);

        // Construct second BST: [2, 1, 3, N, N, N, 7, 6]
        Node root2 = new Node(2);
        root2.left = new Node(1);
        root2.right = new Node(3);
        root2.right.right = new Node(7);
        root2.right.right.left = new Node(6);

        // Merging and displaying the result
        ArrayList<Integer> mergedList = sol.merge(root1, root2);
        System.out.println("Merged BST in sorted order: " + mergedList);
    }
}
