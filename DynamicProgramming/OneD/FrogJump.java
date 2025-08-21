package DynamicProgramming.OneD;
/*
✅ Problem Summary
We are given an array `height[]` where height[i] represents the height of the i-th stair.
A frog starts at stair 0 and wants to reach stair n-1.
From any stair i:
   - it can jump to (i+1)th stair 
   - OR to (i+2)th stair.
The cost of a jump = |height[next] - height[current]|.
We must find the minimum total cost required for the frog to reach the last stair.

💡 Intuition:
At each stair, the frog has 2 choices:
   - Jump 1 step
   - Jump 2 steps
So the minimum cost at index i depends on the best of these two choices.

------------------------------------------
🔁 Brute Force Approach (Recursive, TLE)
- Recur from last index to 0
- At each step, compute:
     left = minCost(i-1) + |height[i] - height[i-1]|
     right = minCost(i-2) + |height[i] - height[i-2]|
- Answer = min(left, right)

Time: O(2^N) → Exponential
Space: O(N) recursion stack

------------------------------------------
✅ Optimized Approach 1: Recursion + Memoization
- Use dp[i] to store minimum cost to reach stair i.
- Avoid recomputation.

Time: O(N)
Space: O(N) + recursion stack O(N)

------------------------------------------
✅ Optimized Approach 2: Tabulation (Bottom-Up DP)
- Iteratively compute dp[i]:
     dp[i] = min(dp[i-1] + |h[i]-h[i-1]|, dp[i-2] + |h[i]-h[i-2]|)

Time: O(N)
Space: O(N)

------------------------------------------
✅ Optimized Approach 3: Space Optimization
- Only last 2 states are needed.
- Use variables prev and prev2.

Time: O(N)
Space: O(1)

------------------------------------------
🧪 Dry Run Example
Input: [30, 20, 50, 10, 40]
dp calculation:
dp[0] = 0
dp[1] = |20-30| = 10
dp[2] = min(dp[1]+|50-20|=40, dp[0]+|50-30|=20) = 20
dp[3] = min(dp[2]+|10-50|=60, dp[1]+|10-20|=20) = 20
dp[4] = min(dp[3]+|40-10|=50, dp[2]+|40-50|=30) = 30
Answer = dp[4] = 30 ✅

------------------------------------------
⏱️ Time & Space Complexity
- Memoization: O(N), O(N)
- Tabulation: O(N), O(N)
- Space Optimized: O(N), O(1)
*/

import java.util.*;

class FrogJump {

    // ✅ Recursion + Memoization
    private int helper(int idx, int[] height, int[] dp) {
        if (idx == 0) return 0; // base case
        if (dp[idx] != -1) return dp[idx];

        int left = helper(idx - 1, height, dp) + Math.abs(height[idx] - height[idx - 1]);

        int right = Integer.MAX_VALUE;
        if (idx > 1) {
            right = helper(idx - 2, height, dp) + Math.abs(height[idx] - height[idx - 2]);
        }

        return dp[idx] = Math.min(left, right);
    }

    public int minCostMemoization(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(n - 1, height, dp);
    }

    // ✅ Tabulation (Bottom-Up DP)
    public int minCostTabulation(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int fs = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int ss = Integer.MAX_VALUE;
            if (i > 1) {
                ss = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            }
            dp[i] = Math.min(fs, ss);
        }
        return dp[n - 1];
    }

    // ✅ Space Optimized DP
    public int minCostSpaceOptimized(int[] height) {
        int n = height.length;
        int prev = 0, prev2 = 0;

        for (int i = 1; i < n; i++) {
            int fs = prev + Math.abs(height[i] - height[i - 1]);
            int ss = Integer.MAX_VALUE;
            if (i > 1) {
                ss = prev2 + Math.abs(height[i] - height[i - 2]);
            }
            int curr = Math.min(fs, ss);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    // ✅ main() for testing
    public static void main(String[] args) {
        FrogJump sol = new FrogJump();

        int[] h1 = {20, 30, 40, 20};
        System.out.println("Memoization: " + sol.minCostMemoization(h1)); // 20
        System.out.println("Tabulation: " + sol.minCostTabulation(h1));   // 20
        System.out.println("Space Opt: " + sol.minCostSpaceOptimized(h1));// 20

        int[] h2 = {30, 20, 50, 10, 40};
        System.out.println("Memoization: " + sol.minCostMemoization(h2)); // 30
        System.out.println("Tabulation: " + sol.minCostTabulation(h2));   // 30
        System.out.println("Space Opt: " + sol.minCostSpaceOptimized(h2));// 30
    }
}
