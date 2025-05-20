/*
✅ Problem:
Given an `n x n` 2D matrix representing an image, rotate the image by 90 degrees (clockwise) **in-place**.

🧠 Intuition:
There are two standard ways to rotate a matrix 90 degrees clockwise:
1. Using an extra matrix (not in-place).
2. Using **transpose + reverse** (in-place).

⚙️ Algorithm:
✔ Method 1: (Not in-place)
   - Create a new matrix.
   - Set `ans[j][n-1-i] = matrix[i][j]`.
   - Copy back to original.

✔ Method 2: (In-place)
   - Step 1: Transpose the matrix. (`matrix[i][j]` ↔ `matrix[j][i]`)
   - Step 2: Reverse each row.

⏱ Time Complexity:
- Both methods: O(n^2)

📦 Space Complexity:
- Method 1: O(n^2)
- Method 2: O(1) (in-place)
*/

package Array.Hard;

import java.util.Arrays;

public class RotateImage {

    // ✅ Method 1: Rotate using extra space
    // TC -> O(n^2), SC -> O(n^2)
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ans[j][n - 1 - i] = matrix[i][j];
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = ans[i][j];
            }
        }
    }

    // ✅ Method 2: Rotate in-place using transpose + reverse
    // TC -> O(n^2), SC -> O(1)
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose matrix
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for(int i = 0; i < n; i++){
            int low = 0, high = n - 1;
            while(low < high){
                int temp = matrix[i][low];
                matrix[i][low] = matrix[i][high];
                matrix[i][high] = temp;
                low++;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println("Before rotating by 90 degrees:");
        for(int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }

        // Rotate matrix by 90 degrees clockwise
        rotate2(matrix);  // or use rotate1(matrix);

        System.out.println("After rotating by 90 degrees:");
        for(int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}
