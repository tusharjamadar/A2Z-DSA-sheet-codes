package DynamicProgramming.DpOnStrings;

import java.util.*;

/**
 * ✅ Problem: LeetCode 115 - Distinct Subsequences
 * 
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * 
 * Example:
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation: There are 3 distinct subsequences of s that form "rabbit".
 * 
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * 
 * Constraints:
 * 1 <= s.length, t.length <= 1000
 * s and t consist of English letters.
 * Answer fits in 32-bit integer.
 * 
 * -------------------------------------------------------------------
 * ✅ Intuition:
 * A subsequence means we can delete characters but must keep the relative order.
 * For every character in s, we have 2 choices:
 *   1. If it matches t[j], we can either take it or skip it.
 *   2. Otherwise, we skip it.
 * 
 * This naturally gives us a recursive solution which can be optimized using DP.
 * 
 * -------------------------------------------------------------------
 * ✅ Brute Force (Exponential Recursion - Not Implemented Here):
 * Try all subsequences of s and count those that equal t.
 * TC = O(2^n), SC = O(n) (recursion depth) → Not feasible.
 * 
 * -------------------------------------------------------------------
 * ✅ Optimized Approaches:
 * 
 * 1. Top-Down DP (Memoization):
 *    dp[i][j] = number of subsequences of s[0..i] that equal t[0..j]
 *    Transition:
 *       if s[i] == t[j] → dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
 *       else             → dp[i][j] = dp[i-1][j]
 *    Base cases:
 *       If t is empty → 1 way (delete all chars from s).
 *       If s is empty but t is not → 0 ways.
 *    TC = O(n*m), SC = O(n*m)
 * 
 * 2. Bottom-Up DP (Tabulation):
 *    Build dp table iteratively.
 *    Same transition as above.
 *    TC = O(n*m), SC = O(n*m)
 * 
 * 3. Space Optimized (2 arrays):
 *    Only need previous row to compute current row.
 *    TC = O(n*m), SC = O(m)
 * 
 * 4. Space Optimized (1 array):
 *    Process j from right to left so updates don’t overwrite.
 *    TC = O(n*m), SC = O(m) (Best approach)
 * 
 * -------------------------------------------------------------------
 * ✅ Dry Run Example:
 * s = "rabbbit", t = "rabbit"
 * 
 * dp evolution:
 * - Initially dp[0][0] = 1 (empty subsequence forms empty subsequence).
 * - Each match of 'r', 'a', 'b', ... increases count.
 * - Final dp[n][m] = 3
 * 
 * -------------------------------------------------------------------
 */

public class DistinctSubsequences {

    // ✅ 1. Memoization (Top-Down DP)
    public int helper(int i, int j, String s, String t, int[][] dp) {
        // Base cases
        if (j < 0) return 1;  // If t is exhausted → 1 subsequence found
        if (i < 0) return 0;  // If s is exhausted but t is not → no subsequence

        if (dp[i][j] != -1) return dp[i][j];

        // Option 1: If chars match → take or skip
        if (s.charAt(i) == t.charAt(j)) {
            int take = helper(i - 1, j - 1, s, t, dp);
            int notTake = helper(i - 1, j, s, t, dp);
            return dp[i][j] = take + notTake;
        } else {
            // Option 2: Skip s[i]
            return dp[i][j] = helper(i - 1, j, s, t, dp);
        }
    }

    public int numDistinctMemo(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(n - 1, m - 1, s, t, dp);
    }

    // ✅ 2. Bottom-Up Tabulation
    public int numDistinctTabu(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];

        // Base case: empty t can always be formed
        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }

    // ✅ 3. Space Optimized (2 arrays)
    public int numDistinctSpaceOpt2(String s, String t) {
        int n = s.length(), m = t.length();
        int[] prev = new int[m + 1];
        prev[0] = 1;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            curr[0] = 1; // empty t always possible
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }
            }
            prev = curr;
        }
        return prev[m];
    }

    // ✅ 4. Space Optimized (1 array) → Best approach
    public int numDistinctSpaceOpt1(String s, String t) {
        int n = s.length(), m = t.length();
        int[] dp = new int[m + 1];
        dp[0] = 1; // empty t always possible

        for (int i = 1; i <= n; i++) {
            // Traverse backwards to avoid overwriting
            for (int j = m; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[m];
    }

    // ✅ Main function to test all approaches
    public static void main(String[] args) {
        DistinctSubsequences solver = new DistinctSubsequences();

        String s1 = "rabbbit", t1 = "rabbit";
        String s2 = "babgbag", t2 = "bag";

        System.out.println("Memoization: " + solver.numDistinctMemo(s1, t1)); // 3
        System.out.println("Tabulation: " + solver.numDistinctTabu(s1, t1)); // 3
        System.out.println("Space Opt (2 arrays): " + solver.numDistinctSpaceOpt2(s1, t1)); // 3
        System.out.println("Space Opt (1 array): " + solver.numDistinctSpaceOpt1(s1, t1)); // 3

        System.out.println("Memoization: " + solver.numDistinctMemo(s2, t2)); // 5
        System.out.println("Tabulation: " + solver.numDistinctTabu(s2, t2)); // 5
        System.out.println("Space Opt (2 arrays): " + solver.numDistinctSpaceOpt2(s2, t2)); // 5
        System.out.println("Space Opt (1 array): " + solver.numDistinctSpaceOpt1(s2, t2)); // 5
    }
}

/**
 * ✅ Time Complexity:
 * - Memoization: O(n*m)
 * - Tabulation: O(n*m)
 * - Space Optimized (2 arrays): O(n*m)
 * - Space Optimized (1 array): O(n*m)
 * 
 * ✅ Space Complexity:
 * - Memoization: O(n*m) + recursion stack O(n)
 * - Tabulation: O(n*m)
 * - Space Optimized (2 arrays): O(m)
 * - Space Optimized (1 array): O(m) → Best
 */
