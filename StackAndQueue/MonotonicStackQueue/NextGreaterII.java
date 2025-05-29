package StackAndQueue.MonotonicStackQueue;

import java.util.*;

/*
 * Problem: 503. Next Greater Element II
 * --------------------------------------
 * You are given a circular integer array 'nums' (i.e., the next element of nums[nums.length - 1] is nums[0]).
 * For each element x in the array, find the next greater number in the circular traversal.
 * If no such greater number exists, return -1 for that element.
 *
 * Example 1:
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 *
 * Example 2:
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^9 <= nums[i] <= 10^9
 *
 * --------------------------------------
 * Intuition:
 * --------------------------------------
 * The array is circular, so for each element we may need to look beyond the end of the array,
 * wrapping around to the beginning. To simulate circular traversal, we iterate over the array twice.
 * We'll use a monotonic decreasing stack to keep track of elements for which we haven’t yet found a next greater.
 * 
 * For each element, we pop elements from the stack until we find a greater one,
 * and that becomes the next greater for the current element.
 *
 * --------------------------------------
 * Algorithm:
 * --------------------------------------
 * 1. Initialize an array `result` of length n and a stack to store elements.
 * 2. Loop from 2n-1 down to 0 (simulate circular traversal).
 * 3. For each i:
 *    a. Get the actual index in the array as i % n.
 *    b. While the stack is not empty and top of stack <= nums[i % n], pop from stack.
 *    c. If i < n (first pass), then result[i] = top of stack if stack is not empty, else -1.
 *    d. Push nums[i % n] onto the stack.
 * 4. Return the result array.
 *
 * --------------------------------------
 * Time Complexity: O(n)
 * - Each element is pushed and popped at most once.
 *
 * Space Complexity: O(n)
 * - Stack and result array both use O(n) space.
 */

public class NextGreaterII {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n]; // Result array to store next greater elements
        Stack<Integer> stack = new Stack<>(); // Stack to store elements in decreasing order

        // Traverse the array twice to simulate circular behavior
        for (int i = 2 * n - 1; i >= 0; i--) {
            int index = i % n; // Circular index

            // Pop all smaller or equal elements as they can't be next greater
            while (!stack.isEmpty() && stack.peek() <= nums[index]) {
                stack.pop();
            }

            // Fill result only in the first pass (i < n)
            if (i < n) {
                result[index] = stack.isEmpty() ? -1 : stack.peek();
            }

            // Push current element to stack
            stack.push(nums[index]);
        }

        return result;
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        NextGreaterII sol = new NextGreaterII();

        int[] nums1 = {1, 2, 1};
        System.out.println("Output 1: " + Arrays.toString(sol.nextGreaterElements(nums1))); // [2, -1, 2]

        int[] nums2 = {1, 2, 3, 4, 3};
        System.out.println("Output 2: " + Arrays.toString(sol.nextGreaterElements(nums2))); // [2, 3, 4, -1, 4]
    }
}
