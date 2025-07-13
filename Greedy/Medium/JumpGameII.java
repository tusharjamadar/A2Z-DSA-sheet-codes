package Greedy.Medium;
/*
✅ Problem: 45. Jump Game II
----------------------------
You are given an array `nums` where each element represents the max jump length from that index.
Return the **minimum number of jumps** required to reach the last index.

----------------------------
Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation:
- Jump from index 0 to index 1 (3 steps allowed)
- Jump from index 1 to index 4

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
Explanation:
- Jump from index 0 to index 1
- Jump from index 1 to index 4

----------------------------
✅ Intuition:
We need to minimize jumps to reach the end.
This can be solved using a greedy approach by tracking the **current jump range** and the **farthest index** we can reach in that range.

We increase jump count when we exhaust the current jump range.

----------------------------
✅ Algorithm:
1. Initialize `jumps = 0`, `farthest = 0`, `currentEnd = 0`
2. Loop `i` from 0 to n-2:
   a. Update `farthest = max(farthest, i + nums[i])`
   b. If `i == currentEnd`, we made a jump:
       - Increment `jumps`
       - Update `currentEnd = farthest`
3. Return `jumps`

----------------------------
✅ Time Complexity: O(n)
- We traverse the array once.

✅ Space Complexity: O(1)
- Only constant extra variables used.
*/

public class JumpGameII {
    public int jump(int[] nums) {
        int n = nums.length;

        int jumps = 0;
        int farthest = 0;
        int currentEnd = 0;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // If we reach the end of the current jump range, jump!
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // If we already reached or crossed the last index, break early
                if (currentEnd >= n - 1) break;
            }
        }

        return jumps;
    }

    // ✅ Main method to test the solution
    public static void main(String[] args) {
        JumpGameII sol = new JumpGameII();

        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Min jumps (Test 1): " + sol.jump(nums1)); // Output: 2

        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println("Min jumps (Test 2): " + sol.jump(nums2)); // Output: 2

        int[] nums3 = {1, 1, 1, 1};
        System.out.println("Min jumps (Test 3): " + sol.jump(nums3)); // Output: 3

        int[] nums4 = {5, 1, 1, 1, 1};
        System.out.println("Min jumps (Test 4): " + sol.jump(nums4)); // Output: 1
    }
}
