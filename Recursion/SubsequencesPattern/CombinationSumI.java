package SubsequencesPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationSumI {
    /*
     * Problem: 39. Combination Sum
     * 
     * Given an array of distinct integers `candidates` and a target integer
     * `target`,
     * return all unique combinations of candidates where the chosen numbers sum to
     * target.
     * Each number in candidates may be used an unlimited number of times.
     * 
     * Example:
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * 
     * Intuition:
     * - This is a classic backtracking problem where we need to explore
     * combinations.
     * - We keep choosing the same number (since unlimited usage is allowed),
     * but once we move forward in the array, we don't go back to previous elements.
     * - To avoid duplicate combinations, we maintain the order by always moving
     * forward.
     * 
     * Algorithm:
     * 1. Sort the candidates array to enable early termination if a number is
     * greater than the remaining target.
     * 2. Use a helper function `solve` to perform backtracking:
     * - If target == 0, we found a valid combination and add it to the result.
     * - If current candidate is greater than target, we skip further processing
     * (early pruning).
     * 3. From the current index, explore each candidate:
     * - Include the candidate in the temporary list
     * - Recurse with the same index (since a number can be reused)
     * - Backtrack by removing the last element
     * 
     * Time Complexity:
     * - Exponential in nature: O(2^target) in the worst case due to branching
     * factor.
     * 
     * Space Complexity:
     * - O(target) for the recursion stack and temporary list.
     */

    // Helper function to generate combinations
    private void solve(List<List<Integer>> res, List<Integer> temp, int[] arr, int start, int target) {
        // Base case: if target becomes 0, current combination is valid
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // If the current candidate is greater than remaining target, we cannot proceed
        if (arr[start] > target)
            return;

        // Explore all candidates from current index onwards
        for (int i = start; i < arr.length; i++) {
            // Choose current candidate
            temp.add(arr[i]);

            // Recur with same candidate (i) since it can be reused
            solve(res, temp, arr, i, target - arr[i]);

            // Backtrack to try other possibilities
            temp.remove(temp.size() - 1);
        }
    }

    // Main function to initiate combination search
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // Sort to enable early stopping
        List<List<Integer>> res = new ArrayList<>();
        solve(res, new ArrayList<>(), candidates, 0, target); // Start backtracking
        return res;
    }

    // Example test
    public static void main(String[] args) {
        CombinationSumI sol = new CombinationSumI();
        int[] candidates1 = { 2, 3, 6, 7 };
        int target1 = 7;
        System.out.println("Combinations for target 7: " + sol.combinationSum(candidates1, target1));

        int[] candidates2 = { 2, 3, 5 };
        int target2 = 8;
        System.out.println("Combinations for target 8: " + sol.combinationSum(candidates2, target2));

        int[] candidates3 = { 2 };
        int target3 = 1;
        System.out.println("Combinations for target 1: " + sol.combinationSum(candidates3, target3));
    }
}