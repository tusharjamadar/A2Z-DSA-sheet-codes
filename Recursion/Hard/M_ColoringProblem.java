package Hard;
/*
Problem Description:
You are given an undirected graph with V vertices and E edges. Determine whether it is possible to color the graph using at most 'm' colors 
such that no two adjacent vertices share the same color.

Intuition:
Use a backtracking approach to try all possible color assignments for each vertex.
If at any point, no valid color can be assigned to a vertex, backtrack to explore other options.
If a valid coloring is found for all vertices, return true.

Algorithm:
1. Initialize an array 'nodeColor' to store assigned colors for each vertex.
2. Use a recursive function 'backtrack' to try coloring each node from 0 to V-1.
3. For each node, try assigning colors from 1 to m.
4. Before assigning a color, check if it is safe using 'isSafe' function (i.e., adjacent vertices should not have the same color).
5. If all nodes are colored successfully, return true. Otherwise, backtrack.

Time Complexity: O(m^V), where m is the number of colors and V is the number of vertices.
Space Complexity: O(V), for the nodeColor array.
*/

class M_ColoringProblem {

    // Function to determine if the graph can be colored with at most m colors
    boolean graphColoring(int v, int[][] edges, int m) {
        // Array to store assigned color for each vertex; 0 means no color assigned
        int[] nodeColor = new int[v];

        // Start backtracking from the first vertex
        return backtrack(0, v, edges, m, nodeColor);
    }

    // Recursive backtracking function to assign colors to each node
    boolean backtrack(int node, int v, int[][] edges, int m, int[] nodeColor) {
        // If all nodes are colored, return true
        if (node == v) return true;

        // Try assigning each color from 1 to m
        for (int color = 1; color <= m; color++) {
            // Check if assigning this color is safe
            if (isSafe(node, edges, color, nodeColor)) {
                // Assign the color to current node
                nodeColor[node] = color;

                // Recursively color the next node
                if (backtrack(node + 1, v, edges, m, nodeColor)) return true;

                // Backtrack if coloring fails
                nodeColor[node] = 0;
            }
        }

        // Return false if no valid color assignment found for this node
        return false;
    }

    // Function to check if the current color assignment is valid
    boolean isSafe(int node, int[][] edges, int color, int[] nodeColor) {
        // Iterate through all edges to check adjacent vertices
        for (int[] edge : edges) {
            // If the current node is connected to another node with the same color, return false
            if (edge[0] == node && nodeColor[edge[1]] == color) return false;
            if (edge[1] == node && nodeColor[edge[0]] == color) return false;
        }
        // Return true if no conflict found
        return true;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        M_ColoringProblem sol = new M_ColoringProblem();

        // Test Case 1
        int V1 = 4;
        int[][] edges1 = {{0, 1}, {1, 3}, {2, 3}, {3, 0}, {0, 2}};
        int m1 = 3;
        System.out.println("Test Case 1 Output: " + sol.graphColoring(V1, edges1, m1)); // Expected: true

        // Test Case 2
        int V2 = 3;
        int[][] edges2 = {{0, 1}, {1, 2}, {0, 2}};
        int m2 = 2;
        System.out.println("Test Case 2 Output: " + sol.graphColoring(V2, edges2, m2)); // Expected: false
    }
}
