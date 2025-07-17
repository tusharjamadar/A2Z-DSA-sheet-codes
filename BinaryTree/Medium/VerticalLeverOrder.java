package BinaryTree.Medium;
import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class VerticalLeverOrder {

    // ✅ TreeMap to store column-wise entries
    // Key: column index
    // Value: List of pairs [node value, row index]
    Map<Integer, List<int[]>> map = new TreeMap<>();

    /*
     ✅ Problem Summary:
     Given the root of a binary tree, return its vertical order traversal.
     Each node has a (row, col) position:
     - Left child → (row+1, col-1)
     - Right child → (row+1, col+1)
     
     Requirements:
     - Sort nodes by column
     - If nodes are on the same column and row → sort by value
     - Return a list of lists (one list for each vertical column)
    */

    /*
     ✅ Intuition:
     Use DFS to assign (col, row) to each node.
     Store values in TreeMap<col, List<(val, row)>>:
     - Columns are auto-sorted
     - Then sort each list by row first, then value
    */

    /*
     ✅ Algorithm:
     1. DFS traversal to collect each node’s (col, row) and value.
     2. After traversal, for each column:
         - Sort the list by (row, then value)
     3. Collect and return sorted values column by column.
    */

    /*
     ✅ Time & Space Complexity:
     Time: O(N log N)
     - N for visiting nodes
     - log N for sorting nodes in columns
     
     Space: O(N)
     - For storing all nodes in TreeMap
    */

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: DFS traversal to collect (col, row, val)
        dfs(root, 0, 0);

        // Step 2: Process TreeMap by column
        for (int col : map.keySet()) {
            List<int[]> nodeList = map.get(col);

            // Sort each list by row first, then by value
            Collections.sort(nodeList, (a, b) -> {
                if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            });

            // Step 3: Extract values only for this column
            List<Integer> colValues = new ArrayList<>();
            for (int[] pair : nodeList) {
                colValues.add(pair[0]);
            }

            result.add(colValues);
        }

        return result;
    }

    // DFS function to assign (col, row) for each node
    private void dfs(TreeNode node, int col, int row) {
        if (node == null) return;

        map.putIfAbsent(col, new ArrayList<>());
        map.get(col).add(new int[]{node.val, row});

        dfs(node.left, col - 1, row + 1);
        dfs(node.right, col + 1, row + 1);
    }

    // 🔧 Helper function to build binary tree from array input
    // Example: ["1", "2", "3", "4", "N", "5", "6"]
    public static TreeNode buildTree(String[] nodes) {
        if (nodes.length == 0 || nodes[0].equals("N")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();

            // Left child
            if (!nodes[i].equals("N")) {
                current.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(current.left);
            }
            i++;
            if (i >= nodes.length) break;

            // Right child
            if (!nodes[i].equals("N")) {
                current.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // ✅ Main function to test the code
    public static void main(String[] args) {
        /*
         Example:
         Input: root = [1,2,3,4,5,6,7]
             Tree looks like:
                     1
                   /   \
                 2       3
               /   \    /  \
              4     5  6    7
         Expected Output: [[4],[2],[1,5,6],[3],[7]]
        */

        String[] input = {"1", "2", "3", "4", "5", "6", "7"};
        TreeNode root = buildTree(input);

        VerticalLeverOrder sol = new VerticalLeverOrder();
        List<List<Integer>> output = sol.verticalTraversal(root);

        // Print the result
        for (List<Integer> col : output) {
            System.out.println(col);
        }
    }
}
