package Heap.Hard;
import java.util.*;

public class MaximumSumCombination {

    /*
     * Problem: Maximum Sum Combination
     *
     * You are given two integer arrays `a[]` and `b[]` of equal size.
     * A sum combination is formed by adding one element from `a[]` and one from `b[]`,
     * using each index pair (i, j) at most once.
     * Return the top k maximum sum combinations, sorted in non-increasing order.
     *
     * Example 1:
     * Input: a = [3, 2], b = [1, 4], k = 2
     * Output: [7, 6]
     * Explanation: Possible sums = [3+1=4, 3+4=7, 2+1=3, 2+4=6]. Top 2 = [7, 6]
     *
     * Example 2:
     * Input: a = [1, 4, 2, 3], b = [2, 5, 1, 6], k = 3
     * Output: [10, 9, 9]
     */

    /*
     * Intuition:
     * The largest sum can be formed by picking the largest elements from both arrays.
     * To avoid generating all n^2 combinations, we use a max heap (priority queue)
     * to always pick the next maximum possible sum.
     */

    /*
     * Algorithm:
     * 1. Sort both arrays in descending order.
     * 2. Use a max heap to store combinations: (sum, index_a, index_b)
     * 3. Use a set to avoid pushing the same index pairs into the heap.
     * 4. Push the initial pair (0, 0) into the heap.
     * 5. While k > 0:
     *    a. Pop the max sum and add to result.
     *    b. Push next two valid pairs: (i+1, j) and (i, j+1), if not visited.
     */

    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        int n = a.length;

        // Step 1: Sort both arrays in descending order
        Arrays.sort(a);
        Arrays.sort(b);
        reverse(a);
        reverse(b);

        // Max Heap: stores {sum, i, j}
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[0] - x[0]);

        // Visited set to avoid duplicates
        HashSet<String> visited = new HashSet<>();

        // Initialize heap with the largest pair
        maxHeap.offer(new int[]{a[0] + b[0], 0, 0});
        visited.add("0_0");

        ArrayList<Integer> result = new ArrayList<>();

        // Step 5: Process top k sums
        while (k-- > 0 && !maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int sum = top[0], i = top[1], j = top[2];
            result.add(sum);

            // Push next pair (i+1, j)
            if (i + 1 < n) {
                String key1 = (i + 1) + "_" + j;
                if (!visited.contains(key1)) {
                    maxHeap.offer(new int[]{a[i + 1] + b[j], i + 1, j});
                    visited.add(key1);
                }
            }

            // Push next pair (i, j+1)
            if (j + 1 < n) {
                String key2 = i + "_" + (j + 1);
                if (!visited.contains(key2)) {
                    maxHeap.offer(new int[]{a[i] + b[j + 1], i, j + 1});
                    visited.add(key2);
                }
            }
        }

        return result;
    }

    // Utility method to reverse an array in-place
    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }

    /*
     * Time Complexity:
     * - Sorting both arrays: O(n log n)
     * - Heap operations for k elements: O(k log k)
     * - Total: O(n log n + k log k)
     *
     * Space Complexity:
     * - Heap: O(k)
     * - Visited set: O(k)
     * - Result list: O(k)
     * - Total: O(k)
     */

    // Main function to test the solution
    public static void main(String[] args) {
        MaximumSumCombination sol = new MaximumSumCombination();

        int[] a1 = {3, 2}, b1 = {1, 4};
        System.out.println("Top 2 sums: " + sol.topKSumPairs(a1, b1, 2)); // Output: [7, 6]

        int[] a2 = {1, 4, 2, 3}, b2 = {2, 5, 1, 6};
        System.out.println("Top 3 sums: " + sol.topKSumPairs(a2, b2, 3)); // Output: [10, 9, 9]
    }
}
