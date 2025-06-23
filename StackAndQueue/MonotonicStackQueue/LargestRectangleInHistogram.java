package StackAndQueue.MonotonicStackQueue;

import java.util.Stack;

class LargestRectangleInHistogram {

    /*
     * 84. Largest Rectangle in Histogram
     *
     * 🧩 Problem:
     * Given an array of integers `heights` representing the histogram's bar height
     * where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     *
     * ------------------------------------------------------------------
     * 💡 Intuition:
     * - For each bar, we want to find the maximum width we can extend left and right
     *   while all bars are >= current height.
     * - To achieve this efficiently, we use a stack to keep track of indices of increasing bar heights.
     * - When we find a smaller bar, we pop from the stack and calculate the area with the popped height
     *   as the smallest height of the rectangle.
     * - This works similarly to a **monotonic increasing stack** strategy.
     *
     * ------------------------------------------------------------------
     * 🧪 Algorithm:
     * 1. Initialize a stack to keep indices of increasing bar heights.
     * 2. Traverse from i = 0 to n:
     *    - Let `curr` be the height at index `i` (or 0 if `i == n` to flush out stack).
     *    - While stack is not empty and height at top of stack > curr:
     *        a. Pop the index.
     *        b. Calculate width:
     *           - If stack is empty: width = i
     *           - Else: width = i - stack.peek() - 1
     *        c. Calculate area = height * width, update max area.
     *    - Push current index to stack.
     * 3. Return the max area found.
     *
     * ------------------------------------------------------------------
     * ⏱️ Time Complexity: O(n)
     * - Each element is pushed and popped from the stack at most once.
     *
     * 🗃️ Space Complexity: O(n)
     * - For the stack storing indices.
     */

    public int largestRectangleArea(int[] heights) {
        int maxi = 0;
        Stack<Integer> st = new Stack<>();
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Treat current height as 0 at the end to flush out the stack
            int curr = (i == n) ? 0 : heights[i];

            // If current bar is lower than bar at top of stack, calculate area
            while (!st.isEmpty() && heights[st.peek()] > curr) {
                int h = heights[st.pop()]; // Height of the bar
                // Width is from current index to the index before the new top of stack
                int w = st.isEmpty() ? i : (i - st.peek() - 1);
                maxi = Math.max(maxi, h * w);
            }

            // Push current index to stack
            st.push(i);
        }

        return maxi;
    }

    // 🔍 Optional: You can test this with a main method
    public static void main(String[] args) {
        LargestRectangleInHistogram sol = new LargestRectangleInHistogram();
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println(sol.largestRectangleArea(heights1)); // Output: 10

        int[] heights2 = {2, 4};
        System.out.println(sol.largestRectangleArea(heights2)); // Output: 4

        int[] heights3 = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(sol.largestRectangleArea(heights3)); // Output: 12
    }
}
