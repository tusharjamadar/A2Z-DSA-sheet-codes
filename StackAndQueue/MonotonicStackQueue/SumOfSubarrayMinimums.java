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

import java.util.Stack;

class SumOfSubarrayMinimums {

    // Function to find Next Smaller Element (strictly less) to the right
    public int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        return nse;
    }

    // Function to find Previous Smaller or Equal Element to the left
    public int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] psee = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            psee[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return psee;
    }

    public int sumSubarrayMins(int[] arr) {
        int mod = (int) 1e9 + 7;

        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);

        long answer = 0;

        for (int i = 0; i < arr.length; i++) {
            int left = i - psee[i];
            int right = nse[i] - i;

            long contribution = ((long) arr[i] * left % mod * right % mod) % mod;
            answer = (answer + contribution) % mod;
        }

        return (int) answer;
    }

    // Test method
    public static void main(String[] args) {
        SumOfSubarrayMinimums sol = new SumOfSubarrayMinimums();
        int[] arr1 = {3, 1, 2, 4};
        System.out.println("Sum of Subarray Minimums: " + sol.sumSubarrayMins(arr1)); // Output: 17

        int[] arr2 = {11, 81, 94, 43, 3};
        System.out.println("Sum of Subarray Minimums: " + sol.sumSubarrayMins(arr2)); // Output: 444
    }
}
