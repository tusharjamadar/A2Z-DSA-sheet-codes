package BinaryTree.Medium;
import java.util.*;

/*
✅ Node class representing a tree node
*/
class Node {
    int data;
    Node left, right;

    Node(int data){
        this.data = data;
        left = right = null;
    }
}

public class TopViewOfBinaryTree {

    /*
    ✅ Problem: Top View of Binary Tree
    - Return the nodes visible when the tree is viewed from the top.
    - Nodes must be returned from leftmost to rightmost.
    - If two nodes share the same horizontal distance, only the topmost one (smallest depth) is considered.

    ✅ Intuition:
    - Perform DFS, assign each node a horizontal distance (order) and depth (level).
    - Use TreeMap to store the first seen node at each horizontal distance (ordered from left to right).

    ✅ Algorithm:
    1. Use DFS and maintain:
       - `order`: horizontal distance (root = 0)
       - `level`: depth of node
    2. For each node:
       - If this order is not in map OR current node is higher (smaller level), update map.
    3. Traverse TreeMap and collect node values.

    ✅ Time Complexity: O(N log N)
    - N nodes visited
    - log N insertion/access in TreeMap

    ✅ Space Complexity: O(N)
    - TreeMap stores up to N entries
    - Recursion stack up to height of tree
    */

    // DFS traversal function
    public static void dfs(Node root, int order, int level, TreeMap<Integer, int[]> map) {
        if (root == null) return;

        // If this column not visited OR current node is at a higher level (smaller depth)
        if (!map.containsKey(order) || level < map.get(order)[1]) {
            map.put(order, new int[]{root.data, level});
        }

        // Explore left and right children with updated order and level
        dfs(root.left, order - 1, level + 1, map);
        dfs(root.right, order + 1, level + 1, map);
    }

    // ✅ Main function to return top view
    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        TreeMap<Integer, int[]> map = new TreeMap<>();
        dfs(root, 0, 0, map);

        for (int[] val : map.values()) {
            res.add(val[0]);
        }

        return res;
    }

    // 🔧 Helper: Build tree from level-order array like ["1", "2", "3", "N", "4"]
    public static Node buildTree(String[] arr) {
        if (arr.length == 0 || arr[0].equals("N")) return null;

        Node root = new Node(Integer.parseInt(arr[0]));
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node current = q.poll();

            // Left child
            if (!arr[i].equals("N")) {
                current.left = new Node(Integer.parseInt(arr[i]));
                q.offer(current.left);
            }
            i++;
            if (i >= arr.length) break;

            // Right child
            if (!arr[i].equals("N")) {
                current.right = new Node(Integer.parseInt(arr[i]));
                q.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        // Test case 1
        String[] input1 = {"1", "2", "3"};
        Node root1 = buildTree(input1);
        System.out.println("Top View: " + topView(root1));  // Output: [2, 1, 3]

        // Test case 2
        String[] input2 = {"10", "20", "30", "40", "60", "90", "100"};
        Node root2 = buildTree(input2);
        System.out.println("Top View: " + topView(root2));  // Output: [40, 20, 10, 30, 100]

        // Test case 3
        String[] input3 = {"1", "2", "3", "N", "4", "N", "N", "N", "5", "N", "6"};
        Node root3 = buildTree(input3);
        System.out.println("Top View: " + topView(root3));  // Output: [2, 1, 3, 6]
    }
}
