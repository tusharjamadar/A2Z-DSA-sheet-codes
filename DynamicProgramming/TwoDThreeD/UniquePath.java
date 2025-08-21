package DynamicProgramming.TwoDThreeD;
import java.util.*;

public class UniquePath {

    /*
     * ✅ Problem Summary:
     * We are given a grid of size m x n. 
     * A robot starts at (0,0) → top-left and wants to reach (m-1, n-1) → bottom-right.
     * Robot can only move RIGHT or DOWN.
     * Task: Count the total number of unique paths.
     * 
     * Example:
     * m = 3, n = 2 → Output = 3
     * Paths: RDD, DRD, DDR
     */

    // -------------------------------------------------------------------------
    // 1️⃣ Recursive Approach (Brute Force)
    // -------------------------------------------------------------------------
    /*
     * 💡 Intuition:
     * - From each cell (row, col), we have 2 choices:
     *   1. Move RIGHT → (row, col+1)
     *   2. Move DOWN → (row+1, col)
     * - Base Cases:
     *   → If we go out of bounds, return 0.
     *   → If we reach bottom-right, return 1.
     * - The answer = paths from RIGHT + paths from DOWN.
     * 
     * 🔁 Steps:
     * 1. Start recursion from (0,0).
     * 2. At each step, branch into RIGHT and DOWN.
     * 3. Sum up valid paths.
     * 
     * ⏱️ Time Complexity: O(2^(m+n)) → Exponential (TLE for large inputs)
     * ⏳ Space Complexity: O(m+n) (recursion stack)
     */
    private int recursionHelper(int row, int col, int m, int n) {
        if (row >= m || col >= n) return 0; // out of bounds
        if (row == m - 1 && col == n - 1) return 1; // reached target

        // explore right & down
        return recursionHelper(row, col + 1, m, n) + recursionHelper(row + 1, col, m, n);
    }

    public int uniquePathsRecursion(int m, int n) {
        return recursionHelper(0, 0, m, n);
    }

    // -------------------------------------------------------------------------
    // 2️⃣ Memoization (Top-Down DP)
    // -------------------------------------------------------------------------
    /*
     * 💡 Intuition:
     * - Recursion has overlapping subproblems (same cell visited multiple times).
     * - Use DP cache (memo) to store results of subproblems.
     * 
     * 🔁 Steps:
     * 1. Create dp[m][n], initialized with -1.
     * 2. If result for (row, col) already computed, return it.
     * 3. Otherwise, compute paths and store in dp[row][col].
     * 
     * ⏱️ Time Complexity: O(m*n)
     * ⏳ Space Complexity: O(m*n) + O(m+n) recursion stack
     */
    private int memoHelper(int row, int col, int m, int n, int[][] dp) {
        if (row >= m || col >= n) return 0;
        if (row == m - 1 && col == n - 1) return 1;
        if (dp[row][col] != -1) return dp[row][col];

        dp[row][col] = memoHelper(row, col + 1, m, n, dp) + memoHelper(row + 1, col, m, n, dp);
        return dp[row][col];
    }

    public int uniquePathsMemo(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoHelper(0, 0, m, n, dp);
    }

    // -------------------------------------------------------------------------
    // 3️⃣ Tabulation (Bottom-Up DP)
    // -------------------------------------------------------------------------
    /*
     * 💡 Intuition:
     * - Fill a DP table where dp[row][col] = number of ways to reach (row, col).
     * - Base case:
     *   - First row = 1 (only moves RIGHT possible).
     *   - First column = 1 (only moves DOWN possible).
     * - Transition:
     *   dp[row][col] = dp[row-1][col] + dp[row][col-1]
     * 
     * 🔁 Steps:
     * 1. Initialize dp[0][j] = 1 and dp[i][0] = 1.
     * 2. Fill table row by row, col by col.
     * 3. Return dp[m-1][n-1].
     * 
     * ⏱️ Time Complexity: O(m*n)
     * ⏳ Space Complexity: O(m*n)
     */
    public int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];

        // Fill first row
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill first column
        for (int i = 0; i < m; i++) dp[i][0] = 1;

        // Fill rest of the table
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    // -------------------------------------------------------------------------
    // 4️⃣ Space Optimized DP
    // -------------------------------------------------------------------------
    /*
     * 💡 Intuition:
     * - For dp[row][col], we only need the current row & previous row.
     * - Instead of 2D dp, we use a 1D array.
     * - dp[col] = dp[col] + dp[col-1]
     * 
     * 🔁 Steps:
     * 1. Initialize dp[] with 1s (first row).
     * 2. For each row, update dp[col] = dp[col] + dp[col-1].
     * 3. Return dp[n-1].
     * 
     * ⏱️ Time Complexity: O(m*n)
     * ⏳ Space Complexity: O(n)
     */
    public int uniquePathsSpaceOpt(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[col] += dp[col - 1];
            }
        }

        return dp[n - 1];
    }

    // -------------------------------------------------------------------------
    // 🏁 Main function to test all approaches
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        UniquePath sol = new UniquePath();

        int m = 3, n = 7;

        System.out.println("Unique Paths using Recursion: " + sol.uniquePathsRecursion(m, n));
        System.out.println("Unique Paths using Memoization: " + sol.uniquePathsMemo(m, n));
        System.out.println("Unique Paths using Tabulation: " + sol.uniquePathsTabulation(m, n));
        System.out.println("Unique Paths using Space Optimization: " + sol.uniquePathsSpaceOpt(m, n));
    }
}
