package Array;

import java.util.Arrays;

public class RotateImage {

    // TC -> O(n*n) SC-> O(n*n)
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                ans[j][n-i-1] = matrix[i][j]; 
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                matrix[i][j] = ans[i][j]; 
            }
        }
    }
    
    // TC -> O(n*n) SC-> O(1)
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;

        // Step 1 -> Transpose of matrix so rows become cols
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2 -> Reverse every row
        for(int i=0; i<n; i++){
            int low = 0, high = n-1;
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
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};

        System.out.println("Before rotating by 90 degree");
        for(int i=0; i<matrix.length; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        // Rotate matrix by 90 degree clockwise
        rotate2(matrix);

        System.out.println("After rotating by 90 degree");
        for(int i=0; i<matrix.length; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
