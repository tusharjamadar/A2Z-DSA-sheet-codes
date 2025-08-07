package Graph.Hard;
import java.util.*;

// ✅ Class Solution containing method to find articulation points using Tarjan's Algorithm
class ArticulationPoint {
    private int timer = 1;  // Global timer for tracking discovery time

    /*
     * ✅ DFS helper function to perform Tarjan's Algorithm
     * 
     * Parameters:
     * - node: current node in DFS
     * - parent: parent of current node
     * - adj: adjacency list of graph
     * - vis: visited array
     * - timeInsertion: discovery time of nodes
     * - lowestTime: lowest discovery time reachable from the subtree
     * - mark: array to mark articulation points
     */
    private void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj,
                     int[] vis, int[] timeInsertion, int[] lowestTime, int[] mark) {

        vis[node] = 1;
        timeInsertion[node] = lowestTime[node] = timer++;
        int child = 0;  // To track child count for root

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue; // Ignore edge to parent

            if (vis[neighbor] == 0) {
                dfs(neighbor, node, adj, vis, timeInsertion, lowestTime, mark);
                lowestTime[node] = Math.min(lowestTime[node], lowestTime[neighbor]);

                // ✅ Articulation Point Condition
                if (lowestTime[neighbor] >= timeInsertion[node] && parent != -1) {
                    mark[node] = 1;
                }

                child++;
            } else {
                // Back edge case
                lowestTime[node] = Math.min(lowestTime[node], timeInsertion[neighbor]);
            }
        }

        // ✅ Special case: if root node has more than 1 child
        if (parent == -1 && child > 1) {
            mark[node] = 1;
        }
    }

    /*
     * ✅ Main method to find all articulation points in the graph
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        int[] timeInsertion = new int[V];
        int[] lowestTime = new int[V];
        int[] mark = new int[V];

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, adj, vis, timeInsertion, lowestTime, mark);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (mark[i] == 1) result.add(i);
        }

        if (result.isEmpty()) result.add(-1);
        return result;
    }

    /*
     * ✅ Main function to test the articulation point logic
     */
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Sample Graph:
        // 0 - 1 - 2
        //     |
        //     3 - 4

        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(1).add(3);
        adj.get(3).add(1);

        adj.get(3).add(4);
        adj.get(4).add(3);

        ArticulationPoint sol = new ArticulationPoint();
        ArrayList<Integer> articulationPoints = sol.articulationPoints(V, adj);

        System.out.println("Articulation Points: " + articulationPoints);
    }
}

/*
🧠 Intuition:
- An articulation point is a node that, if removed, increases the number of connected components.
- We use DFS traversal and track:
    - discovery time (`timeInsertion`)
    - lowest reachable discovery time (`lowestTime`)
- If a child cannot reach an ancestor of the current node without going through the current node, the current node is a critical articulation point.

🧪 Dry Run:
- Start DFS from node 0
- Visit 1 → 2 → backtrack
- Visit 3 → 4 → backtrack
- Node 1 is articulation point because removing it disconnects 0-2 from 3-4
- Node 3 is articulation point because removing it disconnects node 4

⏱️ Time Complexity: O(V + E)
📦 Space Complexity: O(V) for vis, timeInsertion, lowestTime, and mark arrays
*/
