package Graph.DisjointSet;

/*
 ✅ Problem: 827. Making A Large Island (Hard)
 ---------------------------------------------------
 You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 Return the size of the largest island after the change.

 An island is a group of 1s connected 4-directionally (up, down, left, right).

 🔹 Example:
 Input: grid = [[1,0],[0,1]]
 Output: 3
 Explanation: Change one 0 to 1 and connect two 1s, resulting in an island of size 3.

 🔹 Constraints:
 1 <= n <= 500
 grid[i][j] is either 0 or 1
*/

/*
 ✅ Brute Force Approach (Explanation Only)
 -----------------------------------------
 1. For each 0 cell in the grid:
     - Change it to 1.
     - Use DFS/BFS to compute the size of the connected island.
     - Restore it back to 0.
 2. Track the maximum island size.

 🔻 Time Complexity: O(N^4) in worst case → O(N^2) for each 0, and O(N^2) for each DFS.
 🔻 Not feasible for large grid sizes (N up to 500).
*/

/*
 ✅ Optimized Approach using Disjoint Set Union (Union-Find)
 ------------------------------------------------------------
 🔹 Step 1: Union all connected 1s into disjoint sets (components) and store their sizes.
 🔹 Step 2: For each 0 cell, consider converting it to 1 and connect it to all adjacent unique islands.
 🔹 Step 3: Keep track of the maximum size encountered.

 🔹 Time Complexity: O(N^2 * α(N)) ≈ O(N^2), where α(N) is inverse Ackermann (near constant)
 🔹 Space Complexity: O(N^2) for parent and size arrays
*/

import java.util.*;


class MakingALargeIsland {

    public class DisjointSet {
        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
    
        public DisjointSet(int n){
            for(int i = 0; i <= n; i++){
                size.add(1);         
                parent.add(i);       
            }
        }
    
        public int findUpar(int node){
            if(node == parent.get(node)) return node;
            int ulp = findUpar(parent.get(node));
            parent.set(node, ulp);  // path compression
            return parent.get(node);
        }
    
        public int getSize(int node){
            return size.get(node);
        }
    
        public void unionBySize(int u, int v){
            int ulp_u = findUpar(u);
            int ulp_v = findUpar(v);
    
            if(ulp_u == ulp_v) return;
    
            if(size.get(ulp_u) < size.get(ulp_v)){
                parent.set(ulp_u, ulp_v);
                size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
            } else {
                parent.set(ulp_v, ulp_u);
                size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
            }
        }
    }

    
    private boolean isValid(int row, int col, int n, int m){
        return (row >= 0 && row < n && col >= 0 && col < m);
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        DisjointSet ds = new DisjointSet(n * m);

        int[] dr = {-1, 0, 1, 0}; // direction row
        int[] dc = {0, 1, 0, -1}; // direction col

        // Step 1: Union all connected 1s
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    for(int k = 0; k < 4; k++){
                        int nrow = i + dr[k];
                        int ncol = j + dc[k];
                        if(isValid(nrow, ncol, n, m) && grid[nrow][ncol] == 1){
                            int nodeNo = i * m + j;
                            int adjNo = nrow * m + ncol;
                            ds.unionBySize(nodeNo, adjNo);
                        }
                    }
                }
            }
        }

        // Step 2: Try flipping each 0 and compute connected size
        int maxCount = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0){
                    Set<Integer> uniqueParents = new HashSet<>();
                    int currentSize = 1; // including this flipped 0

                    for(int k = 0; k < 4; k++){
                        int nrow = i + dr[k];
                        int ncol = j + dc[k];
                        if(isValid(nrow, ncol, n, m) && grid[nrow][ncol] == 1){
                            int nodeNo = nrow * m + ncol;
                            int parent = ds.findUpar(nodeNo);
                            if (!uniqueParents.contains(parent)) {
                                uniqueParents.add(parent);
                                currentSize += ds.getSize(parent);
                            }
                        }
                    }

                    maxCount = Math.max(maxCount, currentSize);
                }
            }
        }

        // Step 3: If no 0 was flipped, return max component size
        for (int i = 0; i < n * m; i++) {
            maxCount = Math.max(maxCount, ds.getSize(i));
        }

        return maxCount;
    }

    /*
     ✅ Dry Run Example:
     --------------------
     Input: grid = [[1, 0], [0, 1]]
     Index Mapping:
     0 1
     2 3

     Initially: Two separate 1s → (0) and (3)
     Flip grid[0][1] from 0 → 1
     It connects to node 0 and 3 → total size = 1 (flip) + 1 + 1 = 3

     Output: 3
    */

    // ✅ main method for testing
    public static void main(String[] args) {
        MakingALargeIsland sol = new MakingALargeIsland();

        int[][] grid1 = {{1,0},{0,1}};
        int[][] grid2 = {{1,1},{1,0}};
        int[][] grid3 = {{1,1},{1,1}};

        System.out.println("Output 1: " + sol.largestIsland(grid1)); // Expected: 3
        System.out.println("Output 2: " + sol.largestIsland(grid2)); // Expected: 4
        System.out.println("Output 3: " + sol.largestIsland(grid3)); // Expected: 4
    }
}

/*
 ✅ Time Complexity: O(N^2 * α(N)) ~ O(N^2)
    - One pass for union = O(N^2)
    - For each 0 cell, checking 4 directions = O(N^2)
 ✅ Space Complexity: O(N^2)
    - DSU arrays: parent and size
*/
