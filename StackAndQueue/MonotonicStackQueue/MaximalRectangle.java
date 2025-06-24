package StackAndQueue.MonotonicStackQueue;

import java.util.Stack;

class MaximalRectangle {

    /*
     * 85. Maximal Rectangle
     * 
     * 🧩 Problem:
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     * 
     * ------------------------------------------------------------------
     * 💡 Intuition:
     * - Think of each row as the base of a histogram.
     * - We transform each row into an array of "heights", where each height represents
     *   how many continuous '1's are stacked vertically up to that row.
     * - Then for each row, we calculate the largest rectangle area using the
     *   classic "Largest Rectangle in Histogram" method.
     * 
     * ------------------------------------------------------------------
     * 🧠 Algorithm:
     * 1. Convert the matrix into a prefix sum histogram (`pSum`):
     *    - For each cell (i, j), store how many consecutive 1's exist vertically up to that point.
     *    - Reset to 0 if the cell is '0'.
     * 2. For each row in the prefix matrix, use the histogram technique:
     *    - Compute the largest rectangle area using a monotonic stack.
     * 3. Return the maximum area found.
     * 
     * ------------------------------------------------------------------
     * ⏱️ Time Complexity: O(n * m)
     * - n = number of rows, m = number of columns
     * - Each cell is processed once for prefix sum and once for histogram
     * 
     * 🗃️ Space Complexity: O(n * m)
     * - For storing the prefix sum matrix
     */

    // Helper function to calculate largest rectangle in a histogram
    public int largestRectangleHistogram(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int maxi = 0;

        for (int i = 0; i <= n; i++) {
            int curr = (i == n) ? 0 : arr[i];

            // While current is less than top of stack, calculate area
            while (!st.isEmpty() && arr[st.peek()] > curr) {
                int h = arr[st.pop()];
                int w = st.isEmpty() ? i : (i - st.peek() - 1);
                maxi = Math.max(maxi, h * w);
            }
            st.push(i);
        }
        return maxi;
    }

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Step 1: Build prefix histogram
        int[][] pSum = new int[n][m];
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum = (matrix[i][j] == '1') ? sum + 1 : 0;
                pSum[i][j] = sum;
            }
        }

        // Step 2: Apply histogram logic to each row
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, largestRectangleHistogram(pSum[i]));
        }

        return maxi;
    }

    // 🔍 Test driver
    public static void main(String[] args) {
        MaximalRectangle sol = new MaximalRectangle();

        char[][] matrix1 = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };

        System.out.println("Max Rectangle Area: " + sol.maximalRectangle(matrix1)); // Output: 6
    }
}
