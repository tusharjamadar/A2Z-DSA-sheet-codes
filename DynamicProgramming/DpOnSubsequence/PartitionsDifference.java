package DynamicProgramming.DpOnSubsequence;
/*
===============================================================
✅ Problem Summary:
We are given an array 'arr' and an integer 'd'. We want to partition the array into two subsets (sum1, sum2) such that:
   1. sum1 >= sum2
   2. sum1 - sum2 = d
We need to count the number of valid partitions that satisfy these conditions.

===============================================================
✅ Key Insight:
Let totalSum = sum(arr).
We want: sum1 - sum2 = d  -->  sum1 = (totalSum + d) / 2.

👉 Why?
Because sum1 + sum2 = totalSum (by definition of partition).
So:
   sum1 - sum2 = d
   sum1 + sum2 = totalSum
---------------------------------
Add both equations:
   2*sum1 = totalSum + d
   sum1 = (totalSum - d) / 2  (after rearranging)

Thus, the problem reduces to: **Count subsets of arr whose sum is (totalSum - d) / 2**.

⚠️ Edge Cases:
- If (totalSum - d) < 0, no solution.
- If (totalSum - d) is odd, no solution (since sum must be integer).

===============================================================
✅ Brute Force Approach (Exponential):
- Generate all subsets, compute sums of both subsets, check if difference = d.
- Time Complexity: O(2^n), not feasible for n up to 50.

===============================================================
✅ Optimized Approach (Dynamic Programming - Subset Sum Count):
- Use DP to count how many subsets have a given target sum.
- Target = (totalSum - d)/2.
- This is a classic **count subset sum problem**.

DP State:
   dp[i][t] = number of ways to form sum 't' using first 'i' elements.

Transition:
   - Not pick arr[i] → dp[i-1][t]
   - Pick arr[i] if (arr[i] <= t) → dp[i-1][t - arr[i]]
   dp[i][t] = (pick + notPick).

Special Case:
   If arr[0] == 0 → two ways (pick or not pick), so initialize accordingly.

===============================================================
✅ Time & Space Complexity:
- Time Complexity: O(n * target) where target = (totalSum - d)/2.
- Space Complexity: O(n * target) for DP table (can be optimized to 1D).

===============================================================
✅ Dry Run Example:
arr = [5,2,6,4], d = 3
- totalSum = 17
- target = (totalSum - d)/2 = (17 - 3)/2 = 7
So we need to count subsets with sum = 7.

Subsets that give sum 7:
   {5,2} → 7
Only one such subset.

Thus answer = 1 ✅

===============================================================
*/

public class PartitionsDifference {
    static int MOD = (int)1e9 + 7;

    // Function to count subsets with sum = target
    static int countNumberOfWays(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];

        // Base case initialization
        // If first element is 0 → two ways to form sum=0 (pick it or not pick it)
        if (arr[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1; // Otherwise only 1 way (not pick)

        // If arr[0] is non-zero and <= target, then we can form that sum in 1 way
        if (arr[0] != 0 && arr[0] <= target) dp[0][arr[0]] = 1;

        // Fill DP table
        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int notPick = dp[i-1][t];
                int pick = 0;
                if (arr[i] <= t) {
                    pick = dp[i-1][t - arr[i]];
                }
                dp[i][t] = (pick + notPick) % MOD;
            }
        }
        return dp[n-1][target];
    }

    // Main function to count partitions
    int countPartitions(int[] arr, int d) {
        int totalSum = 0;
        for (int ele : arr) totalSum += ele;

        // If totalSum - d is negative or odd, no valid partition
        if (totalSum - d < 0 || (totalSum - d) % 2 != 0) return 0;

        int target = (totalSum - d) / 2;
        return countNumberOfWays(arr, target);
    }

    // ========================
    // ✅ main() for Testing
    // ========================
    public static void main(String[] args) {
        PartitionsDifference sol = new PartitionsDifference();

        int[] arr1 = {5, 2, 6, 4};
        int d1 = 3;
        System.out.println("Test Case 1: " + sol.countPartitions(arr1, d1)); // Expected: 1

        int[] arr2 = {1, 1, 1, 1};
        int d2 = 0;
        System.out.println("Test Case 2: " + sol.countPartitions(arr2, d2)); // Expected: 6

        int[] arr3 = {1, 2, 1, 0, 1, 3, 3};
        int d3 = 11;
        System.out.println("Test Case 3: " + sol.countPartitions(arr3, d3)); // Expected: 2
    }
}
