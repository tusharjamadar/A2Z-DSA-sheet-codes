package DynamicProgramming.DpOnStocks;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockII {

    /*
     ============================================================
     ✅ Problem Summary:
     You are given an integer array prices where prices[i] is the price
     of a given stock on the i-th day.

     - On each day, you may buy and/or sell one stock.
     - You can hold at most one stock at a time.
     - You may buy and sell on the same day.
     - Goal: Maximize profit.

     Example:
       Input: prices = [7,1,5,3,6,4]
       Output: 7
       Explanation:
         Buy on day 2 (price=1), sell on day 3 (price=5) -> profit=4
         Buy on day 4 (price=3), sell on day 5 (price=6) -> profit=3
         Total profit = 7
     ============================================================
     
     ✅ Brute Force Approach (Not Implemented):
     - Try all possible buy/sell combinations using recursion.
     - At each day, decide whether to:
         1. Buy
         2. Sell
         3. Skip
     - Return maximum profit from all paths.
     - Time Complexity: O(2^n) (Exponential, not feasible for n=3*10^4)
     - Space Complexity: O(n) recursion stack
     
     ============================================================
     
     ✅ Optimized Approach (Dynamic Programming):
     Intuition:
     - At each day, we have two states:
         1. We can buy a stock (buy = 1)
         2. We cannot buy (because we already hold one, buy = 0)
     - Recurrence:
         If buy == 1:
             profit = max(-prices[i] + f(i+1, 0), f(i+1, 1))
         If buy == 0:
             profit = max(prices[i] + f(i+1, 1), f(i+1, 0))
     - Base case:
         If we reach end of array -> profit = 0

     Approaches:
       1. Memoization (Top-Down DP with recursion + cache)
       2. Tabulation (Bottom-Up DP)
       3. Space Optimization (Only need next day info, use 2 arrays)
       4. Greedy (Simplest): Just add every "uphill" profit
          i.e., whenever prices[i+1] > prices[i], add (prices[i+1] - prices[i]).

     ============================================================
     
     ✅ Time & Space Complexity:
     - Memoization: O(n*2) time, O(n*2) space
     - Tabulation: O(n*2) time, O(n*2) space
     - Space Optimized: O(n*2) time, O(1) space
     - Greedy: O(n) time, O(1) space (BEST)
     
     ============================================================
     
     ✅ Dry Run Example (Greedy approach):
     prices = [7,1,5,3,6,4]
     
     Day 1: 7 -> 1 (down, skip)
     Day 2: 1 -> 5 (up, profit += 4) total=4
     Day 3: 5 -> 3 (down, skip)
     Day 4: 3 -> 6 (up, profit += 3) total=7
     Day 5: 6 -> 4 (down, skip)
     Final Profit = 7
     ============================================================
     */

    // 1. Memoization Approach
    private int helper(int i, int buy, int[] prices, int[][] dp) {
        if (i == prices.length) return 0;
        if (dp[i][buy] != -1) return dp[i][buy];

        if (buy == 1) {
            // Option 1: Buy today (-prices[i]) + future profit
            // Option 2: Skip buying today
            return dp[i][buy] = Math.max(-prices[i] + helper(i + 1, 0, prices, dp),
                                         helper(i + 1, 1, prices, dp));
        } else {
            // Option 1: Sell today (+prices[i]) + future profit
            // Option 2: Skip selling today
            return dp[i][buy] = Math.max(prices[i] + helper(i + 1, 1, prices, dp),
                                         helper(i + 1, 0, prices, dp));
        }
    }

    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(0, 1, prices, dp);
    }

    // 2. Tabulation Approach
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2]; // dp[i][buy]
        dp[n][0] = dp[n][1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    dp[i][buy] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                } else {
                    dp[i][buy] = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
                }
            }
        }
        return dp[0][1];
    }

    // 3. Space Optimization
    public int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        int[] ahead = new int[2]; // dp[i+1]
        ahead[0] = ahead[1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int[] curr = new int[2];
            curr[1] = Math.max(-prices[i] + ahead[0], ahead[1]); // when buying
            curr[0] = Math.max(prices[i] + ahead[1], ahead[0]);  // when selling
            ahead = curr;
        }
        return ahead[1];
    }

    // 4. Greedy Approach (Best & Simplest)
    public int maxProfitGreedy(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // ===========================
    // Main method for testing
    // ===========================
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockII solution = new BestTimeToBuyAndSellStockII();

        int[] prices1 = {7,1,5,3,6,4};
        int[] prices2 = {1,2,3,4,5};
        int[] prices3 = {7,6,4,3,1};

        System.out.println("Memoization Result: " + solution.maxProfitMemoization(prices1)); // Expected 7
        System.out.println("Tabulation Result: " + solution.maxProfitTabulation(prices1));   // Expected 7
        System.out.println("Space Optimized Result: " + solution.maxProfitSpaceOptimized(prices1)); // Expected 7
        System.out.println("Greedy Result: " + solution.maxProfitGreedy(prices1)); // Expected 7

        System.out.println("Greedy Result (prices2): " + solution.maxProfitGreedy(prices2)); // Expected 4
        System.out.println("Greedy Result (prices3): " + solution.maxProfitGreedy(prices3)); // Expected 0
    }
}


