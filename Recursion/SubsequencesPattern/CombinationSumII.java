package SubsequencesPattern;

import java.util.*;

/*
Problem: 40. Combination Sum II

Given a collection of candidate numbers (`candidates`) and a target number (`target`), 
find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: [[1,1,6],[1,2,5],[1,7],[2,6]]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output: [[1,2,2],[5]]

Constraints:
- 1 <= candidates.length <= 100
- 1 <= candidates[i] <= 50
- 1 <= target <= 30

Intuition:
- Since each candidate can be used only once, we need to move to the next index on recursion.
- To avoid duplicate combinations, we skip over elements that are the same as the previous one at the same recursion level.

Algorithm:
1. Sort the array to help with pruning and duplicate removal.
2. Use backtracking to explore combinations.
3. If the current element is greater than the target, break the loop early.
4. If the current element is the same as the previous element at the same recursion level, skip it to avoid duplicates.
5. Recurse with the next index (i+1) since reuse of the same element is not allowed.
6. Backtrack to explore other combinations.

Time Complexity:
- Exponential in worst case: O(2^n), where n = number of candidates
- Sorting takes O(n log n)

Space Complexity:
- O(target) auxiliary space for recursion stack
*/

class CombinationSumII {
    // Helper function for backtracking
    private void solve(List<List<Integer>> res, List<Integer> temp, int[] arr, int start, int target) {
        // Base case: if target is 0, add current combination to result
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // Iterate over the array starting from index `start`
        for (int i = start; i < arr.length; i++) {
            // Skip duplicates: if the current number is same as previous at the same level
            if (i > start && arr[i] == arr[i - 1]) continue;

            // If current number is greater than target, break early (since array is sorted)
            if (arr[i] > target) break;

            // Choose the current number
            temp.add(arr[i]);

            // Recurse with the next index (i+1), because reuse is not allowed
            solve(res, temp, arr, i + 1, target - arr[i]);

            // Backtrack: remove the last added number and try other combinations
            temp.remove(temp.size() - 1);
        }
    }

    // Main function to generate all combinations
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // Sort the array to handle duplicates and enable pruning
        List<List<Integer>> res = new ArrayList<>();
        solve(res, new ArrayList<>(), candidates, 0, target); // Start backtracking
        return res;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        CombinationSumII sol = new CombinationSumII();

        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println("Combinations for target 8: " + sol.combinationSum2(candidates1, target1));

        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println("Combinations for target 5: " + sol.combinationSum2(candidates2, target2));

        int[] candidates3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 3;
        System.out.println("Combinations for target 3: " + sol.combinationSum2(candidates3, target3));
    }
}
