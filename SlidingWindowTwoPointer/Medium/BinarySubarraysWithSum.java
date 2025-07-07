package SlidingWindowTwoPointer.Medium;
/*
930. Binary Subarrays With Sum

🧩 Problem:
You are given a binary array `nums` (only 0s and 1s) and an integer `goal`. 
Return the number of **non-empty subarrays** whose sum equals `goal`.

A subarray is a contiguous segment of the array.

🧠 Intuition:
We count the number of subarrays with **exactly `goal` sum**.

Key Observation:
👉 number of subarrays with **exactly `goal`** = 
   number of subarrays with **at most `goal`** − number of subarrays with **at most `goal - 1`**

Why?
- This trick is often used when counting "exact" value is hard, but "at most" is easier using sliding window.

👓 Dry Run:
nums = [1,0,1,0,1], goal = 2

- subarrays with at most 2 ones: 12
- subarrays with at most 1 ones: 8
- so exactly 2: 12 - 8 = 4 ✅

✅ Algorithm (Sliding Window for AtMostGoal):
1. Use sliding window with two pointers: `left` and `right`.
2. Keep track of `onesCount` inside the window.
3. If `onesCount` > `goal`, shrink window from the left.
4. At each step, add `(right - left + 1)` to the result — which represents the number of valid subarrays ending at `right`.

⏱ Time Complexity: O(n)
Only one pass through the array in both calls.

📦 Space Complexity: O(1)
Uses constant space.
*/

class BinarySubarraysWithSum {

    // Helper to calculate number of subarrays with sum ≤ goal
    public static int subarrayWithAtMostGoal(int[] nums, int goal){
        int onesCnt = 0;
        int left = 0, right = 0, totalCnt = 0;

        while (right < nums.length) {
            if (nums[right] == 1) onesCnt++;

            while (left <= right && onesCnt > goal) {
                if (nums[left] == 1) onesCnt--;
                left++;
            }

            totalCnt += (right - left + 1);
            right++;
        }

        return totalCnt;
    }

    // Main function to return number of subarrays with sum == goal
    public int numSubarraysWithSum(int[] nums, int goal) {
        return subarrayWithAtMostGoal(nums, goal) - subarrayWithAtMostGoal(nums, goal - 1);
    }

    // 🔍 Main method to test the solution
    public static void main(String[] args) {
        BinarySubarraysWithSum sol = new BinarySubarraysWithSum();

        int[] nums1 = {1, 0, 1, 0, 1};
        int goal1 = 2;
        System.out.println("Output 1: " + sol.numSubarraysWithSum(nums1, goal1)); // Expected: 4

        int[] nums2 = {0, 0, 0, 0, 0};
        int goal2 = 0;
        System.out.println("Output 2: " + sol.numSubarraysWithSum(nums2, goal2)); // Expected: 15

        int[] nums3 = {1,1,1,1,1};
        int goal3 = 3;
        System.out.println("Output 3: " + sol.numSubarraysWithSum(nums3, goal3)); // Expected: 3
    }
}
