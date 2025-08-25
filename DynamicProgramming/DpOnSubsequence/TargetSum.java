package DynamicProgramming.DpOnSubsequence;
/*
✅ Problem: 494. Target Sum
You are given an array `nums` and a target value `target`.
You need to assign '+' or '-' to each number such that the final expression evaluates to `target`.

Return the number of different possible expressions.

Example:
Input: nums = [1,1,1,1,1], target = 3
Output: 5

----------------------------------------------------------
💡 Intuition:
We need to partition the numbers into two groups:
  - One group gets a '+' sign → call its sum = S1
  - Other group gets a '-' sign → call its sum = S2

So the equation becomes:
    S1 - S2 = target
Also, S1 + S2 = totalSum

=> S1 = (totalSum + target) / 2

This reduces the problem to:
👉 Count the number of subsets of nums that have sum = (totalSum + target) / 2

⚠️ Important checks:
- If totalSum < target → impossible
- If (totalSum + target) is odd → impossible
----------------------------------------------------------
🔁 Brute Force Approach:
Use recursion / backtracking:
- At each index, either add '+' or '-'.
- Explore all possibilities and count valid ones.
Time: O(2^N), exponential → works only for small n.

----------------------------------------------------------
✅ Optimized Approach (DP):
Convert into "Count Subsets with Given Sum" problem:
- Use dynamic programming to count subsets with sum = S1.
- dp[i][t] = number of ways to achieve target sum `t` using first `i` elements.

Steps:
1. Compute totalSum
2. Validate edge cases
3. Required subset sum = (totalSum - target)/2
4. Use DP (bottom-up) to count subsets

----------------------------------------------------------
🧪 Dry Run:
nums = [1,1,1,1,1], target = 3
totalSum = 5
S1 = (5 + 3) / 2 = 4
Now, count subsets with sum = 4

Subsets of sum 4: [1,1,1,1] → There are 5 ways (choose which one is excluded).
Answer = 5 ✅

----------------------------------------------------------
⏱️ Complexity:
Time: O(N * Target) where Target = required subset sum
Space: O(N * Target) (can be optimized to O(Target) with 1D DP)

*/

public class TargetSum {

    // Function to count subsets with given target sum
    public int findWays(int[] num, int tar) {
        int n = num.length;
        int dp[][] = new int[n][tar + 1];

        // Base case initialization
        if (num[0] == 0)
            dp[0][0] = 2; // Two cases: pick 0 or don't pick
        else
            dp[0][0] = 1; // Only one case: don't pick

        if (num[0] != 0 && num[0] <= tar)
            dp[0][num[0]] = 1; // One case: pick num[0]

        // Fill DP table
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= tar; target++) {
                int notTaken = dp[ind - 1][target];
                int taken = 0;
                if (num[ind] <= target)
                    taken = dp[ind - 1][target - num[ind]];
                dp[ind][target] = notTaken + taken;
            }
        }
        return dp[n - 1][tar];
    }

    // Count partitions with given difference = target
    public int countPartitions(int n, int d, int[] arr) {
        int totSum = 0;
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }

        // Edge cases
        if (totSum - d < 0 || (totSum - d) % 2 == 1) return 0;

        int requiredSum = (totSum - d) / 2;
        return findWays(arr, requiredSum);
    }

    // Main function for LeetCode
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        return countPartitions(n, target, nums);
    }

    // 🔎 Testing with examples
    public static void main(String[] args) {
        TargetSum sol = new TargetSum();

        int[] nums1 = {1,1,1,1,1};
        int target1 = 3;
        System.out.println("Test 1 Output: " + sol.findTargetSumWays(nums1, target1)); 
        // Expected: 5

        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Test 2 Output: " + sol.findTargetSumWays(nums2, target2)); 
        // Expected: 1

        int[] nums3 = {2,1};
        int target3 = 1;
        System.out.println("Test 3 Output: " + sol.findTargetSumWays(nums3, target3)); 
        // Expected: 1 ("+2-1")
    }
}
