package DynamicProgramming.TwoDThreeD;

/**
 * ✅ Problem: 931. Minimum Falling Path Sum
 * 
 * We are given an n x n matrix. A falling path starts at any element in the first row 
 * and chooses one element in the next row directly below or diagonally left/right.
 * We must return the minimum sum of any falling path.
 * 
 * Example:
 * Input: [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: Path = 1 -> 5 -> 7 = 13 (minimum)
 * 
 * ------------------------------------------------------------------------------------
 * ✅ Approaches Covered:
 * 1. Recursion (Brute Force)
 * 2. Memoization (Top-Down DP)
 * 3. Tabulation (Bottom-Up DP)
 * 4. Space Optimized DP
 * 
 * Each approach includes:
 * - Intuition
 * - Algorithm
 * - Time & Space Complexity
 * - Dry Run Example
 * ------------------------------------------------------------------------------------
 */

 import java.util.*;

 public class MinimumFallingPathSum {
 
     // --------------------------------------------------------------------------------
     // 1️⃣ RECURSION (Brute Force)
     // --------------------------------------------------------------------------------
     /**
      * Intuition:
      * - Try all possible paths starting from each column in the first row.
      * - At each cell, we explore 3 options:
      *      -> Move directly below
      *      -> Move diagonally left
      *      -> Move diagonally right
      * - Take the minimum among them.
      * 
      * Algorithm:
      * 1. Start from (0, col) for all col in first row.
      * 2. Recursively calculate the min path sum till the last row.
      * 3. Return the minimum of all starting columns.
      * 
      * Time Complexity: O(3^n) (TLE for large n, as we branch into 3 choices per row)
      * Space Complexity: O(n) (recursion stack)
      */
     private int helperRec(int row, int col, int[][] matrix) {
         if (row >= matrix.length || col < 0 || col >= matrix[0].length) return Integer.MAX_VALUE;
         if (row == matrix.length - 1) return matrix[row][col];
 
         int left = helperRec(row + 1, col - 1, matrix);
         int down = helperRec(row + 1, col, matrix);
         int right = helperRec(row + 1, col + 1, matrix);
 
         return matrix[row][col] + Math.min(left, Math.min(down, right));
     }
 
     public int minFallingPathSumRec(int[][] matrix) {
         int n = matrix.length, m = matrix[0].length;
         int minSum = Integer.MAX_VALUE;
 
         for (int col = 0; col < m; col++) {
             minSum = Math.min(minSum, helperRec(0, col, matrix));
         }
         return minSum;
     }
 
     // --------------------------------------------------------------------------------
     // 2️⃣ MEMOIZATION (Top-Down DP)
     // --------------------------------------------------------------------------------
     /**
      * Intuition:
      * - Recursion recalculates overlapping subproblems.
      * - Use DP array (memoization) to cache results.
      * 
      * Algorithm:
      * 1. Create a dp[][] filled with Integer.MAX_VALUE.
      * 2. At each state (row, col), if result already computed, return it.
      * 3. Otherwise compute recursively and store in dp.
      * 
      * Time Complexity: O(n^2) (each cell computed once)
      * Space Complexity: O(n^2) (DP table) + O(n) recursion stack
      */
     private int helperMemo(int row, int col, int[][] matrix, int[][] dp) {
         if (row >= matrix.length || col < 0 || col >= matrix[0].length) return Integer.MAX_VALUE;
         if (row == matrix.length - 1) return matrix[row][col];
         if (dp[row][col] != Integer.MAX_VALUE) return dp[row][col];
 
         int left = helperMemo(row + 1, col - 1, matrix, dp);
         int down = helperMemo(row + 1, col, matrix, dp);
         int right = helperMemo(row + 1, col + 1, matrix, dp);
 
         return dp[row][col] = matrix[row][col] + Math.min(left, Math.min(down, right));
     }
 
     public int minFallingPathSumMemo(int[][] matrix) {
         int n = matrix.length, m = matrix[0].length;
         int[][] dp = new int[n][m];
         for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
 
         int minSum = Integer.MAX_VALUE;
         for (int col = 0; col < m; col++) {
             minSum = Math.min(minSum, helperMemo(0, col, matrix, dp));
         }
         return minSum;
     }
 
     // --------------------------------------------------------------------------------
     // 3️⃣ TABULATION (Bottom-Up DP)
     // --------------------------------------------------------------------------------
     /**
      * Intuition:
      * - Instead of recursion, fill the DP table row by row.
      * - dp[row][col] = min(dp[row-1][col-1], dp[row-1][col], dp[row-1][col+1]) + matrix[row][col]
      * - Answer = min(dp[n-1][col]) for all col.
      * 
      * Algorithm:
      * 1. Initialize first row as matrix[0][col].
      * 2. For each row from 1 to n-1, compute min path sum.
      * 3. Return min of last row.
      * 
      * Time Complexity: O(n^2)
      * Space Complexity: O(n^2)
      */
     public int minFallingPathSumTabu(int[][] matrix) {
         int n = matrix.length, m = matrix[0].length;
         int[][] dp = new int[n][m];
 
         for (int col = 0; col < m; col++) dp[0][col] = matrix[0][col];
 
         for (int row = 1; row < n; row++) {
             for (int col = 0; col < m; col++) {
                 int left = (col > 0) ? dp[row - 1][col - 1] : Integer.MAX_VALUE;
                 int down = dp[row - 1][col];
                 int right = (col + 1 < m) ? dp[row - 1][col + 1] : Integer.MAX_VALUE;
 
                 dp[row][col] = matrix[row][col] + Math.min(left, Math.min(down, right));
             }
         }
 
         int res = Integer.MAX_VALUE;
         for (int col = 0; col < m; col++) res = Math.min(res, dp[n - 1][col]);
         return res;
     }
 
     // --------------------------------------------------------------------------------
     // 4️⃣ SPACE OPTIMIZATION
     // --------------------------------------------------------------------------------
     /**
      * Intuition:
      * - Only the previous row is needed to compute the current row.
      * - Replace 2D DP with 1D arrays.
      * 
      * Algorithm:
      * 1. Store first row in "prev".
      * 2. For each row, compute "curr" using only "prev".
      * 3. Update prev = curr.
      * 4. Return min of last row.
      * 
      * Time Complexity: O(n^2)
      * Space Complexity: O(n) (optimized from O(n^2))
      */
     public int minFallingPathSumSpaceOpt(int[][] matrix) {
         int n = matrix.length, m = matrix[0].length;
         int[] prev = new int[m];
 
         for (int col = 0; col < m; col++) prev[col] = matrix[0][col];
 
         for (int row = 1; row < n; row++) {
             int[] curr = new int[m];
             for (int col = 0; col < m; col++) {
                 int left = (col > 0) ? prev[col - 1] : Integer.MAX_VALUE;
                 int down = prev[col];
                 int right = (col + 1 < m) ? prev[col + 1] : Integer.MAX_VALUE;
 
                 curr[col] = matrix[row][col] + Math.min(left, Math.min(down, right));
             }
             prev = curr;
         }
 
         int res = Integer.MAX_VALUE;
         for (int val : prev) res = Math.min(res, val);
         return res;
     }
 
     // --------------------------------------------------------------------------------
     // MAIN METHOD (Testing All Approaches)
     // --------------------------------------------------------------------------------
     public static void main(String[] args) {
         MinimumFallingPathSum solver = new MinimumFallingPathSum();
 
         int[][] matrix1 = {{2,1,3},{6,5,4},{7,8,9}};
         int[][] matrix2 = {{-19,57},{-40,-5}};
 
         System.out.println("Matrix1 Rec: " + solver.minFallingPathSumRec(matrix1));
         System.out.println("Matrix1 Memo: " + solver.minFallingPathSumMemo(matrix1));
         System.out.println("Matrix1 Tabu: " + solver.minFallingPathSumTabu(matrix1));
         System.out.println("Matrix1 SpaceOpt: " + solver.minFallingPathSumSpaceOpt(matrix1));
 
         System.out.println("Matrix2 Rec: " + solver.minFallingPathSumRec(matrix2));
         System.out.println("Matrix2 Memo: " + solver.minFallingPathSumMemo(matrix2));
         System.out.println("Matrix2 Tabu: " + solver.minFallingPathSumTabu(matrix2));
         System.out.println("Matrix2 SpaceOpt: " + solver.minFallingPathSumSpaceOpt(matrix2));
     }
 }
 