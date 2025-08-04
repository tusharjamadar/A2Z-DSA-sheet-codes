// ✅ Problem Summary:
// Implement Disjoint Set Union (DSU) using Union by Size + Path Compression.
// DSU is used to manage dynamic connectivity among elements, commonly used in Kruskal’s MST, cycle detection, etc.

// ✅ Brute Force (Explanation Only):
// For each union, just connect v under u, no optimizations. Find operations will be O(N) in worst case (deep trees).

// ✅ Optimized Approach using Union by Size + Path Compression:
// - Each node keeps a parent and the size of the tree it belongs to.
// - Smaller tree is always merged into larger tree -> keeps height small.
// - During find, we compress the path so that all nodes point directly to the root.

package Graph.DisjointSet;

import java.util.*;

public class DisjointSetBySize {

    // ✅ Stores the size of each disjoint set rooted at a node
    List<Integer> size = new ArrayList<>();

    // ✅ Stores the parent of each node
    List<Integer> parent = new ArrayList<>();

    // ✅ Constructor: Initializes DSU with 'n' elements
    public DisjointSetBySize(int n){
        for(int i = 0; i <= n; i++){
            size.add(1);         // Initially, every node is in its own set of size 1
            parent.add(i);       // Initially, every node is its own parent
        }
    }

    // ✅ Find with Path Compression
    public int findUpar(int node){
        // Base case: node is its own parent
        if(node == parent.get(node)){
            return node;
        }

        // Recursively compress the path
        int ulp = findUpar(parent.get(node));  // Find ultimate parent
        parent.set(node, ulp);                // Set parent directly to ultimate parent
        return parent.get(node);
    }

    // ✅ Union by Size: Attach smaller set under the larger one
    public void unionBySize(int u, int v){
        int ulp_u = findUpar(u);  // Ultimate parent of u
        int ulp_v = findUpar(v);  // Ultimate parent of v

        // If already in the same set, do nothing
        if(ulp_u == ulp_v) return;

        // Attach smaller set under larger one
        if(size.get(ulp_u) < size.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }

    // ✅ main() for Testing + Dry Run
    public static void main(String[] args) {
        // Create DSU for 1 to 7
        DisjointSetBySize ds = new DisjointSetBySize(7);

        // ✅ Dry Run Setup:
        // Initial unions:
        ds.unionBySize(1, 2);  // 1 - 2
        ds.unionBySize(2, 3);  // 1 - 2 - 3
        ds.unionBySize(4, 5);  // 4 - 5
        ds.unionBySize(6, 7);  // 6 - 7
        ds.unionBySize(5, 6);  // 4 - 5 - 6 - 7

        // Now, sets are:
        // Set1: {1, 2, 3}
        // Set2: {4, 5, 6, 7}

        // ✅ Query 1: Are 3 and 7 in the same set?
        if(ds.findUpar(3) == ds.findUpar(7)){
            System.out.println("Same"); // ❌ Not expected yet
        } else {
            System.out.println("Not Same"); // ✅ Expected output
        }

        // ✅ Connect the two sets
        ds.unionBySize(3, 7);

        // ✅ Query 2: Are 3 and 7 in the same set now?
        if(ds.findUpar(3) == ds.findUpar(7)){
            System.out.println("Same"); // ✅ Expected output
        } else {
            System.out.println("Not Same");
        }
    }
}

/*

✅ Time Complexity:
- findUpar(x): O(α(N)) ≈ constant in practice due to path compression.
- unionBySize(u, v): O(α(N)) due to find operations.

✅ Space Complexity:
- O(N) for maintaining parent and size arrays.

✅ Dry Run Example:
Input:
    union(1,2), union(2,3), union(4,5), union(6,7), union(5,6), find(3), find(7), union(3,7), find(3), find(7)

Initial:
    parent = [0,1,2,3,4,5,6,7]
    size   = [0,1,1,1,1,1,1,1]

After unions:
    {1,2,3} -> root 1
    {4,5,6,7} -> root 4 or 5 depending on size updates

After find(3) == find(7) → Not Same
After union(3,7) → Merge the two sets
After find(3) == find(7) → Same

*/
