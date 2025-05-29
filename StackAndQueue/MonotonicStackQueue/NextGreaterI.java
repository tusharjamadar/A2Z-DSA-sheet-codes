package StackAndQueue.MonotonicStackQueue;
import java.util.*;

/*
 * Problem:
 * You are given two arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each element in nums1, find the next greater element in nums2.
 * If no greater element exists to the right, return -1 for that element.
 *
 * Example:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 */

/*
 * Intuition:
 * We can preprocess nums2 using a monotonic decreasing stack to find the next greater element
 * for every value in nums2 and store it in a map.
 * Then for each value in nums1, we just lookup the map.
 */

/*
 * Algorithm:
 * 1. Create a map to store next greater element for each number in nums2.
 * 2. Use a stack to process nums2 from right to left:
 *    - If the current element is smaller than the stack's top, stack's top is its next greater.
 *    - If not, pop until we find a greater or the stack is empty.
 *    - Push current element to stack.
 * 3. For each number in nums1, lookup its next greater in the map.
 */

/*
 * Time Complexity: O(n + m) where n = nums2.length and m = nums1.length
 * Space Complexity: O(n) for the map and stack
 */

public class NextGreaterI {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>(); // To store next greater element
        Stack<Integer> stack = new Stack<>(); // Monotonic decreasing stack

        // Step 1: Process nums2 and fill the map
        for (int i = nums2.length - 1; i >= 0; i--) {
            int current = nums2[i];

            // Maintain decreasing order stack
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }

            // If stack is empty, there's no greater element
            int nextGreater = stack.isEmpty() ? -1 : stack.peek();
            map.put(current, nextGreater);

            // Push current element to stack
            stack.push(current);
        }

        // Step 2: Build result for nums1 using map
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        NextGreaterI sol = new NextGreaterI();

        int[] nums1a = {4, 1, 2};
        int[] nums2a = {1, 3, 4, 2};
        System.out.println("Output 1: " + Arrays.toString(sol.nextGreaterElement(nums1a, nums2a))); // [-1, 3, -1]

        int[] nums1b = {2, 4};
        int[] nums2b = {1, 2, 3, 4};
        System.out.println("Output 2: " + Arrays.toString(sol.nextGreaterElement(nums1b, nums2b))); // [3, -1]

        // int[] nums1c = {4, 8, 5, 2, 25};
        // int[] nums2c = {4, 8, 5, 2, 25};
        // System.out.println("Output 2: " + Arrays.toString(sol.nextGreaterElement(nums1c, nums2c))); // [3, -1]
    }
}
