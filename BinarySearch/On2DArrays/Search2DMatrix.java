package BinarySearch.On2DArrays;

class Search2DMatrix {

    /*
    Problem:
    Search a given target value in a 2D matrix with the following properties:
    - Each row is sorted in non-decreasing order.
    - The first element of each row is greater than the last element of the previous row.
    Goal: Return true if target exists in the matrix, else false.

    Intuition:
    - Since the matrix behaves like a sorted 1D array (flattened), we can apply binary search on it.
    - We treat the 2D matrix as a virtual 1D sorted array of size m * n.
    - Index mapping:
      mid in 1D --> row = mid / n, col = mid % n
      This lets us access elements in 2D form using 1D logic.

    Time Complexity: O(log(m * n)), as required.
    */

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;       // number of rows
        int n = matrix[0].length;    // number of columns

        int start = 0;
        int end = m * n - 1;

        // Apply binary search on the "flattened" 2D array
        while (start <= end) {
            int mid = start + (end - start) / 2;

            // Map 1D mid to 2D indices
            int midEle = matrix[mid / n][mid % n];

            if (midEle == target) {
                return true;         // target found
            } else if (midEle > target) {
                end = mid - 1;       // move left
            } else {
                start = mid + 1;     // move right
            }
        }

        return false; // target not found
    }

    // Main method for testing
    public static void main(String[] args) {
        Search2DMatrix sol = new Search2DMatrix();

        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println(sol.searchMatrix(matrix1, 3));   // true
        System.out.println(sol.searchMatrix(matrix1, 13));  // false
    }
}
