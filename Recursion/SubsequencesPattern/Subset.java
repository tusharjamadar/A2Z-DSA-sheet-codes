package SubsequencesPattern;

import java.util.ArrayList;
import java.util.List;

class Subset {
    /*
    ✅ Problem: Subsets (Power Set)
    Given an array of unique integers, return all possible subsets.

    ✅ Example:
    Input: [1, 2, 3]
    Output: 
    [
      [],         // empty set
      [1], [2], [3],
      [1, 2], [1, 3], [2, 3],
      [1, 2, 3]
    ]

    ✅ Intuition:
    - At every index, we have two choices: include the current element or exclude it.
    - Use backtracking to explore all these inclusion/exclusion combinations.

    ✅ Algorithm:
    1. Create a recursive function `solve` that:
       - Takes current index and a temporary list `temp` holding current subset
       - At each step, add the current `temp` subset to the result list
    2. For each index `i` from `start` to end of array:
       - Include `nums[i]` in current subset (`temp.add(nums[i])`)
       - Recurse with `i + 1`
       - Backtrack by removing last added element (`temp.remove(...)`)
    3. This generates all combinations of subsets.

    ✅ Time Complexity:
    - O(2^n), where n = length of input array
    - For each element, we either include it or not (2 choices)

    ✅ Space Complexity:
    - O(n) for recursion call stack and temporary subset
    - O(2^n) overall for storing all subsets
    */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(); // Result list to hold all subsets
        solve(res, new ArrayList<>(), nums, 0);      // Start backtracking from index 0
        return res;
    }

    // 🔁 Recursive helper method to generate subsets
    private void solve(List<List<Integer>> res, List<Integer> temp, int[] nums, int start){
        res.add(new ArrayList<>(temp)); // ✅ Add current subset to result

        // 🔄 Explore all possible elements starting from index `start`
        for(int i = start; i < nums.length; i++){
            temp.add(nums[i]);                  // 💡 Choose current element
            solve(res, temp, nums, i + 1);      // 🔁 Recurse to next index
            temp.remove(temp.size() - 1);       // 🔙 Backtrack to try other possibilities
        }
    }

    // 🔍 Example test
    public static void main(String[] args) {
        Subset sol = new Subset();
        int[] nums1 = {1, 2, 3};
        System.out.println("Subsets of [1, 2, 3]: " + sol.subsets(nums1));

        int[] nums2 = {0};
        System.out.println("Subsets of [0]: " + sol.subsets(nums2));
    }
}
