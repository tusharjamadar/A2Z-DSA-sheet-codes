package Hard;
/*
Problem Description:
Write a program to solve a Sudoku puzzle by filling the empty cells. The input board is a 9x9 grid with digits '1'-'9' and '.' for empty cells.
Each row, column, and 3x3 subgrid must contain all digits from 1 to 9 exactly once. It is guaranteed that the input board has only one solution.

Intuition:
Use backtracking to fill each empty cell ('.') with a valid digit from '1' to '9'. For each cell, try all digits, and proceed recursively.
If a valid digit leads to a complete solution, return true. If not, undo the change (backtrack) and try the next digit.

Algorithm:
1. Iterate over each cell in the board.
2. When an empty cell is found, try placing digits '1' to '9' one by one.
3. For each digit, check if it is valid using the `isValid()` function.
4. If valid, place it and recursively solve the rest of the board.
5. If a solution is found, return true; otherwise, backtrack and reset the cell.
6. If no digit leads to a solution, return false.

Time Complexity:
O(9^(n)), where n is the number of empty cells, because each empty cell can potentially try 9 digits.

Space Complexity:
O(1) - board is modified in-place and recursion stack is bounded (at most 81 calls).
*/

class SudokuSolver {

    // Public method to start solving the board
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    // Recursive backtracking solver function
    private boolean solve(char[][] board) {
        // Iterate through each cell in the 9x9 board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // If the current cell is empty
                if (board[i][j] == '.') {
                    // Try placing digits '1' to '9'
                    for (char ch = '1'; ch <= '9'; ch++) {
                        // Check if placing 'ch' is valid
                        if (isValid(board, i, j, ch)) {
                            // Place the digit
                            board[i][j] = ch;

                            // Recursively attempt to solve the rest of the board
                            if (solve(board)) return true;

                            // If not solvable, backtrack
                            board[i][j] = '.';
                        }
                    }
                    // If no digit fits, return false to trigger backtracking
                    return false;
                }
            }
        }
        // All cells filled successfully
        return true;
    }

    // Function to check if placing a digit is valid according to Sudoku rules
    private boolean isValid(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            // Check the row
            if (board[row][i] == ch) return false;

            // Check the column
            if (board[i][col] == ch) return false;

            // Check the 3x3 subgrid
            int subgridRow = 3 * (row / 3) + i / 3;
            int subgridCol = 3 * (col / 3) + i % 3;
            if (board[subgridRow][subgridCol] == ch) return false;
        }
        return true;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        SudokuSolver sol = new SudokuSolver();

        // Test board with empty cells represented by '.'
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // Solve the board
        sol.solveSudoku(board);

        // Print the solved board
        System.out.println("Solved Sudoku:");
        for (char[] row : board) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
