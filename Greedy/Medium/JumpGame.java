package Greedy.Medium;
/*
✅ Problem: 55. Jump Game
-------------------------
You are given an integer array nums where each element represents your maximum jump length at that position.
You start at index 0. Return true if you can reach the last index, otherwise false.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump from 0 → 1 → 4

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: Cannot move beyond index 3 because nums[3] = 0

-----------------------------------------------------
✅ Intuition:
Keep track of the furthest index you can reach while iterating.
If at any point your current index `i` is greater than the `maxReach`, you’re stuck and return false.

-----------------------------------------------------
✅ Algorithm (Greedy):
1. Initialize maxReach = 0
2. Iterate from i = 0 to n - 1:
   a. If i > maxReach → cannot move forward → return false
   b. Update maxReach = max(maxReach, i + nums[i])
3. If the loop finishes, it means you can reach the end → return true

-----------------------------------------------------
✅ Time Complexity: O(n)
   - We traverse the array once.

✅ Space Complexity: O(1)
   - No extra space used.
*/

public class JumpGame {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxReach = 0;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) return false; // can't reach this point
            maxReach = Math.max(maxReach, i + nums[i]);
        }

        return true;
    }

    // ✅ Main method to test the solution with examples
    public static void main(String[] args) {
        JumpGame sol = new JumpGame();

        // Test Case 1: Should return true
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump (Test 1): " + sol.canJump(nums1)); // true

        // Test Case 2: Should return false
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump (Test 2): " + sol.canJump(nums2)); // false

        // Test Case 3: Single element
        int[] nums3 = {0};
        System.out.println("Can jump (Test 3): " + sol.canJump(nums3)); // true

        // Test Case 4: Large initial jump
        int[] nums4 = {5, 0, 0, 0, 0, 0};
        System.out.println("Can jump (Test 4): " + sol.canJump(nums4)); // true
    }
}
