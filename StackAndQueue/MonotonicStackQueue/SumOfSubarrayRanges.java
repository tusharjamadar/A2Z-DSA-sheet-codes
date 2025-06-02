package StackAndQueue.MonotonicStackQueue;
/*
 * Problem: 2104. Sum of Subarray Ranges
 * -------------------------------------
 * Given an integer array `nums`, return the sum of the ranges of all subarrays.
 * A subarray's range = max(subarray) - min(subarray)
 *
 * Example:
 * Input: nums = [1,2,3]
 * Output: 4
 * Explanation:
 * Subarrays: [1], [2], [3], [1,2], [2,3], [1,2,3]
 * Ranges:    0,   0,   0,   1,     1,     2 → Total = 4
 *
 * ------------------------------------------------------
 * Intuition:
 * ------------------------------------------------------
 * - The range of a subarray is the difference between its max and min.
 * - So for each element, calculate how many subarrays in which it is the max,
 *   and how many where it is the min.
 * - We use monotonic stacks to find:
 *   1. Previous Greater or Equal (PGE)
 *   2. Next Greater (NGE)
 *   3. Previous Smaller or Equal (PSE)
 *   4. Next Smaller (NSE)
 * - For each element:
 *     - Total max contribution: (i - PGE[i]) * (NGE[i] - i) * nums[i]
 *     - Total min contribution: (i - PSE[i]) * (NSE[i] - i) * nums[i]
 * - Final result = sum of max contributions - sum of min contributions
 *
 * ------------------------------------------------------
 * Time Complexity: O(n)
 * - Each element is pushed/popped once from stack.
 *
 * Space Complexity: O(n)
 * - For the four arrays (PSE, NSE, PGE, NGE) and stacks.
 */

 import java.util.*;

 class SumOfSubarrayRanges {
 
     // Next Smaller Element
     private int[] nextSmaller(int[] arr) {
         int n = arr.length;
         int[] nse = new int[n];
         Stack<Integer> st = new Stack<>();
         for (int i = n - 1; i >= 0; i--) {
             while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
             nse[i] = st.isEmpty() ? n : st.peek();
             st.push(i);
         }
         return nse;
     }
 
     // Previous Smaller or Equal Element
     private int[] prevSmallerOrEqual(int[] arr) {
         int n = arr.length;
         int[] pse = new int[n];
         Stack<Integer> st = new Stack<>();
         for (int i = 0; i < n; i++) {
             while (!st.isEmpty() && arr[st.peek()] > arr[i]) st.pop();
             pse[i] = st.isEmpty() ? -1 : st.peek();
             st.push(i);
         }
         return pse;
     }
 
     // Total contribution of elements as minimum
     public long sumSubarrayMins(int[] arr) {
         int n = arr.length;
         int[] next = nextSmaller(arr);
         int[] prev = prevSmallerOrEqual(arr);
         long sum = 0;
         for (int i = 0; i < n; i++) {
             long left = i - prev[i];
             long right = next[i] - i;
             sum += left * right * (long) arr[i];
         }
         return sum;
     }
 
     // Next Greater Element
     private int[] nextGreater(int[] arr) {
         int n = arr.length;
         int[] nge = new int[n];
         Stack<Integer> st = new Stack<>();
         for (int i = n - 1; i >= 0; i--) {
             while (!st.isEmpty() && arr[st.peek()] <= arr[i]) st.pop();
             nge[i] = st.isEmpty() ? n : st.peek();
             st.push(i);
         }
         return nge;
     }
 
     // Previous Greater or Equal Element
     private int[] prevGreaterOrEqual(int[] arr) {
         int n = arr.length;
         int[] pge = new int[n];
         Stack<Integer> st = new Stack<>();
         for (int i = 0; i < n; i++) {
             while (!st.isEmpty() && arr[st.peek()] < arr[i]) st.pop();
             pge[i] = st.isEmpty() ? -1 : st.peek();
             st.push(i);
         }
         return pge;
     }
 
     // Total contribution of elements as maximum
     public long sumSubarrayMaxs(int[] arr) {
         int n = arr.length;
         int[] next = nextGreater(arr);
         int[] prev = prevGreaterOrEqual(arr);
         long sum = 0;
         for (int i = 0; i < n; i++) {
             long left = i - prev[i];
             long right = next[i] - i;
             sum += left * right * (long) arr[i];
         }
         return sum;
     }
 
     // Main function: difference between total max and total min contributions
     public long subArrayRanges(int[] nums) {
         return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
     }
 
     // Test cases
     public static void main(String[] args) {
        SumOfSubarrayRanges sol = new SumOfSubarrayRanges();
 
         int[] nums1 = {1, 2, 3};
         System.out.println("Sum of subarray ranges: " + sol.subArrayRanges(nums1));  // Output: 4
 
         int[] nums2 = {1, 3, 3};
         System.out.println("Sum of subarray ranges: " + sol.subArrayRanges(nums2));  // Output: 4
 
         int[] nums3 = {4, -2, -3, 4, 1};
         System.out.println("Sum of subarray ranges: " + sol.subArrayRanges(nums3));  // Output: 59
     }
 }
 