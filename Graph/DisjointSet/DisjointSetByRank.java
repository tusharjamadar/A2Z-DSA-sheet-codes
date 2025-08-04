// ✅ Problem Summary:
// Implement Disjoint Set (Union-Find) data structure with union by rank optimization.
// Operations supported:
// 1. findUpar(x) → finds ultimate parent of node x.
// 2. unionByRank(u, v) → connects the sets of u and v by comparing rank.

// ✅ Intuition:
// In a Disjoint Set, each node points to its parent.
// Path compression + union by rank makes operations almost constant time.
// Union by rank connects smaller trees under larger trees to keep the structure flat.

// ✅ Time Complexity:
// - findUpar(x): O(α(N)) where α is inverse Ackermann function (nearly constant).
// - unionByRank(u, v): O(α(N)) as well.
// ✅ Space Complexity: O(N) for storing parent and rank arrays.

package Graph.DisjointSet;

import java.util.*;

public class DisjointSetByRank {
    // List to store rank (depth) of trees
    List<Integer> rank = new ArrayList<>();
    
    // List to store parent of each node
    List<Integer> parent = new ArrayList<>();

    // ✅ Constructor to initialize parent and rank lists
    public DisjointSetByRank(int n){
        for(int i = 0; i <= n; i++){
            rank.add(0);        // Initially all nodes have rank 0
            parent.add(i);      // Each node is its own parent (self set)
        }
    }

    // ✅ Find function with path compression
    public int findUpar(int node){
        // If node is its own parent, it's the root
        if(node == parent.get(node)){
            return node;
        }

        // Recursively find parent and apply path compression
        int ulp = findUpar(parent.get(node));
        parent.set(node, ulp);  // Path compression
        return parent.get(node);
    }

    // ✅ Union function with rank optimization
    public void unionByRank(int u, int v){
        // Find ultimate parents
        int ulp_u = findUpar(u);
        int ulp_v = findUpar(v);

        // If already in same set, do nothing
        if(ulp_u == ulp_v) return;

        // Attach smaller rank tree under bigger rank tree
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u, ulp_v);  // u under v
        }
        else if(rank.get(ulp_v) < rank.get(ulp_u)){
            parent.set(ulp_v, ulp_u);  // v under u
        }
        else {
            // Same rank → make one root and increase rank
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);
        }
    }

    // ✅ main method for testing the DSU implementation
    public static void main(String[] args) {
        // Create disjoint set with 7 elements
        DisjointSetByRank ds = new DisjointSetByRank(7);

        // Perform unions
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        // ✅ Dry Run Example:
        // At this point:
        // - 1,2,3 are in one set
        // - 4,5,6,7 are in another set

        if(ds.findUpar(3) == ds.findUpar(7)){
            System.out.println("Same");   // Expected: Not Same
        }else{
            System.out.println("Not Same");
        }

        // Union the two sets
        ds.unionByRank(3, 7);

        if(ds.findUpar(3) == ds.findUpar(7)){
            System.out.println("Same");   // Expected: Same
        }else{
            System.out.println("Not Same");
        }
    }
}
