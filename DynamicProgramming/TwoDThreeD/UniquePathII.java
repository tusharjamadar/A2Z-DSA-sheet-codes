package DynamicProgramming.TwoDThreeD;
import java.util.*;

// ✅ LeetCode 63: Unique Paths II
public class UniquePathII {

    /*
    ============================================================
    ✅ Problem Summary:
    - A robot starts at the top-left (0,0) of a grid.
    - It wants to reach the bottom-right (m-1, n-1).
    - It can only move RIGHT or DOWN at each step.
    - Some cells have obstacles (marked with 1).
    - Robot cannot pass through obstacles.
    - Return the total number of unique paths to reach destination.

    Constraints:
    - 1 <= m, n <= 100
    - Answer fits in int (<= 2 * 10^9)
    ============================================================
    */

    /*
    ============================================================
    ✅ Brute Force Approach (Recursion - Exponential)
    Intuition:
    - At each cell (row, col), the robot has two choices:
      1. Move Right
      2. Move Down
    - If an obstacle is found → stop exploring that path.
    - If destination is reached → count that as 1 path.
    - Explore all possibilities recursively.

    Algorithm Steps:
    1. Start at (0,0).
    2. If outside grid OR on obstacle → return 0.
    3. If reached (m-1, n-1) → return 1.
    4. Else: return sum of right-call + down-call.

    Time Complexity: O(2^(m+n)) (exponential)
    Space Complexity: O(m+n) (recursion depth)
    ============================================================
    */
    private int bruteForceHelper(int row, int col, int m, int n, int[][] grid) {
        if (row >= m || col >= n || grid[row][col] == 1) return 0;
        if (row == m - 1 && col == n - 1) return 1;

        // Try moving right and down
        int right = bruteForceHelper(row, col + 1, m, n, grid);
        int down = bruteForceHelper(row + 1, col, m, n, grid);

        return right + down;
    }

    public int uniquePathsBruteForce(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        return bruteForceHelper(0, 0, m, n, grid);
    }


    /*
    ============================================================
    ✅ Optimized Approach 1: Recursion + Memoization (Top-Down DP)
    Intuition:
    - Brute force recomputes the same subproblems multiple times.
    - Example: From (0,0), both right & down calls may again explore (1,1).
    - Use DP (cache) to store results of (row, col).

    Algorithm Steps:
    1. Create dp[m][n], initialize with -1.
    2. If dp[row][col] already computed, return it.
    3. Else compute using recursion & store in dp[row][col].

    Time Complexity: O(m*n)
    Space Complexity: O(m*n) + O(m+n) (stack depth)
    ============================================================
    */
    private int memoHelper(int row, int col, int m, int n, int[][] grid, int[][] dp) {
        if (row >= m || col >= n || grid[row][col] == 1) return 0;
        if (row == m - 1 && col == n - 1) return 1;
        if (dp[row][col] != -1) return dp[row][col];

        int right = memoHelper(row, col + 1, m, n, grid, dp);
        int down = memoHelper(row + 1, col, m, n, grid, dp);

        return dp[row][col] = right + down;
    }

    public int uniquePathsMemoization(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoHelper(0, 0, m, n, grid, dp);
    }


    /*
    ============================================================
    ✅ Optimized Approach 2: Tabulation (Bottom-Up DP)
    Intuition:
    - Instead of recursion, build the solution iteratively.
    - dp[row][col] = ways from top + ways from left
    - If obstacle → dp[row][col] = 0

    Algorithm Steps:
    1. Create dp[m][n].
    2. Start filling from (0,0).
    3. For each cell:
       - If obstacle → 0
       - If start → 1
       - Else dp[row][col] = dp[row-1][col] + dp[row][col-1]
    4. Answer = dp[m-1][n-1]

    Time Complexity: O(m*n)
    Space Complexity: O(m*n)
    ============================================================
    */
    public int uniquePathsTabulation(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    dp[row][col] = 0; // obstacle
                } else if (row == 0 && col == 0) {
                    dp[row][col] = 1; // start
                } else {
                    int fromTop = (row > 0) ? dp[row - 1][col] : 0;
                    int fromLeft = (col > 0) ? dp[row][col - 1] : 0;
                    dp[row][col] = fromTop + fromLeft;
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /*
    ============================================================
    ✅ Optimized Approach 3: Space Optimized DP
    Intuition:
    - Each dp[row][col] only depends on current row & previous row.
    - So, we don’t need entire 2D dp.
    - Use 1D dp of size n.

    Algorithm Steps:
    1. Create dp[n], all zeros.
    2. Iterate row by row.
    3. Update dp[col] = (dp[col] from top) + (dp[col-1] from left)
       - Handle obstacles.
    4. Answer = dp[n-1].

    Time Complexity: O(m*n)
    Space Complexity: O(n)
    ============================================================
    */
    public int uniquePathsSpaceOptimized(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    dp[col] = 0; // obstacle
                } else if (row == 0 && col == 0) {
                    dp[col] = 1; // start cell
                } else {
                    int fromLeft = (col > 0) ? dp[col - 1] : 0;
                    dp[col] += fromLeft;
                }
            }
        }
        return dp[n - 1];
    }

    /*
    ============================================================
    ✅ Dry Run Example
    Input: grid = [[0,0,0],
                   [0,1,0],
                   [0,0,0]]

    Tabulation dp table:

    Step 1: Initialize start
    dp[0][0] = 1

    Step 2: Fill row by row
    [1,1,1]
    [1,0,1]
    [1,1,2]

    Answer = dp[2][2] = 2

    Paths:
    - Right → Right → Down → Down
    - Down → Down → Right → Right
    ============================================================
    */

    // ===========================================================
    // ✅ Main function for testing all approaches
    // ===========================================================
    public static void main(String[] args) {
        UniquePathII solver = new UniquePathII();

        int[][] grid1 = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] grid2 = {{0,1},{0,0}};

        System.out.println("===== Brute Force =====");
        System.out.println("Grid1: " + solver.uniquePathsBruteForce(grid1)); // 2
        System.out.println("Grid2: " + solver.uniquePathsBruteForce(grid2)); // 1

        System.out.println("===== Memoization =====");
        System.out.println("Grid1: " + solver.uniquePathsMemoization(grid1)); // 2
        System.out.println("Grid2: " + solver.uniquePathsMemoization(grid2)); // 1

        System.out.println("===== Tabulation =====");
        System.out.println("Grid1: " + solver.uniquePathsTabulation(grid1)); // 2
        System.out.println("Grid2: " + solver.uniquePathsTabulation(grid2)); // 1

        System.out.println("===== Space Optimized =====");
        System.out.println("Grid1: " + solver.uniquePathsSpaceOptimized(grid1)); // 2
        System.out.println("Grid2: " + solver.uniquePathsSpaceOptimized(grid2)); // 1
    }
}
