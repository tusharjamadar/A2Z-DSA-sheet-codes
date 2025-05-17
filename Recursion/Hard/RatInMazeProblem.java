package Hard;

import java.util.*;

// Problem: Rat in a Maze - I
// Difficulty: Medium

/*
Problem Details:
You are given a n x n grid (matrix) where each cell is either 0 or 1:
- 1: open cell (rat can move)
- 0: blocked cell (rat cannot move)

Start at (0,0) and reach (n-1,n-1) by moving in four possible directions:
'U' = up, 'D' = down, 'L' = left, 'R' = right

Goal: Find all paths from start to destination that are valid (no revisiting cells).
Return the paths in lexicographical (sorted) order.
If no path exists, return an empty list.
*/

/*
Intuition:
This is a classical backtracking problem where we recursively explore all
possible directions the rat can move from each cell. We mark a cell as
visited before recursive calls and backtrack by unmarking it afterward.
*/

/*
Algorithm:
1. Check if the start and destination cells are not blocked. If blocked, return empty list.
2. Use DFS (depth-first search) to explore each direction:
   - Mark the current cell as visited (maze[i][j] = 0).
   - Recurse in 4 directions (D, L, R, U).
   - After exploring, unmark the current cell (backtrack).
3. Add the path to the result list when the destination (n-1, n-1) is reached.
4. Sort the result to get lexicographically smallest order.
*/

/*
Time Complexity:
- Worst Case: O(4^(n*n)) — at each cell we may explore 4 directions
- Practical input constraints (n <= 5) make this brute-force with pruning acceptable.
*/

class RatInMazeProblem {
    public ArrayList<String> ratInMaze(int[][] maze) {
        ArrayList<String> res = new ArrayList<>();
        int n = maze.length;

        // Edge case: If start or destination is blocked, return empty list
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) return res;

        StringBuilder path = new StringBuilder();

        // Direction arrays: D, L, R, U
        int[] di = {1, 0, 0, -1};     // row change
        int[] dj = {0, -1, 1, 0};     // column change
        char[] dir = {'D', 'L', 'R', 'U'}; // direction characters

        // Start DFS from (0, 0)
        dfs(0, 0, maze, path, res, di, dj, dir);

        // Sort result in lexicographical order
        Collections.sort(res);
        return res;
    }

    private void dfs(int i, int j, int[][] maze, StringBuilder path,
                     ArrayList<String> res, int[] di, int[] dj, char[] dir) {

        int n = maze.length;

        // If destination reached, add current path to result
        if (i == n - 1 && j == n - 1) {
            res.add(path.toString());
            return;
        }

        // Mark current cell as visited
        maze[i][j] = 0;

        // Explore all 4 directions
        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            // Check if new cell is within bounds and not blocked
            if (ni >= 0 && nj >= 0 && ni < n && nj < n && maze[ni][nj] == 1) {
                path.append(dir[k]);                      // Add direction to path
                dfs(ni, nj, maze, path, res, di, dj, dir); // Recurse
                path.deleteCharAt(path.length() - 1);      // Backtrack
            }
        }

        // Unmark current cell (backtracking)
        maze[i][j] = 1;
    }

     // -----------------------------------------------------------------
    // Main method for quick testing
    // -----------------------------------------------------------------
    public static void main(String[] args) {
        RatInMazeProblem sol = new RatInMazeProblem();

        int[][] maze1 = {
            {1,0,0,0},
            {1,1,0,1},
            {1,1,0,0},
            {0,1,1,1}
        };
        System.out.println("Paths for maze1: " + sol.ratInMaze(maze1));

        int[][] maze2 = {
            {1,0},
            {1,0}
        };
        System.out.println("Paths for maze2: " + sol.ratInMaze(maze2));

        int[][] maze3 = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };
        System.out.println("Paths for maze3: " + sol.ratInMaze(maze3));
    }
}
