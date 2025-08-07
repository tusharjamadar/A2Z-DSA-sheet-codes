package Graph.DisjointSet;
/*
🔶✅ Problem: 1319. Number of Operations to Make Network Connected

You are given `n` computers labeled from 0 to n-1 and a list of `connections`,
where each `connection[i] = [a, b]` represents a direct cable between computers a and b.

You can extract a cable and use it to connect two disconnected computers.

Return the **minimum number of operations** required to make all computers connected.
If it's not possible, return `-1`.

----------------------------------------------
🔶 Brute Force (Explanation Only):

- Try to simulate the connection of every computer using BFS/DFS.
- Track connected components manually.
- Count extra edges to reconnect components.
- Time complexity will be higher due to repeated traversals.

----------------------------------------------
🔶 Optimized Approach: Using Disjoint Set Union (DSU)

💡 Intuition:
- There are `n` nodes, and to connect them all, you need at least `n-1` cables.
- Any cable beyond that is "extra" and can be reused.
- Use DSU to find how many **connected components** exist.
- You’ll need (components - 1) operations to connect them.

✅ Steps:
1. If number of connections < n - 1 ⇒ return -1 (not enough cables).
2. Use Disjoint Set to group all connected nodes.
3. Count how many unique components (root parents) exist.
4. Answer = components - 1.

----------------------------------------------
*/

class DisjointSet {
    int[] size, parent;

    // ✅ Constructor
    public DisjointSet(int n) {
        size = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            size[i] = 1;     // Initially, each node is a separate component of size 1
            parent[i] = i;   // Each node is its own parent
        }
    }

    // ✅ Find with Path Compression
    int findUpar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUpar(parent[node]);
    }

    // ✅ Union by Size
    void unionBySize(int u, int v) {
        int ulp_u = findUpar(u);
        int ulp_v = findUpar(v);

        if (ulp_u == ulp_v) return;

        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

public class NumberOfOperationsToMakeNetworkConnected {

    // ✅ Function to find minimum operations to connect all computers
    public int makeConnected(int n, int[][] connections) {
        // ⚠️ If we don’t have at least n-1 cables, it's impossible
        if (connections.length < n - 1) return -1;

        DisjointSet ds = new DisjointSet(n);

        // 🔁 Union all given connections
        for (int[] conn : connections) {
            int u = conn[0], v = conn[1];
            ds.unionBySize(u, v);
        }

        // 🔍 Count connected components
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (ds.findUpar(i) == i)
                components++;
        }

        // 📌 Need (components - 1) operations to connect all
        return components - 1;
    }

    // ✅ Dry Run Example:
    public static void main(String[] args) {
        NumberOfOperationsToMakeNetworkConnected sol = new NumberOfOperationsToMakeNetworkConnected();

        int[][] con1 = {{0,1},{0,2},{1,2}};
        System.out.println(sol.makeConnected(4, con1)); // 🔸 Output: 1

        int[][] con2 = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println(sol.makeConnected(6, con2)); // 🔸 Output: 2

        int[][] con3 = {{0,1},{0,2},{0,3},{1,2}};
        System.out.println(sol.makeConnected(6, con3)); // 🔸 Output: -1 (not enough edges)
    }
}

/*
----------------------------------------------
🕒 Time Complexity:
- Union operations: O(α(N)) ≈ O(1) per edge, total O(M), M = number of connections
- Find operations for each node = O(N * α(N))
- Overall: O(N + M), where N = nodes, M = connections

🧠 Space Complexity:
- O(N) for parent[] and size[] arrays
----------------------------------------------
*/
