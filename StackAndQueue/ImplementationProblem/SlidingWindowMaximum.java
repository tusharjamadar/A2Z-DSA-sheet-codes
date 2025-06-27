// package StackAndQueue.ImplementationProblem;
import java.util.*;

/*
 * 239. Sliding Window Maximum
 * 
 * ✅ Problem:
 * Given an array of integers `nums` and a window size `k`, return an array of the maximum element in every sliding window of size `k`.
 * 
 * 💡 Intuition:
 * The key idea is to maintain a **monotonic decreasing deque** (storing indices), so that the front always contains the index of the maximum value in the current window.
 * 
 * 🧠 Algorithm:
 * 1. Traverse the array.
 * 2. Remove indices from the front if they are outside the current window.
 * 3. Remove indices from the back if their values are less than the current value (as they can never be the max again).
 * 4. Add current index to the deque.
 * 5. If the window is fully formed (i >= k - 1), record the max (front of deque) in result array.
 * 
 * ⏱️ Time Complexity: O(n)
 * Each element is added and removed from the deque at most once.
 * 
 * 🗃️ Space Complexity: O(k)
 * Size of the deque will never exceed k.
 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Double-ended queue to store indices of potential max values
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;

        // Output array to store maximums for each window
        int[] res = new int[n - k + 1];
        int ri = 0; // result index

        for (int i = 0; i < n; i++) {
            // Step 1: Remove indices that are out of the current window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Step 2: Remove smaller elements from the back
            // since they can't be max if the current one is greater
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            // Step 3: Add current index to deque
            dq.offerLast(i);

            // Step 4: If window is formed, record max (front of deque)
            if (i >= k - 1) {
                res[ri++] = nums[dq.peekFirst()];
            }
        }

        return res;
    }

    // 🔍 Test driver
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println("Sliding Window Maximums: " + Arrays.toString(sol.maxSlidingWindow(nums, k)));
        // Output: [3, 3, 5, 5, 6, 7]
    }
}
