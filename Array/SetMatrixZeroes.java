import java.util.Arrays;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // avoiding conflict using extra variable to track col 0
        int col0 = 1;

        // Step 1: Tracking of 0 itselt in matrix
        // Using first row for col track (1 ... n)
        // Using first col for row track (0 ... m)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // mark first col to track row
                    matrix[i][0] = 0;

                    // if first col found 0 then will only update col0 var else we use first row to
                    // track
                    if (j != 0) {
                        matrix[0][j] = 0;
                    } else {
                        col0 = 0;
                    }
                }
            }
        }

        // Step 2: first update inner matrix (not for 1st row and 1st col as they are
        // using for tracking)

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != 0) {
                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // Step 3: Now update first row which using for col track
        // update only if [0][0] = 0 then update whole first row to 0
        // same for first col which s col0 == 0 then update whole col

        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col0 == 0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
