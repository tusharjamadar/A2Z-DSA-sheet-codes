package BinarySearch.On2DArrays;

class MedianRowWiseSortedMatrix {

    // ============================
    // Problem: Median in Row-Wise Sorted Matrix
    // ----------------------------
    // Given a matrix with odd dimensions where each row is sorted, 
    // find the median of all matrix elements.
    // 
    // Example:
    // mat = [[1, 3, 5], [2, 6, 9], [3, 6, 9]]
    // Sorted elements: [1, 2, 3, 3, 5, 6, 6, 9, 9] → Median = 5
    //
    // ============================
    // Intuition:
    // - The matrix is only row-wise sorted, not fully.
    // - Instead of flattening and sorting, we apply binary search on the value range.
    // - For a guessed value `x`, count how many elements are <= x.
    // - Use this count to converge on the median value.
    //
    // ============================
    // Algorithm:
    // 1. Find the minimum (low) and maximum (high) element in the matrix.
    // 2. Perform binary search between low and high:
    //    - For each mid, count how many elements in matrix <= mid.
    //    - If count <= n*m/2 → search right; else search left.
    // 3. The point where low > high, low holds the median.
    //
    // ============================
    // Time Complexity:
    // - Each binary search step: O(n * log m) due to upperBound per row.
    // - Binary search range: log(max - min)
    // - Total: O(log(max - min) * n * log m)
    // ============================

    // Function to find the index of first element greater than target (upper bound)
    int upperBound(int[] arr, int n, int target) {
        int low = 0, high = n - 1;
        int res = n; // default to length if all elements are <= target

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > target) {
                res = mid;     // potential upper bound
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    // Function to count how many elements are <= x in the matrix
    int countSmallEqual(int[][] mat, int n, int m, int x) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += upperBound(mat[i], m, x); // upper bound = count of <= x
        }
        return count;
    }

    // Main function to compute the median
    int median(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Step 1: Set initial binary search bounds
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // Update low as the smallest first element across rows
            low = Math.min(low, mat[i][0]);

            // Update high as the largest last element across rows
            high = Math.max(high, mat[i][m - 1]);
        }

        int required = (n * m) / 2; // Position of median in sorted array

        // Step 2: Binary search on the answer space (value range)
        while (low <= high) {
            int mid = (low + high) / 2;

            // Count number of elements less than or equal to mid
            int count = countSmallEqual(mat, n, m, mid);

            if (count <= required) {
                // Too few elements ≤ mid → increase mid
                low = mid + 1;
            } else {
                // Enough or too many elements → try lower
                high = mid - 1;
            }
        }

        // Step 3: low now points to the smallest value with enough elements ≤ it → median
        return low;
    }

    // ============================
    // Main method to test the logic
    // ============================
    public static void main(String[] args) {
        MedianRowWiseSortedMatrix sol = new MedianRowWiseSortedMatrix();

        int[][] mat1 = {
            {1, 3, 5},
            {2, 6, 9},
            {3, 6, 9}
        };
        System.out.println("Median of mat1: " + sol.median(mat1)); // Output: 5

        int[][] mat2 = {
            {1},
            {2},
            {3}
        };
        System.out.println("Median of mat2: " + sol.median(mat2)); // Output: 2

        int[][] mat3 = {
            {3},
            {5},
            {8}
        };
        System.out.println("Median of mat3: " + sol.median(mat3)); // Output: 5
    }
}

