package BinarySearch.On2DArrays;

class FindPeakElementII {

    /*
    Problem:
    - Given a 2D grid, find a **peak element** which is strictly greater than all its adjacent neighbors (top, bottom, left, right).
    - No two adjacent cells are equal.
    - We assume virtual padding of -1 around the matrix.
    - Return the position [i, j] of any peak element.
    - Time complexity required: O(m log n) or O(n log m)

    Intuition:
    - Since we need to do better than O(m*n), we use a binary search strategy.
    - We apply binary search on **columns**, and in each column, find the row having the maximum element.
    - Then we compare this element with its left and right neighbors:
      → If it's greater than both, it's a peak.
      → Else move to the side which has the bigger neighbor (like hill climbing).
    - This ensures we converge toward a peak efficiently.

    Time Complexity: O(m log n)
    */

    // Helper function to find the row with maximum element in a column
    public int getMax(int[][] mat, int n, int m, int col) {
        int maxi = -1;
        int resRow = -1;

        for (int i = 0; i < n; i++) {
            if (maxi < mat[i][col]) {
                maxi = mat[i][col];
                resRow = i;
            }
        }

        return resRow;
    }

    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;      // number of rows
        int m = mat[0].length;   // number of columns

        int[] res = new int[] { -1, -1 };

        int low = 0, high = m - 1;

        // Binary search on columns
        while (low <= high) {
            int mid = (low + high) / 2;

            // Find row index having maximum in mid column
            int row = getMax(mat, n, m, mid);

            // Get left and right neighbors
            int left = mid - 1 >= 0 ? mat[row][mid - 1] : -1;
            int right = mid + 1 < m ? mat[row][mid + 1] : -1;

            int maxEle = mat[row][mid];

            // Check peak condition
            if (maxEle > left && maxEle > right) {
                res[0] = row;
                res[1] = mid;
                return res;
            } 
            // Move to side with higher neighbor
            else if (maxEle < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res; // fallback, though we should always find a peak
    }

    // Main method to test
    public static void main(String[] args) {
        FindPeakElementII sol = new FindPeakElementII();

        int[][] mat1 = {
            {1, 4},
            {3, 2}
        };
        int[] result = sol.findPeakGrid(mat1);
        System.out.println("Peak at: [" + result[0] + ", " + result[1] + "]");

        int[][] mat2 = {
            {10, 20, 15},
            {21, 30, 14},
            {7, 16, 32}
        };
        result = sol.findPeakGrid(mat2);
        System.out.println("Peak at: [" + result[0] + ", " + result[1] + "]");
    }
}
