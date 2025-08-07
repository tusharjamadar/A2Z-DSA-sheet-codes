package Graph.DisjointSet;
import java.util.*;

/*
 * ✅ Problem: LeetCode 947 - Most Stones Removed with Same Row or Column
 * 
 * You are given `n` stones placed on a 2D plane at coordinates (x, y).
 * A stone can be removed if there is another stone in the same row or column.
 * Return the maximum number of stones that can be removed while ensuring at least one stone remains in each connected group.
 *
 * --------------------------------------------
 * ✅ Intuition:
 * A stone can only be removed if it shares row or column with another.
 * This means stones form connected groups (components) via rows and columns.
 * The minimum number of stones that must remain is the number of connected components.
 * So, the maximum number of stones that can be removed = total stones - number of components.
 *
 * --------------------------------------------
 * ✅ Brute Force (Not implemented here):
 * - Build a graph by connecting stones sharing row/column.
 * - Use DFS/BFS to count number of connected components.
 * - Return stones.length - components.
 * 
 * ❌ Time Complexity: O(N^2)
 *
 * --------------------------------------------
 * ✅ Optimized Approach (Implemented below):
 * - Use Disjoint Set (Union-Find) to group stones by row and column.
 * - Map column index as (col + maxRow + 1) to avoid conflict with row indices.
 * - For each stone, union(row, col + offset)
 * - Count number of unique parent nodes (i.e., number of components).
 * - Answer = total stones - components
 *
 * ✅ Dry Run:
 * Input: [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * After processing unions: All belong to one group.
 * Components = 1
 * Answer = 6 - 1 = 5
 *
 * --------------------------------------------
 * ✅ Time Complexity:
 * - O(N * α(N)) where α is inverse Ackermann, almost constant
 *
 * ✅ Space Complexity:
 * - O(R + C) where R = max row, C = max col
 */

// Disjoint Set (Union-Find) with union by size
class DisjointSet {
    int[] size, parent;

    public DisjointSet(int n) {
        size = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    // Find the ultimate parent with path compression
    int findUpar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUpar(parent[node]);
    }

    // Union by size
    void unionBySize(int u, int v) {
        int ulp_u = findUpar(u);
        int ulp_v = findUpar(v);

        if (ulp_u == ulp_v)
            return;

        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

public class MostStonesRemovedwithSameRoworColumn {

    public int removeStones(int[][] stones) {
        int maxRow = 0, maxCol = 0;

        // Step 1: Find max row and column index
        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        // Step 2: Create Disjoint Set of total size (rows + cols + 1)
        // "+1" ensures no overlap between row and col index in union
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);

        Set<Integer> uniqueNodes = new HashSet<>();

        // Step 3: Union each stone's row and (col + offset)
        for (int[] stone : stones) {
            int rowNode = stone[0];
            int colNode = stone[1] + maxRow + 1; // shift col index to avoid clash

            ds.unionBySize(rowNode, colNode);

            uniqueNodes.add(rowNode);
            uniqueNodes.add(colNode);
        }

        // Step 4: Count number of connected components
        int components = 0;
        for (int node : uniqueNodes) {
            if (ds.findUpar(node) == node) {
                components++;
            }
        }

        // Step 5: Answer = total stones - components
        return stones.length - components;
    }

    // 🔍 Main method to test the solution
    public static void main(String[] args) {
        MostStonesRemovedwithSameRoworColumn sol = new MostStonesRemovedwithSameRoworColumn();

        int[][] test1 = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println("Output: " + sol.removeStones(test1)); // Expected: 5

        int[][] test2 = {{0,0},{0,2},{1,1},{2,0},{2,2}};
        System.out.println("Output: " + sol.removeStones(test2)); // Expected: 3

        int[][] test3 = {{0,0}};
        System.out.println("Output: " + sol.removeStones(test3)); // Expected: 0
    }
}
