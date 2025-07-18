package BinaryTree.Medium;
import java.util.*;

/**
 * ✅ Problem: 297. Serialize and Deserialize Binary Tree
 * 
 * Serialization = Convert a binary tree to a string
 * Deserialization = Reconstruct the binary tree from that string
 *
 * You can use any custom format. The goal is to ensure the structure is preserved.
 *
 * 🔸 Example:
 * Input Tree:      1
 *                /   \
 *               2     3
 *                    / \
 *                   4   5
 * Serialized: "1,2,#,#,3,4,#,#,5,#,#,"
 *
 * ✅ Intuition:
 * Use preorder traversal:
 * - Visit root, then left, then right
 * - Represent null with `#`
 * - Separate values by comma
 * 
 * ✅ Algorithm:
 * ▶ serialize(TreeNode root):
 *   - Use preorder traversal and build string recursively.
 *   - Append "#" for nulls to preserve structure.
 *
 * ▶ deserialize(String data):
 *   - Split string by "," to get tokens
 *   - Use recursion and index pointer (array) to rebuild tree in preorder fashion
 *
 * ✅ Dry Run:
 * Tree: [1,2,3,null,null,4,5]
 * serialize → "1,2,#,#,3,4,#,#,5,#,#,"
 *
 * deserialize:
 * i=0: "1" → root = 1
 * i=1: "2" → left = 2
 * i=2: "#" → left.left = null
 * i=3: "#" → left.right = null
 * i=4: "3" → right = 3
 * i=5: "4" → right.left = 4
 * i=6: "#" → null
 * i=7: "#" → null
 * i=8: "5" → right.right = 5
 * i=9: "#" → null
 * i=10:"#” → null
 *
 * ✅ Time Complexity: O(N)
 * - Visit each node once
 *
 * ✅ Space Complexity: O(N)
 * - Recursion stack (in worst case), and string length is proportional to number of nodes
 */

// 🔸 TreeNode Definition
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class SerializeDeserialize {

    // 🔁 Helper method to serialize using preorder traversal
    private void serialize_helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }

        sb.append(root.val).append(",");
        serialize_helper(root.left, sb);
        serialize_helper(root.right, sb);
    }

    // ✅ Converts tree to string
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_helper(root, sb);
        return sb.toString();
    }

    // 🔁 Helper to deserialize from string using preorder
    private TreeNode deserialize_helper(String[] arr, int[] i) {
        if (i[0] >= arr.length || arr[i[0]].equals("#")) {
            i[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr[i[0]++]));
        node.left = deserialize_helper(arr, i);
        node.right = deserialize_helper(arr, i);

        return node;
    }

    // ✅ Converts string back to tree
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] i = new int[1];  // index pointer as array for pass-by-reference
        return deserialize_helper(arr, i);
    }

    // 🔧 Print tree in level-order (for testing)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        SerializeDeserialize codec = new SerializeDeserialize();

        // Manually build tree: [1,2,3,null,null,4,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // 🔁 Serialize
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized); // Output: 1,2,#,#,3,4,#,#,5,#,#,

        // 🔁 Deserialize
        TreeNode deserializedRoot = codec.deserialize(serialized);
        System.out.println("Level Order of Deserialized Tree: " + levelOrder(deserializedRoot)); // Output: [[1], [2, 3], [4, 5]]
    }
}
