package BitManipulation.Medium;


/*
Problem Description:
Given an integer array `nums` of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the subsets in any order.

Intuition:
There are 2^n possible subsets for an array of n elements.
Each subset can be represented by a binary number of length n, where the i-th bit indicates 
whether to include nums[i] in the current subset.

Algorithm:
1. Compute the total number of subsets as 2^n using left shift (1 << n).
2. For each number from 0 to 2^n - 1:
   - Treat it as a bitmask.
   - For each bit in the bitmask, if the i-th bit is set, include nums[i] in the current subset.
3. Add the current subset to the result list.
4. Return the list of all subsets.

Time Complexity: O(n * 2^n)
- There are 2^n subsets, and generating each subset takes O(n) time.

Space Complexity: O(n * 2^n)
- The output list contains 2^n subsets, and each subset can have up to n elements.
*/

import java.util.*;

class PowerSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        // Total number of subsets = 2^n
        int subsetCount = 1 << n;

        // Iterate through all possible combinations
        for (int mask = 0; mask < subsetCount; mask++) {
            List<Integer> temp = new ArrayList<>();

            // For each bit, check if it's set in mask
            for (int i = 0; i < n; i++) {
                // If i-th bit is set, include nums[i]
                if ((mask & (1 << i)) != 0) {
                    temp.add(nums[i]);
                }
            }

            // Add the subset to the result list
            res.add(temp);
        }

        return res;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        PowerSet sol = new PowerSet();

        int[] nums1 = {1, 2, 3};
        System.out.println("Subsets of [1, 2, 3]: " + sol.subsets(nums1));

        int[] nums2 = {0};
        System.out.println("Subsets of [0]: " + sol.subsets(nums2));
    }
}
