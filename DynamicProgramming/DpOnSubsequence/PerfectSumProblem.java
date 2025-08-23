package DynamicProgramming.DpOnSubsequence;
import java.util.*; 

public class PerfectSumProblem {
    /*
    ✅ Problem Summary:
    We are given an array of non-negative integers `arr` and a target integer `target`.
    We need to count the total number of subsets of `arr` such that the sum of the subset equals `target`.

    Example:
    Input: arr = [5, 2, 3, 10, 6, 8], target = 10
    Output: 3
    Explanation: The subsets are {5, 2, 3}, {2, 8}, {10}

    Input: arr = [35, 2, 8, 22], target = 0
    Output: 1
    Explanation: Only the empty subset contributes sum = 0

    ----------------------------------
    ✅ Constraints:
    - 1 ≤ arr.length ≤ 1000
    - 0 ≤ arr[i] ≤ 1000
    - 0 ≤ target ≤ 1000
    ----------------------------------
    */

    /*
    ✅ Brute Force Approach (Exponential Time):
    - We try to generate all possible subsets (2^n subsets).
    - For each subset, check if its sum == target.
    - Count all such subsets.

    🔹 Time Complexity: O(2^n * n) (for generating and summing subsets)
    🔹 Space Complexity: O(n) recursion depth
    This is infeasible for n = 1000 → 2^1000 subsets is impossible to compute.

    Hence, we need **Dynamic Programming**.
    */

    // ------------------------------------------------------------------
    // ✅ Optimized Approach 1: Memoization (Top-Down DP)
    // ------------------------------------------------------------------

    private int memoHelper(int idx, int target, int[] nums, int[][] dp) {
        /*
        🔹 Base Cases (very important to understand carefully):
        Case 1: idx == 0 (first element of the array)
            - If target == 0 AND nums[0] == 0 → Answer = 2
              Why? Because two choices:
                 → Take nums[0] (which is 0, sum still remains 0)
                 → Do not take nums[0]
              So, both contribute to sum = 0.

            - If target == 0 → Answer = 1
              Why? Because only one way → take empty subset.
            
            - If nums[0] == target → Answer = 1
              Why? Because subset {nums[0]} forms target.
            
            - Otherwise → Answer = 0
        */

        if (idx == 0) {
            if (target == 0 && nums[0] == 0) return 2; 
            if (target == 0 || nums[0] == target) return 1;
            return 0;
        }

        // If already computed, return from dp
        if (dp[idx][target] != -1) return dp[idx][target];

        // Choice 1: Do not pick current element
        int notPick = memoHelper(idx - 1, target, nums, dp);

        // Choice 2: Pick current element (only if it's <= target)
        int pick = 0;
        if (nums[idx] <= target) {
            pick = memoHelper(idx - 1, target - nums[idx], nums, dp);
        }

        // Store result in dp
        return dp[idx][target] = notPick + pick;
    }

    public int perfectSumMemo(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoHelper(n - 1, target, nums, dp);
    }

    // ------------------------------------------------------------------
    // ✅ Optimized Approach 2: Tabulation (Bottom-Up DP)
    // ------------------------------------------------------------------

    public int perfectSumTabu(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];

        /*
        Base Case Initialization:
        - When idx = 0 (first element):
            → If nums[0] == 0 → dp[0][0] = 2 (pick or not pick)
            → Otherwise dp[0][0] = 1 (only empty subset works)
            → If nums[0] <= target → dp[0][nums[0]] = 1
        */
        if (nums[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;

        if (nums[0] != 0 && nums[0] <= target) dp[0][nums[0]] = 1;

        // Fill dp table
        for (int idx = 1; idx < n; idx++) {
            for (int tar = 0; tar <= target; tar++) {
                int notPick = dp[idx - 1][tar];
                int pick = 0;
                if (nums[idx] <= tar) {
                    pick = dp[idx - 1][tar - nums[idx]];
                }
                dp[idx][tar] = notPick + pick;
            }
        }

        return dp[n - 1][target];
    }

    // ------------------------------------------------------------------
    // ✅ Optimized Approach 3: Space Optimization
    // ------------------------------------------------------------------

    public int perfectSumSpaceOpt(int[] nums, int target) {
        int n = nums.length;
        int[] prev = new int[target + 1];

        // Base Case Initialization
        if (nums[0] == 0) prev[0] = 2;
        else prev[0] = 1;

        if (nums[0] != 0 && nums[0] <= target) prev[nums[0]] = 1;

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[target + 1];
            for (int tar = 0; tar <= target; tar++) {
                int notPick = prev[tar];
                int pick = 0;
                if (nums[idx] <= tar) {
                    pick = prev[tar - nums[idx]];
                }
                curr[tar] = notPick + pick;
            }
            prev = curr;
        }

        return prev[target];
    }

    // ------------------------------------------------------------------
    // ✅ Dry Run Example (for understanding)
    // ------------------------------------------------------------------
    /*
    Example: arr = [5, 2, 3, 10, 6, 8], target = 10

    DP computation:
    - dp[0][0] = 1 (empty subset)
    - dp[0][5] = 1 (subset {5})

    Progressively filling table:
    - For element 2: subsets {2}, {5,2}
    - For element 3: subsets {3}, {2,3}, {5,3}, {5,2,3}
    - For element 10: {10}, {5,2,3}, {2,8}

    Final Answer = 3
    */

    // ------------------------------------------------------------------
    // ✅ Time & Space Complexity Analysis
    // ------------------------------------------------------------------
    /*
    Memoization:
        - Time: O(n * target)
        - Space: O(n * target) + O(n) recursion stack

    Tabulation:
        - Time: O(n * target)
        - Space: O(n * target)

    Space Optimized:
        - Time: O(n * target)
        - Space: O(target)
    */

    // ------------------------------------------------------------------
    // ✅ Main Method for Testing
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        PerfectSumProblem solver = new PerfectSumProblem();

        int[] arr1 = {5, 2, 3, 10, 6, 8};
        int target1 = 10;
        System.out.println("Memoization: " + solver.perfectSumMemo(arr1, target1)); // 3
        System.out.println("Tabulation: " + solver.perfectSumTabu(arr1, target1)); // 3
        System.out.println("Space Optimized: " + solver.perfectSumSpaceOpt(arr1, target1)); // 3

        int[] arr2 = {2, 5, 1, 4, 3};
        int target2 = 10;
        System.out.println("Memoization: " + solver.perfectSumMemo(arr2, target2)); // 3
        System.out.println("Tabulation: " + solver.perfectSumTabu(arr2, target2)); // 3
        System.out.println("Space Optimized: " + solver.perfectSumSpaceOpt(arr2, target2)); // 3

        int[] arr3 = {35, 2, 8, 22};
        int target3 = 0;
        System.out.println("Memoization: " + solver.perfectSumMemo(arr3, target3)); // 1
        System.out.println("Tabulation: " + solver.perfectSumTabu(arr3, target3)); // 1
        System.out.println("Space Optimized: " + solver.perfectSumSpaceOpt(arr3, target3)); // 1
    }
}
