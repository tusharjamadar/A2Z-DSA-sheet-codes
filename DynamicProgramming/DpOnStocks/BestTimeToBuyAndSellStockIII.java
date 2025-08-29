package DynamicProgramming.DpOnStocks;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIII {

    /*
     ============================================================
     ✅ Problem Summary:
     You are given an array prices[] where prices[i] is the price 
     of a stock on the i-th day.
     
     - You may complete at most TWO transactions.
     - A transaction = Buy + Sell
     - You cannot hold multiple stocks at the same time (must sell before buying again).
     - Goal: Maximize total profit.
     
     Example:
       Input: prices = [3,3,5,0,0,3,1,4]
       Output: 6
       Explanation:
         Buy on day 4 (price=0), sell on day 6 (price=3), profit=3
         Buy on day 7 (price=1), sell on day 8 (price=4), profit=3
         Total = 6
     ============================================================
     
     ✅ Brute Force Approach (Not Implemented):
     - Try all possible pairs of two buy/sell operations.
     - For each transaction, loop through all buy/sell days.
     - Take max profit across all combinations.
     - Time Complexity: O(n^2) or worse if extended recursively (O(2^n)).
     - Space Complexity: O(1) (only loop vars).
     Not feasible for n up to 10^5.
     
     ============================================================
     
     ✅ Optimized Approach (Dynamic Programming):
     Key Idea:
     - At each day `i`, we have:
         1. Whether we can buy or sell (buy=1 means can buy, buy=0 means must sell).
         2. Remaining transaction capacity (cap = 2 → at most 2 transactions left).
     - State: f(i, buy, cap)
     - Recurrence:
         if buy==1:
             profit = max(-prices[i] + f(i+1, 0, cap), f(i+1, 1, cap))
         else:
             profit = max(prices[i] + f(i+1, 1, cap-1), f(i+1, 0, cap))
     - Base cases:
         if i == n OR cap == 0 → profit = 0
     
     Approaches:
       1. Memoization (Top-Down recursion + cache)
       2. Tabulation (Bottom-Up 3D DP)
       3. Space Optimization (Rolling arrays)
       4. Transaction count method (0..3 states: Buy→Sell→Buy→Sell)
     
     ============================================================
     
     ✅ Time & Space Complexity:
     - Memoization: O(n*2*3) ≈ O(6n), Space: O(n*2*3) + recursion stack
     - Tabulation: O(n*2*3), Space: O(n*2*3)
     - Space Optimized: O(n*2*3), Space: O(2*3) = O(6)
     - Transaction method: O(n*4), Space: O(4) (more elegant)
     
     ============================================================
     
     ✅ Dry Run Example (Transaction Method):
     prices = [3,3,5,0,0,3,1,4]
     
     trans = 0 (Buy 1st), trans = 1 (Sell 1st), trans = 2 (Buy 2nd), trans = 3 (Sell 2nd)
     
     Day 1: price=3
       trans=0 → profit=max(-3+nextTrans1, skip)=...
     Day 4: price=0
       Buy 1st at 0, later Sell at 3 → profit=3
     Day 7: price=1
       Buy 2nd at 1, Sell at 4 → profit=3
     Total Profit = 6
     ============================================================
     */

    // ===============================
    // 1. Memoization (3D DP: i, buy, cap)
    // ===============================
    private int helper(int i, int buy, int cap, int[] prices, int[][][] dp) {
        if (i == prices.length || cap == 0) return 0;
        if (dp[i][buy][cap] != -1) return dp[i][buy][cap];

        if (buy == 1) {
            // Option 1: Buy stock today
            // Option 2: Skip
            return dp[i][buy][cap] = Math.max(-prices[i] + helper(i + 1, 0, cap, prices, dp),
                                              helper(i + 1, 1, cap, prices, dp));
        } else {
            // Option 1: Sell stock today
            // Option 2: Skip
            return dp[i][buy][cap] = Math.max(prices[i] + helper(i + 1, 1, cap - 1, prices, dp),
                                              helper(i + 1, 0, cap, prices, dp));
        }
    }

    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        for (int[][] row : dp) for (int[] inner : row) Arrays.fill(inner, -1);
        return helper(0, 1, 2, prices, dp);
    }

    // ===============================
    // 2. Tabulation (Bottom-Up 3D DP)
    // ===============================
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 1) {
                        dp[i][buy][cap] = Math.max(-prices[i] + dp[i + 1][0][cap], dp[i + 1][1][cap]);
                    } else {
                        dp[i][buy][cap] = Math.max(prices[i] + dp[i + 1][1][cap - 1], dp[i + 1][0][cap]);
                    }
                }
            }
        }
        return dp[0][1][2];
    }

    // ===============================
    // 3. Space Optimization (2x3 arrays)
    // ===============================
    public int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        int[][] after = new int[2][3];

        for (int i = n - 1; i >= 0; i--) {
            int[][] curr = new int[2][3];
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 1) {
                        curr[buy][cap] = Math.max(-prices[i] + after[0][cap], after[1][cap]);
                    } else {
                        curr[buy][cap] = Math.max(prices[i] + after[1][cap - 1], after[0][cap]);
                    }
                }
            }
            after = curr;
        }
        return after[1][2];
    }

    // ===============================
    // 4. Transaction Method (n*4 DP)
    // trans = 0 → Buy1, 1 → Sell1, 2 → Buy2, 3 → Sell2
    // ===============================
    private int helperTrans(int i, int trans, int[] prices, int[][] dp) {
        if (i == prices.length || trans == 4) return 0;
        if (dp[i][trans] != -1) return dp[i][trans];

        if (trans % 2 == 0) { // Buy
            return dp[i][trans] = Math.max(-prices[i] + helperTrans(i + 1, trans + 1, prices, dp),
                                           helperTrans(i + 1, trans, prices, dp));
        } else { // Sell
            return dp[i][trans] = Math.max(prices[i] + helperTrans(i + 1, trans + 1, prices, dp),
                                           helperTrans(i + 1, trans, prices, dp));
        }
    }

    public int maxProfitTransactionMethod(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][4];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helperTrans(0, 0, prices, dp);
    }

    // ===============================
    // Main Method for Testing
    // ===============================
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII solution = new BestTimeToBuyAndSellStockIII();

        int[] prices1 = {3,3,5,0,0,3,1,4};
        int[] prices2 = {1,2,3,4,5};
        int[] prices3 = {7,6,4,3,1};

        System.out.println("Memoization Result: " + solution.maxProfitMemoization(prices1)); // Expected 6
        System.out.println("Tabulation Result: " + solution.maxProfitTabulation(prices1));   // Expected 6
        System.out.println("Space Optimized Result: " + solution.maxProfitSpaceOptimized(prices1)); // Expected 6
        System.out.println("Transaction Method Result: " + solution.maxProfitTransactionMethod(prices1)); // Expected 6

        System.out.println("Greedy not applicable here (only 2 transactions allowed)");
        System.out.println("Test Case 2: " + solution.maxProfitMemoization(prices2)); // Expected 4
        System.out.println("Test Case 3: " + solution.maxProfitMemoization(prices3)); // Expected 0
    }
}

