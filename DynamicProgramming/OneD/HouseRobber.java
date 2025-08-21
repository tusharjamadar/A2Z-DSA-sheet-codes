package DynamicProgramming.OneD;
/*
✅ Problem Summary
You are a robber planning to rob houses arranged in a line. Each house has some money.
You cannot rob two adjacent houses, otherwise the police will be alerted.
Return the maximum money you can rob without alerting the police.

💡 Intuition
At each house, you have two choices:
1. Rob it → Add its value + solve for (i-2).
2. Skip it → Solve for (i-1).
Take the maximum of both.

This is a classic **Dynamic Programming** problem.

🔁 Brute Force
Try all possibilities recursively.
Time: O(2^N) → TLE.

✅ Optimized Approaches:
1. **Memoization (Top-Down DP)**
   - Recursively compute, store results in `dp[]`.
   - Avoids recomputation.
   - Time: O(N), Space: O(N) + recursion stack.

2. **Tabulation (Bottom-Up DP)**
   - Iteratively build dp[] from base cases.
   - Time: O(N), Space: O(N).

3. **Space Optimized DP**
   - Only last two states are needed.
   - Reduce space to O(1).
   - Time: O(N), Space: O(1).

🧪 Dry Run (Example: nums = [2,7,9,3,1])
- Rob 2 → then max from [9,3,1] = 9+2 = 11
- Rob 7 → then max from [3,1] = 3+7 = 10
- Rob 9 → then only 1 remains = 9+2 = 11
- Final = 12 (rob house 2 and 4 and 5)

⏱️ Time and Space Complexity
- Memoization: Time O(N), Space O(N)
- Tabulation: Time O(N), Space O(N)
- Space Optimized: Time O(N), Space O(1)
*/

import java.util.*;

class HouseRobber {

    // 🔹 1. Memoization (Top-Down DP)
    private int helper(int idx, int[] nums, int[] dp) {
        if (idx == 0) return nums[idx];       // Base case: Only one house
        if (idx < 0) return 0;                // Invalid index

        if (dp[idx] != -1) return dp[idx];    // Return cached result

        // Pick current house → nums[idx] + solve for (idx-2)
        int pick = nums[idx] + helper(idx - 2, nums, dp);

        // Skip current house → solve for (idx-1)
        int notPick = helper(idx - 1, nums, dp);

        // Store and return max
        return dp[idx] = Math.max(pick, notPick);
    }

    public int robMemo(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(n - 1, nums, dp);
    }


    // 🔹 2. Tabulation (Bottom-Up DP)
    public int robTab(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0]; // Edge case: Only one house

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // Either rob this house + dp[i-2], or skip this house (dp[i-1])
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }


    // 🔹 3. Space Optimized DP
    public int robSpaceOpt(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int prev2 = nums[0]; // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]

        for (int i = 2; i < n; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]); // rob or skip
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }


    // 🔹 Main Method to test all 3 approaches
    public static void main(String[] args) {
        HouseRobber sol = new HouseRobber();

        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};

        System.out.println("Memoization:");
        System.out.println("Example 1: " + sol.robMemo(nums1)); // Expected 4
        System.out.println("Example 2: " + sol.robMemo(nums2)); // Expected 12

        System.out.println("\nTabulation:");
        System.out.println("Example 1: " + sol.robTab(nums1)); // Expected 4
        System.out.println("Example 2: " + sol.robTab(nums2)); // Expected 12

        System.out.println("\nSpace Optimized:");
        System.out.println("Example 1: " + sol.robSpaceOpt(nums1)); // Expected 4
        System.out.println("Example 2: " + sol.robSpaceOpt(nums2)); // Expected 12
    }
}
