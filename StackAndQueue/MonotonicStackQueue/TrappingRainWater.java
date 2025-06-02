package StackAndQueue.MonotonicStackQueue;
/*
 * Problem: 42. Trapping Rain Water
 * --------------------------------
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Constraints:
 * - n == height.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= height[i] <= 10^5
 *
 * --------------------------------
 * Intuition:
 * --------------------------------
 * The water trapped at any position is determined by the minimum of the highest bars to its left and right,
 * minus the height of the current bar. We can avoid using extra space for leftMax and rightMax arrays
 * by using two pointers — one from the left and one from the right — and moving inward.
 * At every step, we trap water at the smaller height side, updating the max seen so far on that side.
 *
 * --------------------------------
 * Algorithm:
 * --------------------------------
 * 1. Initialize two pointers `l` (start) and `r` (end).
 * 2. Initialize `leftMax` and `rightMax` to 0.
 * 3. While l < r:
 *    a. If height[l] <= height[r]:
 *        - If height[l] < leftMax → water = leftMax - height[l]
 *        - Else update leftMax
 *        - Move l++
 *    b. Else:
 *        - If height[r] < rightMax → water = rightMax - height[r]
 *        - Else update rightMax
 *        - Move r--
 * 4. Return the total trapped water.
 *
 * --------------------------------
 * Time Complexity: O(n)
 * - Each element is visited once.
 *
 * Space Complexity: O(1)
 * - No extra space used apart from variables.
 */

 class TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        int leftMax = 0, rightMax = 0; // Maximum height to the left and right
        int l = 0, r = n - 1;          // Two pointers from both ends
        int total = 0;                 // Total water trapped

        while (l < r) {
            if (height[l] <= height[r]) {
                // Process left side
                if (leftMax > height[l]) {
                    total += leftMax - height[l]; // Trap water
                } else {
                    leftMax = height[l]; // Update left max
                }
                l++; // Move left pointer
            } else {
                // Process right side
                if (rightMax > height[r]) {
                    total += rightMax - height[r]; // Trap water
                } else {
                    rightMax = height[r]; // Update right max
                }
                r--; // Move right pointer
            }
        }

        return total;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        TrappingRainWater sol = new TrappingRainWater();

        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Trapped Water: " + sol.trap(height1)); // Output: 6

        int[] height2 = {4,2,0,3,2,5};
        System.out.println("Trapped Water: " + sol.trap(height2)); // Output: 9
    }
}
