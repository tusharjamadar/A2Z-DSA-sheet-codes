package StackAndQueue.MonotonicStackQueue;
/*
 * Problem: 907. Sum of Subarray Minimums
 * --------------------------------------
 * Given an array of integers `arr`, find the sum of the minimum value of every (contiguous) subarray of `arr`.
 * Return the result modulo 10^9 + 7.
 *
 * Example 1:
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays and their minimums:
 * [3] = 3, [1] = 1, [2] = 2, [4] = 4,
 * [3,1] = 1, [1,2] = 1, [2,4] = 2,
 * [3,1,2] = 1, [1,2,4] = 1, [3,1,2,4] = 1 → Sum = 17
 *
 * Constraints:
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 *
 * --------------------------------------
 * Intuition:
 * --------------------------------------
 * For each element in the array, we determine:
 * - How many subarrays in which it is the minimum element.
 * To do this, we find:
 * - The previous smaller or equal element's index (PSEE).
 * - The next strictly smaller element's index (NSE).
 *
 * If an element `arr[i]` has:
 * - PSEE at index `j`, and NSE at index `k`,
 * - It contributes to (i - j) * (k - i) subarrays as the minimum.
 * Total contribution of `arr[i]` = arr[i] * (i - j) * (k - i)
 *
 * --------------------------------------
 * Algorithm:
 * --------------------------------------
 * 1. Find PSEE (Previous Smaller or Equal Element) using a monotonic increasing stack.
 * 2. Find NSE (Next Smaller Element) similarly from right to left.
 * 3. For each element, calculate its contribution based on distances.
 * 4. Sum up all contributions under modulo 10^9 + 7.
 *
 * --------------------------------------
 * Time Complexity: O(n)
 * - Each element is pushed and popped once from the stack.
 *
 * Space Complexity: O(n)
 * - For PSEE and NSE arrays and stack.
 */

import java.util.Arrays;
import java.util.Stack;

class SumOfSubarrayMaximums {

    // Function to find Next Smaller Element (strictly less) to the right
    public int[] findNGE(int[] arr) {
        int n = arr.length;
        int[] nge = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return nge;
    }

    // Function to find Previous Smaller or Equal Element to the left
    public int[] findPGEE(int[] arr) {
        int n = arr.length;
        int[] pgee = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }
            pgee[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pgee;
    }

    public int sumSubarrayMaxs(int[] arr) {
        int mod = (int) 1e9 + 7;

        int[] nge = findNGE(arr);
        int[] pgee = findPGEE(arr);

        long answer = 0;

        for (int i = 0; i < arr.length; i++) {
            int left = i - pgee[i];
            int right = nge[i] - i;

            long contribution = ((long) arr[i] * left % mod * right % mod) % mod;
            answer = (answer + contribution) % mod;
        }

        return (int) answer;
    }

    // Test method
    public static void main(String[] args) {
        SumOfSubarrayMaximums sol = new SumOfSubarrayMaximums();
        int[] arr1 = {8, 0, 1};
        System.out.println("Sum of Subarray Maximun: " + sol.sumSubarrayMaxs(arr1)); // Output: 17

        int[] arr2 = {1, 3, 2};
        System.out.println("Sum of Subarray Maximun: " + sol.sumSubarrayMaxs(arr2)); // Output: 444
    }
}
