package DynamicProgramming.TwoDThreeD;

import java.util.*;

public class CherryPickupII {

    // ✅ Problem Summary:
    // We have a grid (rows x cols) with cherries.
    // Robot #1 starts at (0, 0) and Robot #2 at (0, cols-1).
    // Both robots move down row by row, and at each step can move to col-1, col, col+1.
    // If both land on same cell, cherries are counted only once.
    // Goal: Maximize total cherries collected when both reach bottom row.

    // ==============================================================
    // ✅ 1. Recursive Approach (Exponential Time)
    // ==============================================================
    // Intuition:
    // At each row, robot1 can move to 3 possible columns, robot2 also to 3.
    // Total 9 combinations for each step.
    // Try all paths recursively, and take max cherries collected.
    // Time Complexity: O(3^(2n)) -> Exponential
    // Space Complexity: O(n) recursion depth.
    // --------------------------------------------------------------
    private int helperRecursive(int row, int col1, int col2, int[][] grid) {
        int n = grid.length, m = grid[0].length;

        // Invalid position
        if (col1 < 0 || col1 >= m || col2 < 0 || col2 >= m) return (int)-1e8;

        // Base case: last row
        if (row == n - 1) {
            if (col1 == col2) return grid[row][col1];
            return grid[row][col1] + grid[row][col2];
        }

        int maxCherries = (int)-1e8;

        // Try all 9 possible moves
        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                int next = helperRecursive(row + 1, col1 + d1, col2 + d2, grid);
                if (col1 == col2) {
                    maxCherries = Math.max(maxCherries, grid[row][col1] + next);
                } else {
                    maxCherries = Math.max(maxCherries, grid[row][col1] + grid[row][col2] + next);
                }
            }
        }
        return maxCherries;
    }

    // ==============================================================
    // ✅ 2. Memoization (Top-Down DP)
    // ==============================================================
    // Same logic as recursion but cache results.
    // State = (row, col1, col2)
    // dp[row][col1][col2] = max cherries from this state.
    // Time Complexity: O(n * m * m * 9) ≈ O(n * m^2)
    // Space Complexity: O(n * m^2) for dp + O(n) recursion depth.
    // --------------------------------------------------------------
    private int helperMemo(int row, int col1, int col2, int[][] grid, int[][][] dp) {
        int n = grid.length, m = grid[0].length;

        if (col1 < 0 || col1 >= m || col2 < 0 || col2 >= m) return (int)-1e8;

        if (dp[row][col1][col2] != -1) return dp[row][col1][col2];

        if (row == n - 1) {
            if (col1 == col2) return grid[row][col1];
            return grid[row][col1] + grid[row][col2];
        }

        int maxCherries = (int)-1e8;
        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                int next = helperMemo(row + 1, col1 + d1, col2 + d2, grid, dp);
                if (col1 == col2) {
                    maxCherries = Math.max(maxCherries, grid[row][col1] + next);
                } else {
                    maxCherries = Math.max(maxCherries, grid[row][col1] + grid[row][col2] + next);
                }
            }
        }
        return dp[row][col1][col2] = maxCherries;
    }

    // ==============================================================
    // ✅ 3. Tabulation (Bottom-Up DP)
    // ==============================================================
    // Build dp[n][m][m] table.
    // Start from last row → move upwards.
    // Transition same as memoization.
    // Time Complexity: O(n * m^2 * 9)
    // Space Complexity: O(n * m^2)
    // --------------------------------------------------------------
    private int tabulation(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][][] dp = new int[n][m][m];

        // Base case: last row
        for (int c1 = 0; c1 < m; c1++) {
            for (int c2 = 0; c2 < m; c2++) {
                if (c1 == c2) dp[n-1][c1][c2] = grid[n-1][c1];
                else dp[n-1][c1][c2] = grid[n-1][c1] + grid[n-1][c2];
            }
        }

        // Fill table bottom-up
        for (int row = n-2; row >= 0; row--) {
            for (int c1 = 0; c1 < m; c1++) {
                for (int c2 = 0; c2 < m; c2++) {
                    int maxCherries = (int)-1e8;
                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {
                            int next;
                            if (c1+d1 < 0 || c1+d1 >= m || c2+d2 < 0 || c2+d2 >= m) {
                                next = (int)-1e8;
                            } else {
                                next = dp[row+1][c1+d1][c2+d2];
                            }
                            if (c1 == c2) {
                                maxCherries = Math.max(maxCherries, grid[row][c1] + next);
                            } else {
                                maxCherries = Math.max(maxCherries, grid[row][c1] + grid[row][c2] + next);
                            }
                        }
                    }
                    dp[row][c1][c2] = maxCherries;
                }
            }
        }
        return dp[0][0][m-1];
    }

    // ==============================================================
    // ✅ 4. Space Optimized Tabulation
    // ==============================================================
    // Instead of keeping n*m*m, only need 2*m*m (curr & next row).
    // Time Complexity: O(n * m^2 * 9)
    // Space Complexity: O(m^2)
    // --------------------------------------------------------------
    private int spaceOptimized(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] next = new int[m][m];

        // Base case: last row
        for (int c1 = 0; c1 < m; c1++) {
            for (int c2 = 0; c2 < m; c2++) {
                if (c1 == c2) next[c1][c2] = grid[n-1][c1];
                else next[c1][c2] = grid[n-1][c1] + grid[n-1][c2];
            }
        }

        for (int row = n-2; row >= 0; row--) {
            int[][] curr = new int[m][m];
            for (int c1 = 0; c1 < m; c1++) {
                for (int c2 = 0; c2 < m; c2++) {
                    int maxCherries = (int)-1e8;
                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {
                            int nextVal;
                            if (c1+d1 < 0 || c1+d1 >= m || c2+d2 < 0 || c2+d2 >= m) {
                                nextVal = (int)-1e8;
                            } else {
                                nextVal = next[c1+d1][c2+d2];
                            }
                            if (c1 == c2) {
                                maxCherries = Math.max(maxCherries, grid[row][c1] + nextVal);
                            } else {
                                maxCherries = Math.max(maxCherries, grid[row][c1] + grid[row][c2] + nextVal);
                            }
                        }
                    }
                    curr[c1][c2] = maxCherries;
                }
            }
            next = curr;
        }
        return next[0][m-1];
    }

    // ==============================================================
    // ✅ Main function to test all approaches
    // ==============================================================
    public static void main(String[] args) {
        CherryPickupII sol = new CherryPickupII();
        int[][] grid1 = {
            {3,1,1},
            {2,5,1},
            {1,5,5},
            {2,1,1}
        };

        System.out.println("Recursive: " + sol.helperRecursive(0, 0, grid1[0].length-1, grid1));
        
        int n = grid1.length, m = grid1[0].length;
        int[][][] dp = new int[n][m][m];
        for (int[][] arr : dp) for (int[] a : arr) Arrays.fill(a, -1);
        System.out.println("Memoization: " + sol.helperMemo(0, 0, m-1, grid1, dp));
        
        System.out.println("Tabulation: " + sol.tabulation(grid1));
        System.out.println("Space Optimized: " + sol.spaceOptimized(grid1));
    }
}
