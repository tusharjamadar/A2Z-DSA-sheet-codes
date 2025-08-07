package Graph.Hard;

import java.util.*;

/*
✅ Problem Summary:
You're given `n` servers labeled from 0 to n-1 and a list of connections where connections[i] = [a, b] means there is a bidirectional connection between server a and b.

A connection is **critical** if removing it disconnects the network (some nodes can no longer reach others).

You need to return all such critical connections.

💡 Intuition:
Use Tarjan's Algorithm to find **Bridges** in a graph.
A bridge is an edge that, if removed, increases the number of connected components.

🔁 Brute Force (Not feasible for large input):
For each edge:
  - Remove it
  - Check if the graph is still connected using BFS/DFS

Time: O(E * (V + E)) → TLE for large graphs

✅ Optimized Approach (Tarjan's Algorithm):
Use DFS and track:
- `inTime[node]`: when the node was discovered
- `low[node]`: lowest time reachable from that node or its subtree

If `low[child] > inTime[node]`, then (node, child) is a bridge (critical connection)

Why? It means there's no back edge to ancestor of node from the subtree rooted at child.

⏱️ Time Complexity:
- O(V + E), where V = number of nodes, E = number of edges

📦 Space Complexity:
- O(V + E) for adjacency list and visited arrays

🧪 Dry Run Example:
n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]

Graph:
      0
     / \
    1---2
    |
    3

DFS traversal:
- Starts at 0, explores 1 and then 2
- 2 has back edge to 0 → not a bridge
- Then from 1 → 3 (no back edge) → bridge

Result = [[1,3]]
*/

public class BridgeInGraph {

    private int timer = 1;

    // DFS helper function to find bridges (critical connections)
    private void dfs(int node, int parent, List<List<Integer>> adj, int[] vis,
                     int[] timeInsertion, int[] lowestTime, List<List<Integer>> res) {

        vis[node] = 1;
        timeInsertion[node] = lowestTime[node] = timer++;  // Set discovery and low time

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue; // Don't revisit the parent

            if (vis[neighbor] == 0) {
                // DFS on unvisited neighbor
                dfs(neighbor, node, adj, vis, timeInsertion, lowestTime, res);

                // Update lowest time of current node
                lowestTime[node] = Math.min(lowestTime[node], lowestTime[neighbor]);

                // Check for bridge
                if (lowestTime[neighbor] > timeInsertion[node]) {
                    res.add(Arrays.asList(node, neighbor)); // or (neighbor, node)
                }
            } else {
                // Update low time on encountering a back edge
                lowestTime[node] = Math.min(lowestTime[node], timeInsertion[neighbor]);
            }
        }
    }

    // Main function to find all critical connections (bridges)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Step 1: Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> edge : connections) {
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        // Step 2: Initialize helper arrays
        int[] vis = new int[n];             // Visited array
        int[] timeInsertion = new int[n];   // Discovery time of node
        int[] lowestTime = new int[n];      // Lowest discovery time reachable
        List<List<Integer>> result = new ArrayList<>();

        // Step 3: Start DFS traversal from node 0 (guaranteed to be connected)
        dfs(0, -1, adj, vis, timeInsertion, lowestTime, result);

        return result;
    }

    // ✅ main() method to test the solution
    public static void main(String[] args) {
        BridgeInGraph sol = new BridgeInGraph();

        // Test case 1
        int n1 = 4;
        List<List<Integer>> connections1 = new ArrayList<>();
        connections1.add(Arrays.asList(0, 1));
        connections1.add(Arrays.asList(1, 2));
        connections1.add(Arrays.asList(2, 0));
        connections1.add(Arrays.asList(1, 3));

        System.out.println("Critical Connections for Test Case 1:");
        System.out.println(sol.criticalConnections(n1, connections1)); // Expected: [[1,3]]

        // Test case 2
        int n2 = 2;
        List<List<Integer>> connections2 = new ArrayList<>();
        connections2.add(Arrays.asList(0, 1));

        System.out.println("Critical Connections for Test Case 2:");
        System.out.println(sol.criticalConnections(n2, connections2)); // Expected: [[0,1]]
    }
}
